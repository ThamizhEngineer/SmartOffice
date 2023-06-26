import { Component, ViewEncapsulation, AfterViewInit, ViewChild, HostListener, ElementRef, Input, Output, EventEmitter, OnInit, TemplateRef } from '@angular/core';
import { Router } from "@angular/router";

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/takeWhile';
import 'rxjs/add/observable/timer'


import { SearchService } from './../../../shared/_services/search.service';
import { UserService } from "./../../../auth/_services/user.service";
import { AuthenticationService } from "./../../../auth/_services/authentication.service";

import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { InAppNotfn } from './_vo/InAppNotfn';
import { InAppNotfnService } from './in-app-notfn.service';
import { c } from '@angular/core/src/render3';
import { environment } from '../../../../environments/environment';
import { User } from '../../../auth/_models/user';

import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
	selector: "app-header-nav",
	templateUrl: "./header-nav.component.html",
	styleUrls: ['./header.css'],
	encapsulation: ViewEncapsulation.None
})
export class HeaderNavComponent implements AfterViewInit, OnInit {

	@Input() themeCss: any;
	@Output() change: EventEmitter<string> = new EventEmitter<string>();
	@Output() collapse: EventEmitter<boolean> = new EventEmitter<boolean>();

	user: User;
	lightlogo: boolean;
	miniview: boolean;
	isRightbar: boolean = true;
	isSearched: boolean = false;
	notificationToggle: boolean = false;
	msgToggle: boolean = false;
	filterToggle: boolean = false;
	profileToggle: boolean = false;
	searchArray: any;
	messages: any = [];
	filteredSearchArray: any;
	msgCount: any;
	recordTime: number = 0;
	duration: number = 10;
	search: string;
	msgdUserName: InAppNotfn;
	searchType: string;
	userName: string;
	closeResult: string;
	sessionWarning: boolean = false;
	modalReference: any;
	intervalSessionTimer: any;
	alive = true;

	@ViewChild('headerPanel') parentDiv: ElementRef;
	@HostListener('window:resize') onResize() {
		if (this.parentDiv && this.parentDiv.nativeElement && this.parentDiv.nativeElement.clientWidth <= 767) {
			this.lightlogo = false;
		} else {
			if (this.miniview) this.lightlogo = false;
		}
	}
	@HostListener('document:click', ['$event']) clickedSearchOutside($event) {
		this.isSearched = false;
		this.notificationToggle = false;
		this.msgToggle = false;
		this.profileToggle = false;
		// this.filterToggle = false;
	}

	@ViewChild('modalContent') modalContent: TemplateRef<any>;

	menuItems: any = [];
	selectedMenuItem = "";

	searchMenu = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.menuItems
				: this.menuItems.filter(x => this.menuFilterFunc(x, term) > -1)).slice(0, 8))
		);

	menuDescFormattor = (x: { name: string }) => {
		x.name
	};

	searchMenuCustom = (text$: Observable<string>) =>
	text$.pipe(
		debounceTime(200),
		distinctUntilChanged(),
		map(term => (term === '' ? this.menuItems
			: this.menuItems.filter(x => this.customMenuFilterFunc(x, term) > -1)).slice(0, 8))
	);

	customMenuDescFormattor = (x: { op2: string }) => {
		x.op2
	};

	menuAddons = ["screen", "client", "vendor", "client-purchase-order", "sale-order", "purchase-order", "job", "expense", "travel-advance"];
	selectedAddon = "screen";
	isAddon = false;

	private menuFilterFunc(x, term) {
		return ((x.name.toLowerCase().indexOf(term.toLowerCase())) && (x.parentName.toLowerCase().indexOf(term.toLowerCase())));
	}

	private customMenuFilterFunc(x, term) {
		return ((x.op2.toLowerCase().indexOf(term.toLowerCase())) && (x.op3.toLowerCase().indexOf(term.toLowerCase())));
	}

	constructor(private _service: SearchService, private inAppNotfnService: InAppNotfnService,
		private _userService: UserService, private _authService: AuthenticationService,
		private modal: NgbModal, private _router: Router) {

		this.getMenuesc(); //Menu desc

		// Start --------------
		Observable.timer(0, 300000)
			.takeWhile(() => this.alive) // only fires when component is alive
			.subscribe(() => {
				this._authService.validate().subscribe(result => {
					// If validation fails - logout will be intiated
				}, (error) => {
					alert("Token expired")
					this.logout();
					// this._router.navigate(['/logout']);
				})
			});
		// End --------------------------------
	}

	cUser;

	ngOnInit() {
		let currentUser = this.cUser = this._userService.getCurrentUser();
		this.userName = (currentUser.firstName + " " + currentUser.lastName);
		if (!(currentUser.firstName == null || currentUser.firstName == "null")) {
			this.userName = currentUser.firstName;
		}
		if (!(currentUser.lastName == null || currentUser.lastName == "null")) {
			this.userName = this.userName + " " + currentUser.lastName;
		}
		this.sessionActive(currentUser);
		let size = this._userService.getCurrentSize(this.cUser.id);
		this.miniview = this.lightlogo = !size;
		this.notfnMessage();
	}

	ngOnDestroy() {
		this.alive = false; // switches your IntervalObservable off
	}

	// Menu related ---------------------------------------------------------------

	getMenuesc() {
		this.menuItems = [];
		let user = new User
		user =this._userService.getCurrentUser();
				user.menu.forEach(parent => {
					parent.children.forEach(child => {
						var y = { name: child.name, parentState: parent.state, childState: child.state, parentName: parent.name, childPurpose: child.purpose };
						this.menuItems.push(y);
					});
				});
			
	}

	onMenuSelect(event) {
		var res = "";
		switch (this.selectedAddon) {
			case "screen":
				res = event.item.parentState + "/" + event.item.childState;
				break;
			case "client":
				res = "client/edit/"+event.item.op1;
				break;
			case "vendor":
				res = "vendor/edit/"+event.item.op1;
				break;
			case "client-purchase-order":
				res = "job/client-po";
				break;
			case "sale-order":
				res = "job/sale-order/detail/"+event.item.op1;
				break;
			case "purchase-order":
				res = "job/purchase-order/purchase-order-view/"+event.item.op1;
				break;
			case "job":
				res = "job/job-plan/detail/"+event.item.op1;
				break;
			case "expense":
				res = "my-space/expense-claim/list";
				break;
			case "travel-advances":
				res = "my-space/my-tar-request/edit?flowType=Employee-Request&id="+event.item.op1;
				break;
			default:
				break;
		}
		this._router.navigate([res]);
	}

	onAddonSelect(x) {
		this.selectedAddon = x;
		if(x=="screen"){
			this.isAddon = false;
			this.getMenuesc();
		}else{
			this.isAddon = true;
			this.fetchAddonResponse(x);
		}
	}

	fetchAddonResponse(x) {
		this.menuItems = [];
		this._service.getCustomMenuDesc(x)
			.subscribe(arg => {
				arg.forEach(element => {
					element.op4 = element.op4 + '&nbsp;&nbsp;' + element.op5;
					this.menuItems.push(element);
				});
			}, error => {
				console.log(error)
			});
	}

	// Navigate ---------------------------------------------------------------------

	myProfile() {
		this.user = this._userService.getCurrentUser();
		this._router.navigate(['/operation/employee/detail/'], { queryParams: { flowType: "My Profile", id: this.user.employeeId } });
	}

	goTochat() {
		console.log('Chat')
		this._router.navigate(['/chat']);
	}

	// Notification manager ---------------------------------------------------------

	intervalNotifTimer: any;
	notfnMessage() {
		this.inAppNotfnService.getMyInAppNotfn().subscribe(x => {
			x.forEach(element => {
				element.newMessage = element.notfnMessage.substring(0, 20);
				this.messages.push(element);
			});
			this.msgCount = this.messages.length;
			this.intervalNotifTimer = setInterval(() => {
				this.messages = new Array<InAppNotfn>();
				this.inAppNotfnService.getMyInAppNotfn().subscribe(x => {
					this.messages = x;
					console.log(this.messages)
					this.msgCount = this.messages.length;
				});

			}, 600000)
		})
	}

	// session manager -----------------------------------------------

	sessionActive(user) {
		let time = new Date(user.tokenValidityDt).getTime();
		let sessExpire = time + (environment.sessExpire * 1000);
		let sessPopup = time + (environment.sessPopup * 1000);
		this.timer(time, sessExpire, sessPopup);
	}

	timer(curTime, sessExpire, sessPopup) {
		let i = 0;
		this.intervalSessionTimer = setInterval(() => {
			i += 5000;
			if ((curTime + i) > sessExpire) {
				this.modalReference.close();
				this.logout();
			} else if ((curTime + i) > sessPopup && !this.sessionWarning) {
				this.sessionWarning = true;
				this.startTimer()
				this.modalReference = this.modal.open(this.modalContent, { size: 'lg' })
			}
		}, 5000);
	}

	// extend session model -------------------------------------------------------------

	sessOK() {
		this._authService.refresh().subscribe(data => {
			clearInterval(this.intervalSessionTimer);
			clearInterval(this.intervalPopTimer);
			this.sessionWarning = false;
			this.modalReference.close();
			this.sessionActive(data);
		});
	}

	sessClose() {
		this.modalReference.close();
		this.logout();
	}

	timeLeft: any;
	displayTime:any;
	intervalPopTimer: any;
	startTimer() {
		this.timeLeft = environment.timeLeft;
		this.displayTime = environment.timeLeft;
		this.intervalPopTimer = setInterval(() => {
			if (this.timeLeft > 0) {
				this.timeLeft--;
			} else {
				this.modalReference.close();
				this.logout();
			}
		}, 5000)
	}


	// logout -------------------------------------------------------------- 

	logout() {
		clearInterval(this.intervalSessionTimer);
		clearInterval(this.intervalNotifTimer);
		clearInterval(this.intervalPopTimer);
		this._router.navigate(['/logout']);
	}

	// Extras ------------------------------------------------------------

	ngAfterViewInit() {
		setTimeout(() => {
			this.sidebartoggler();
		});
	}

	searchFunc(event) { //Not used  for now
		if (event.keyCode == 13) {
			this.searchByText();
			return false;
		}
	}

	modalbox(search: string) { //Not used for now
		if (!search || (search && search.length == 0)) this.isSearched = false;
	}

	clickedSearchInside($event: Event) {
		$event.preventDefault();
		$event.stopPropagation();
	}

	sidebartoggler() {
		let bool = !this.miniview;
		if (this.parentDiv && this.parentDiv.nativeElement && this.parentDiv.nativeElement.clientWidth <= 767) {
			this.lightlogo = false;
			bool = this.miniview = !this.miniview;
			this.collapse.emit(bool);
		} else {

			this.lightlogo = !this.lightlogo;
			this.miniview = bool = !this.lightlogo;
			this.collapse.emit(bool);
			this._userService.setCurrentSize(this.cUser.id, !bool);
		}

	}

	searchByText() {
		let s = this.search;
		this.searchType = '';
		this.isSearched = true;
		if (s && s.length == 0) this.isSearched = false;
		this.filteredSearchArray = [];
		if (this.searchArray[s]) {
			this.filteredSearchArray = this.searchArray[s];
			this.searchType = s;
		}
		else {
			let as = s.split(' ');
			if (this.searchArray[as[0]]) {
				this.searchType = as[0];
				this.searchArray[as[0]].forEach(x => {
					for (let i in x) {
						if (x[i].toLowerCase() == as[1].toLowerCase()) this.filteredSearchArray.push(x);
					}
				});
			}
		}
	}
}

// logout() {
// 	this._authService.logout().subscribe(data => {
// 		this.modalReference.close();
// 		this._router.navigate(['login']);
// 		clearInterval(this.intervalPopTimer);
// 		clearInterval(this.intervalSessionTimer);
// 		clearInterval(this.intervalNotifTimer);
// 		localStorage.removeItem('currentUser');
// 	}, error => {
// 		console.log(error)
// 	});
// }

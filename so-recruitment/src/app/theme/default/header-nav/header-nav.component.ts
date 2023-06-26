import { Component, AfterViewInit, ViewChild, HostListener, ElementRef, Input, Output, EventEmitter, OnInit, TemplateRef } from '@angular/core';
import { Router } from "@angular/router";

import { SearchService } from './../../../shared/_services/search.service';
import { UserService } from "./../../../auth/_services/user.service";
import { AuthenticationService } from "./../../../auth/_services/authentication.service";

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/takeWhile';
import 'rxjs/add/observable/timer'

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: "app-header-nav",
	templateUrl: "./header-nav.component.html",
	styleUrls: ['./header.css'],
})
export class HeaderNavComponent implements AfterViewInit, OnInit {

	@Input() themeCss: any;
	@Output() change: EventEmitter<string> = new EventEmitter<string>();
	@Output() collapse: EventEmitter<boolean> = new EventEmitter<boolean>();

	lightlogo: boolean;
	miniview: boolean;
	isRightbar: boolean = true;
	isSearched: boolean = false;
	notificationToggle: boolean = false;
	msgToggle: boolean = false;
	profileToggle: boolean = false;
	searchArray: any;
	filteredSearchArray: any;
	search: string;
	searchType: string;
	userName: string;
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
	}

	@ViewChild('modalContent') modalContent: TemplateRef<any>;
	@ViewChild('uiAlert') uiAlert: TemplateRef<any>;

	constructor(private _service: SearchService, private _userService: UserService, 
		private _authService: AuthenticationService, private modal: NgbModal, 
		private _router: Router) {
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
		if (this.cUser.applicantId == null) {
			this.interfaceAlert();
		}
	}
	ngAfterViewInit() {
		setTimeout(() => {
			this.sidebartoggler();
		});
	}

	ngOnDestroy() {
		console.log("destroy")
		this.alive = false; // switches your IntervalObservable off
	}


	// session manager -----------------------------------------------

	sessionWarning: boolean = false;
	modalReference: any;
	interval: any;
	sessionActive(user) {
		let time = new Date(user.tokenValidityDt).getTime();
		let sessExpire = time + (7200 * 1000);
		let sessPopup = time + (6600 * 1000);
		this.timer(time, sessExpire, sessPopup);
	}

	timer(curTime, sessExpire, sessPopup) {
		let i = 0;
		this.interval = setInterval(() => {
			console.log(curTime)
			i += 5000;
			if ((curTime + i) > sessExpire) {
				this.modalReference.close();
				this.logout();
			} else if ((curTime + i) > sessPopup && !this.sessionWarning) {
				this.sessionWarning = true;
				this.startTimer();
				this.modalReference = this.modal.open(this.modalContent, { size: 'lg' });
			}
		}, 5000);
	}

	interfaceAlert() {
		this.modalReference = this.modal.open(this.uiAlert, { size: 'sm' });
	}

	// Session model actions -----------------------------------------------------

	sessOK() {
		this._authService.refresh().subscribe(data => {
			clearInterval(this.intervalForTimer);
			clearInterval(this.interval);
			this.sessionWarning = false;
			this.modalReference.close();
			this.sessionActive(data);
		});
	}
	sessClose() {
		this.modalReference.close();
		this.logout();
	}

	close() {
		this.modalReference.close();
	}


	// Timer ----------------------------------------------

	timeLeft: any;
	displayTime:any;
	intervalForTimer: any;
	startTimer() {
		this.timeLeft = 600;
		this.displayTime = 600;
		this.intervalForTimer = setInterval(() => {
			if (this.timeLeft > 0) {
				this.timeLeft--;
			} else {
				this.modalReference.close();
				this.logout();
			}
		}, 1000)
	}


	// logout -------------------------------------------------------------- 

	logout() {
		clearInterval(this.intervalForTimer);
		clearInterval(this.interval);
		this._router.navigate(['/logout']);
	}

	// extras -------------------------------------------------------------

	searchFunc(event) {
		if (event.keyCode == 13) {
			this.searchByText();
			return false;
		}
	}
	modalbox(search: string) {
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
// 		clearInterval(this.intervalForTimer);
// 		clearInterval(this.interval);
// 		localStorage.removeItem('currentUser');
// 	});
// }
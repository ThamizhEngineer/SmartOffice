import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { SaleOrderService } from './../sale-order.service';
import { BuisnessUnintService } from './../buisness-unit.service';
import { ClientPurchaseOrderService } from './../client-purchase-order.service';
import { PartnerService } from './../partner-service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BuisnessUnit, Division } from '../../vo/buisness-unit';
import{Project} from '../../vo/project';
import { Partner,PartnerContact } from '../../vo/partner';
import { SaleOrder, Goods, Services ,PartnerGsts} from './../../vo/sale-order';
import { ClientPurchaseOrder } from '../../vo/ClientPurchaseOrder';
import {ProjectService} from '../../../job/project/project.service'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { XHRBackend } from '@angular/http';
import { timeInterval } from 'rxjs/operator/timeInterval';
import { CommonService } from '../../../../shared/common/common.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

const serviceList = ["FES-T&C", "ATS-Engineering"];
const clientList = ["GE", "Mittal", "Gujarat Powerplant", "1", "2", "3"];


@Component({
	selector: 'sale-order-detail',
	templateUrl: './sale-order-detail.component.html',
	styleUrls: ['sale-order.css']
})
export class SaleOrderDetailComponent {
	addScreen: boolean = false;
	form: FormGroup;
	@ViewChild('notify') notify: TemplateRef<any>;

	searchGoods = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.materials : this.materials.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.materialCode.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
	goodsFormatter = (x: { materialName: string }) => { x.materialName};

	serviceID = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : serviceList.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));

	clientID = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : clientList.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
	
	clientPurchaseOrder: any;
	service: any;
	saleOrder: any = [];
	divisionName: any;
	gstNos: any = [];
	bu: BuisnessUnit;
	goods: any;
	isVirtualPo: boolean = false;
	clientPos: any = [];
	buList: Array<BuisnessUnit>;
	project:Project;
	partners :any;
	projects : any;
	dList: any = [];
	so: SaleOrder;
	materials:any=[];

	partnerList: Array<Partner>;
	partner:Partner
	maps : any = [];
	co: ClientPurchaseOrder;
	children = [];
	saleOrderGood: Array<Goods>;
	goodsCount = 1;
	saleOrders:"sale-orders";

	count = 1;
	rows = [1];

	modalReference: any = null;
	modalData: any;

	saveMsg: any;


	constructor(private router: Router, public commonService: CommonService, private route: ActivatedRoute, private modalService: NgbModal, private _service: SaleOrderService, private clientPurchaseOrderService: ClientPurchaseOrderService, private buisnessUnintService: BuisnessUnintService, private formBuilder: FormBuilder, private partnerService: PartnerService,private ProjectService:ProjectService) {

	}

	ngOnInit() {
		this.project= new Project();
		this.so = new SaleOrder();
		this.bu = new BuisnessUnit();
		this.so.partnerGst = new Array<PartnerGsts>();
		this.bu.divisions = new Array<Division>();
		this.partner = new Partner();
		if (this.route.params['_value']['_id'] === undefined) {
			this.addScreen = true;
		}

		if (this.addScreen) {
			this.so.saleGoods = [new Goods];
			this.so.saleServices = [new Services];
		}
		else{
			this.route.params.switchMap((par: Params) => this._service.getSaleOrder(par['_id'])).subscribe(x => {
				this.so = x;
				this.clientSelected();
				if(this.so.isVirtualPo == 'true'){
					this.valueIsVirtualPo();
				}
			});

		}

		// this.ProjectService.getPartnerById(this.project.immediateClient).subscribe(x=>{
		// 	this.partners=x;
			
		// })

		// this.so.partnerGst= [new PartnerGsts];


		// this.bu.divisions=[new Division];
		// this.co.clientId = this.so.immediateClient;
		this._service.getProjects().subscribe(x =>{
			this.projects = x;
		})

		this._service.getMaterialMasters().subscribe(x=>{
			this.materials=x.content;
			console.log(this.materials);
		});
		this.buisnessUnintService.getAllBuisnessUnit().subscribe(x => {
			this.buList = x;

			this.buList.forEach(x => {

				x.divisions.forEach(x => {
					this.divisionName = x.divisionName;

				})

			})



		});
		this.form = this.formBuilder.group({
			DeliveryTerms: [null, [Validators.required, Validators.required]],
			PaymentTerms: [null, Validators.required],
			orderAmount:[null, Validators.required],
		});

		this.partnerList = new Array<Partner>();
		this.clientPurchaseOrderService.getClients().subscribe(x => {
			this.partnerList = x;
		});

		this._service.getMapLocation().subscribe(x =>{
			this.maps = x;
		})

	}

	onGoodsSelect($event,i){
		this.so.saleGoods[i].materialId=$event.item.id;
		this.so.saleGoods[i].goodsName=$event.item.materialName;
		this.so.saleGoods[i].hsnCode=$event.item.hsnCode;
		this.so.saleGoods[i].goodUnitPrice=$event.item.unitPrice;				
	}


	addRows() {
		this.count++;
		this.rows.push(this.count);
	}
	delRow(item) {
		let i = item - 1;
		this.rows.splice(i, 1);
	}

	
	

	saveSO() {
		if (this.so.id) {
			this._service.updateSaleOrder(this.so).subscribe(x => {
				this.navigateToListPage();
				this.saveMsg = { 'type': 'success', 'text': "Sale Order Updated Successfully" };
			
			});
		}
		else {

			this._service.createSaleOrder(this.so).subscribe(x => {
				console.log(this.so);
				this.saveMsg = { 'type': 'success', 'text': "Sale Order Created Successfully" };
			});
		}
	}



	sendSoAck() {
		this._service.sendSoAck(this.so).subscribe(x => {
			console.log(this.so);
			this.saveMsg = { 'type': 'success', 'text': "Sale Order Ack sent Successfully" };
			
		})
	}
	notifiContent() {
		this.modalReference = this.modalService.open(this.notify);
		// this.saveSO();
	}
	saveSubmitforApproval() {
		if (this.so.id) {
			this._service.submitForApproval(this.so).subscribe(x => {
				console.log(this.so.id);
				this.saveMsg = { 'type': 'success', 'text': "Sale Order Submitted Successfully" }

			});
		}
	}
	saveForApproval() {
		if (this.so.id) {
			this._service.saveForApproval(this.so).subscribe(x => {
				this.saveMsg = { 'type': 'success', 'text': "Sale Order Approved Successfully" }

			});
		}
	}


	navigateToListPage() {
		this.router.navigateByUrl('job/sale-order/list');
	}

	clientSelected() {

		// List of Client Purchase Orders
		this.clientPurchaseOrderService.getClientPOs(this.so.partnerId).subscribe(x => {
			this.clientPos = x.content;
		});
		

		// List of Gsts 
		this.partner = new Partner();
		this.partner.partnerGsts = new Array<PartnerGsts>();
		this.partner.partnerContacts = new Array<PartnerContact>()
		this.partnerService.getPartnerServiceById(this.so.partnerId).subscribe(partner => {
			this.partner = partner;
			this.so.gstNo=this.partner.gstNo;
			this.so.emailId = this.partner.emailId;
			console.log(this.partner);
		});
		
	}

	valueIsVirtualPo() {

		if (this.isVirtualPo) {
			this.isVirtualPo = false;
			this.so.isVirtualPo ='N';
		} else {
			this.isVirtualPo = true;
			this.so.isVirtualPo ='Y';
		}

	}
	childrenCount($e) {
		this.children = [];
		for (let i = 0; i < $e; i++) {
			this.children.push(i);
		}
	}

	addGoods() {
		this.count++;
		this.rows.push(this.count);
		let ts = new Goods();
		this.so.saleGoods.push(ts);

	}
	delGoods(id,item) {
		console.log(item);
		this.so.saleGoods.splice(item, 1);
		this._service.deleteSaleGoods(id).subscribe(x=>{

		})
	}
	addService() {
		this.count++;
		this.rows.push(this.count);
		let ts = new Services();
		this.so.saleServices.push(ts);

	}
	delService(id,item) {
		console.log(item)
		this.so.saleServices.splice(item, 1);
		this._service.deleteSaleServices(id).subscribe(x=>{

		})
	}


}

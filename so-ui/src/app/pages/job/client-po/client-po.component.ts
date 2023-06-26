import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientPurchaseOrderService } from '.././sale-order/client-purchase-order.service';
import { ClientPurchaseOrder } from '../vo/ClientPurchaseOrder';
import { Partner } from '../vo/partner';
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { PartnerService } from '../sale-order/partner-service';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { saveAs } from 'file-saver';

@Component({
    selector: 'client-po-list',
    templateUrl: './client-po.component.html'
}) export class ClientPoComponent implements OnInit {

    @ViewChild('vdetail') vdetail: TemplateRef<any>;

    clientId: string;
    clientName: string;
    clientPo = 'client-po';
    form: FormGroup;

    constructor(private commonService: CommonService, private modalService: NgbModal,
        private clientPurchaseOrderService: ClientPurchaseOrderService,private formBuilder: FormBuilder, private partnerService: PartnerService) { }

    rows: Array<ClientPurchaseOrder>;
    clientPurchaseOrder: ClientPurchaseOrder;
    partners: Array<Partner>;
    partnerList: Array<Partner>;
    clients: Array<ClientPurchaseOrder> = [];
    clientPoList?: Array<ClientPurchaseOrder>;
    documentApiUrl: string = environment.documentApiUrl;

    ngOnInit() {
        this.starter();
        this.form = this.formBuilder.group({
			clientName: [null, [Validators.required, Validators.required]],
            refNo: [null, Validators.required],
            poDt: [null, Validators.required],
            desc: [null, Validators.required],
			orderAmount:[null, Validators.required]
		});
    }

    starter(){
        this.partners = new Array<Partner>();
        this.partnerList = new Array<Partner>();
        this.clientPurchaseOrder = new ClientPurchaseOrder();
        this.clientPoList = [new ClientPurchaseOrder];
        this.partnerService.getPartnerService().subscribe(x => {
            this.partners = x;
            this.partners.forEach(x => {
                this.partnerList.push(x);
            })
        });
        this.getClientPos();
    }

    getClientPos() {
        this.clientPurchaseOrderService.getClientPO().subscribe(x => {
            this.rows = x;

        })
    }

    modalReference: any = null;
    showDetail(id?: any) {
        this.clientPurchaseOrderService.getClientPoById(id).subscribe(x => {
            this.clientPurchaseOrder = x;
            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
            this.form = this.formBuilder.group({
                clientName: [null, [Validators.required, Validators.required]],
                refNo: [null, Validators.required],
                poDt: [null, Validators.required],
                desc: [null, Validators.required],
                orderAmount:[null, Validators.required]
            });
        });
    }

    createNew() {
        this.clientPurchaseOrder = new ClientPurchaseOrder();
        console.log(this.clientPurchaseOrder);
        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        this.form = this.formBuilder.group({
			clientName: [null, [Validators.required, Validators.required]],
            refNo: [null, Validators.required],
            poDt: [null, Validators.required],
            desc: [null, Validators.required],
			orderAmount:[null, Validators.required]
		});
    }

    saveMsg: any;
    save() {
        this.clientPurchaseOrderService.createorUpdateClientPurchaseOrders(this.clientPurchaseOrder).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Client Purchase Order Created Successfully" };
            this.getClientPos();
            this.modalReference.close();
        });
    }

    deleteRow(id?: any) {
        this.clientPurchaseOrderService.deletePurchaseOrder(id).subscribe(x => {
            console.log(x);
            this.saveMsg = { 'type': 'success', 'text': "Client Purchase Order Deleted Successfully" };
            this.getClientPos();
        }, error => {
            this.saveMsg = { 'type': 'danger', 'text': "Server Error" };
        });
    }

    fileChange(event, type: any) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            this.commonService.uploadDocument(file, type)
                .subscribe(
                    data => {
                        this.clientPurchaseOrder.docId = data[0].docId;
                    },
                    error => console.log(error))
        }
    }

    download(docId) {
        if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,'');
        } 
    }

}
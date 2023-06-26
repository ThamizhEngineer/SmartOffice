
import {  Component,OnInit,ViewChild, TemplateRef} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PurchaseOrder,PurchaseOrderLine,PurchaseOrderPayout} from '../../vo/purchase-order';
import { PurchaseOrderService} from '../purchase-order.service';
import { JobService} from '../../job.service';
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import { environment } from './../../../../../environments/environment';
import { saveAs } from 'file-saver';
import { Observable } from "rxjs/Rx";
import { CommonService } from '../../../../shared/common/common.service';

@Component({
    selector:'purchase-order-view',
    templateUrl:'./purchase-order-view.component.html',
    styleUrls:['./bootstrap-wysihtml5.css']

})
export class PurchaseOrderViewComponent implements OnInit{

    @ViewChild('sendEmailTemplate') sendEmailTemplate: TemplateRef<any>;
    @ViewChild('addPaymentTemplate') addPaymentTemplate: TemplateRef<any>;
    @ViewChild('viewPaymentTemplate') viewPaymentTemplate: TemplateRef<any>;
    @ViewChild('receivedProductTemplate') receivedProductTemplate: TemplateRef<any>;
    @ViewChild('returnPurchaseTemplate') returnPurchaseTemplate: TemplateRef<any>;
    @ViewChild('cancelTemplate') cancelTemplate: TemplateRef<any>;
    purchaseOrder:PurchaseOrder;
    purchaseOrderLine:PurchaseOrderLine;
    purchaseOrderPayout:PurchaseOrderPayout;
    documentApiUrl: string = environment.documentApiUrl;
    modelReference:any = null;
    paymentMethods=[
        {"name":"Cash","value":"CASH"},
        {"name":"Credit Card","value":"CREDIT CARD"},
        {"name":"Cheque","value":"CHEQUE"},
        {"name":"Bank Transfer","value":"BANK TRANSFER"},
        ];
    ngOnInit() {
        this.purchaseOrder = new PurchaseOrder();
        this.purchaseOrderLine = new PurchaseOrderLine();
        this.purchaseOrderPayout = new PurchaseOrderPayout();
        this.purchaseOrder.purchaseOrderLines=[];
        this.purchaseOrder.purchaseOrderPayouts=[];
        if (this.activatedRoute.params['_value']['_id']) {
			this.activatedRoute.params.switchMap((params: Params) => this.service.getPurchaseOrderById(params['_id']))
			.subscribe( x => {
                // this.loadJobPlan(x);
                // console.log(x)
                this.purchaseOrder = x;
            });
            // this.setValues();
            
        }
        // this.setValues();    
    }
    constructor(private service:PurchaseOrderService,private commonService: CommonService,private jobService:JobService,private modalService: NgbModal,private router:Router,private activatedRoute :ActivatedRoute, private http: Http){

    }
    sendEmail(){
        this.modelReference =  this.modalService.open(this.sendEmailTemplate, {size: 'lg'});
    }
    updateDate($e, obj, param){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		obj[param] = new Date(e.getTime() - (e.getTimezoneOffset() * 60000)).toJSON();
	}
    frameDate(str?: string, obj?: any){
		let year:any; let month:any; let day:any; let o:any;
		if(str){
			year = new Date(str).getFullYear();
			month = new Date(str).getMonth() + 1;
			day = new Date(str).getDate();
			
		}
		o = {year: year, month: month, day: day};
		
		if(obj) obj = o;
		else return o;
	}
    addPayment(){
        this.purchaseOrderPayout = new PurchaseOrderPayout();
        this.purchaseOrderPayout.purchaseOrderId = this.purchaseOrder.id
        this.modelReference = this.modalService.open(this.addPaymentTemplate, {size: 'lg'});
    }
    savePayment(purchaseOrderPayout:PurchaseOrderPayout){
        purchaseOrderPayout.payoutDate=purchaseOrderPayout.payoutDate+" 10:00:00";
        this.purchaseOrder.purchaseOrderPayouts.push(purchaseOrderPayout);
        let grossAmt=0,paidAmt=0,payoutAmt=0,balanceAmt=0;
        grossAmt = parseInt((this.purchaseOrder.grossPoAmt==null?"0":this.purchaseOrder.grossPoAmt));
        paidAmt = parseInt((this.purchaseOrder.totalPaidAmt==null?"0":this.purchaseOrder.totalPaidAmt));
        payoutAmt = parseInt(purchaseOrderPayout.payoutAmount);
        paidAmt=(paidAmt*1)+(payoutAmt*1);

        if(paidAmt<=grossAmt){

            balanceAmt =(grossAmt*1)-(paidAmt*1);
            this.purchaseOrder.totalPaidAmt = paidAmt.toString();
            this.purchaseOrder.totalDueAmt =balanceAmt.toString();
        }

        this.service.addPurchaseOrderPayment(this.purchaseOrder).subscribe(x=>{
        
        });
        this.service.updatePurchaseOrder(this.purchaseOrder).subscribe(x=>{

        });
        this.closePo();
     
    }

    
    viewPayment(){

        this.modelReference =this.modalService.open(this.viewPaymentTemplate, {size: 'lg'});
    }
    receivedProduct(){
        this.modelReference = this.modalService.open(this.receivedProductTemplate, {size: 'lg'});
    }

    getPoById(){
        this.service.getPurchaseOrderById(this.purchaseOrder.id).subscribe(x=>{
            this.purchaseOrder =x;

        })
    }
    receivedProductQty(){

        this.service.receivedProduct(this.purchaseOrder).subscribe(x=>{
           
        })
        this.modelReference.close();
    }
    returnPurchaseQty(){
        this.service.returnPurchase(this.purchaseOrder).subscribe(x=>{
          
        })
        this.modelReference.close();
    }
    returnPurchase(){
        this.modelReference = this.modalService.open(this.returnPurchaseTemplate, {size: 'lg'});
    }
    edit(){
     
        this.router.navigateByUrl("/job/purchase-order/purchase-order-detail/"+this.purchaseOrder.id);
    }
    listPage(){
        this.router.navigateByUrl("/job/purchase-order/purchase-order-list");
    }
    sendEmailPo(){

        this.service.sendEmail(this.purchaseOrder).subscribe(x=>{
            this.closePo();
        });

    }
    private jwt(paramArr?: any) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var currToken = this.getCurrentToken(currentUser);
        let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data','authorization': currToken});
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
        else return new RequestOptions({ headers: headers });
    }
     
    getCurrentToken(currentUser): string {
        if (currentUser && currentUser.appToken) 
          return currentUser.appToken;
        else
            return '';
      }
    fileUpload(event,purchaseOrderPayout:PurchaseOrderPayout) {
		let fileList: FileList = event.target.files;
        // console.log(fileList.length)
        // console.log(fileList[0].name)
		if (fileList.length > 0) {
			let files: File = fileList[0];

			let formData: FormData = new FormData();
			formData.append('uploadingFiles', files, files.name);
			let headers = new Headers();

			headers.append('Accept', 'application/json');
			headers.append('enctype', 'multipart/form-data');
            let options = new RequestOptions({ headers: headers });
            let documentUrl=this.documentApiUrl + "dm/upload/VENDOR-INVOICE"
			this.http.post(documentUrl, formData, this.jwt()).map((response: Response) => response)
				.map(res => res.json())
				.catch(error => Observable.throw(error))
				.subscribe(
					data => {
                        // console.log(data)
                      
                            purchaseOrderPayout.docId= data[0].docId;
                   
					

					},

					error => console.log(error)
				)
		}
    }
    cancelPurchase()
    {
        this.modelReference = this.modalService.open(this.cancelTemplate, {size: 'sm'});
    }
    generatePdf(){
        this.service.generatePdf(this.purchaseOrder).subscribe(x=>{
            this.getPoById();
            this.service.getDocument(this.purchaseOrder.docId).subscribe(y=>{
                this.download(y);
            })
            
        }, error=>{
            this.getPoById();
            this.service.getDocument(this.purchaseOrder.docId).subscribe(y=>{
                this.download(y);
            })
            //this.download(this.purchaseOrder.docId);
		})
        
    }
    deletePo(){
        this.service.deletePurchaseOrder(this.purchaseOrder).subscribe(x=>{
           
            this.listPage();
           
        });
        this.closePo();
    }
    closePo(){
        this.modelReference.close();
        this.getPoById();
    }
    download(docId: Response){

        let content_type = docId.headers.get('Content-type');
		let extension;
		switch(content_type){
			case 'application/jpeg':
			case 'application/jpg':
				extension = 'jpg';
				break;
			case 'application/png':
				extension = 'png';
				break;
			case 'application/gif':
				extension = 'gif';
				break;
			case 'application/pdf':
				extension = 'pdf';
				break;
			default:
				extension = 'pdf';
		}
        let x_filename = "Vendor-PO"+this.purchaseOrder.poRefNumber+"." + extension;
        console.log(content_type)
        saveAs(docId.blob(), x_filename);
    //     if(docId!=null&&docId!=""&&docId!=undefined){

    //         this.jobService.getDocument(docId).subscribe(x=>{
    //             console.log(x);
    //             saveAs(x.blob());
    //         })

    // } 
    }

}
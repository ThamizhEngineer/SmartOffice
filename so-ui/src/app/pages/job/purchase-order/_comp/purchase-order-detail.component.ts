
import {  Component,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PurchaseOrder,PurchaseOrderLine ,PurchaseOrderPayout} from '../../vo/purchase-order';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { JobService} from '../../job.service';
import { PurchaseOrderService} from '../purchase-order.service';
// const clients = ["client 1", "client 2", "client 3"];
@Component({
    selector:'purchase-order-detail',
    templateUrl:'./purchase-order-detail.component.html'
})
export class PurchaseOrderDetailComponent implements OnInit{
    addScreen:boolean=false;
    purchaseOrder:PurchaseOrder;
    vendors=[];
    materials=[];
    purchaseOrderLine:PurchaseOrderLine;
    
    vendorAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.vendors.filter(v => v.vendorName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    vendor_formatter = (x: {vendorName: string}) => x.vendorName;
    materialAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.materials.filter(v =>{
       
        if(v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1){
            return v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1
        }
        if(v.materialCode.toLowerCase().indexOf(term.toLowerCase()) > -1){
            return v.materialCode.toLowerCase().indexOf(term.toLowerCase()) > -1
        }
  
        
    
    
    })
        
        .slice(0, 10)) );
    material_formatter = (x: {materialName: string}) => x.materialName;
    constructor(private router:Router, private activatedRoute: ActivatedRoute,private jobService:JobService,private service:PurchaseOrderService){

    }
    ngOnInit() {
    this.purchaseOrder = new PurchaseOrder();
    this.purchaseOrderLine = new PurchaseOrderLine();
    this.purchaseOrder.purchaseOrderLines=[];
    this.getVendors();
    this.getMaterials();
        if (this.activatedRoute.params['_value']['_id']) {
			this.activatedRoute.params.switchMap((params: Params) => this.service.getPurchaseOrderById(params['_id']))
			.subscribe( x => {
                this.purchaseOrder = x;
                this.purchaseOrder.paymentDueDt=x.paymentDueDt.substring(0, 10);
                // console.log(this.purchaseOrder)
            });
           
            
        }
        this.purchaseOrder.purchaseOrderLines.push(this.purchaseOrderLine);
    }
    selectVendor(event){
        this.purchaseOrder.emailId=event.item.emailId;
        this.purchaseOrder.phoneNumber=event.item.mobileNo;
        this.purchaseOrder.address=event.item.address;
        this.purchaseOrder.vendorId=event.item.id;
        this.purchaseOrder.vendorName=event.item.vendorName;
        this.purchaseOrder.vendorCode=event.item.vendorCode;
        this.purchaseOrder.vendorPanNumber=event.item.panNo;
        this.purchaseOrder.vendorTinNumber=event.item.tinNo;
    }
    getVendors(){

        this.jobService.getJobs('vendor').subscribe(vendors=>{
          if(vendors!=null && vendors!=undefined){
            vendors.forEach(vendor=>{
                if(vendor.isVendor=="Y"){
                    this.vendors.push(vendor)
                    // console.log(this.vendors)
                }
            })
          }
        })

    }

    getMaterials(){

        this.jobService.getJobs('materials/').subscribe(materials=>{
            this.materials =materials.content;

        })

    }
    selectMaterial(event,purchaseOrderLine:PurchaseOrderLine){
        purchaseOrderLine.materialId=event.item.id;
        purchaseOrderLine.materialName=event.item.materialName;
        purchaseOrderLine.materialDesc=event.item.materialDesc;
        purchaseOrderLine.unitAmt = event.item.unitPrice;
        purchaseOrderLine.materialHsnCode = event.item.hsnCode;
    }

    calculatePoLineSubTotal(purchaseOrderLine:PurchaseOrderLine){
     
     
      
        let total = 0 ,qtr = 0 ,discount = 0, unitAmt =0,subTotalWithoutGst = 0 ,cgstDiscount=0,sgstDiscount=0,igstDiscount=0;

        if(((purchaseOrderLine.qty!=null)&&(purchaseOrderLine.qty!=undefined)) && ((purchaseOrderLine.unitAmt!=null)&&(purchaseOrderLine.unitAmt!=undefined))){
            qtr = purchaseOrderLine.qty*1;
            unitAmt = purchaseOrderLine.unitAmt*1;
            subTotalWithoutGst =  qtr * unitAmt;
            if(purchaseOrderLine.discountType!=null && purchaseOrderLine.discountType!=undefined){
                if((purchaseOrderLine.lineDiscountAmt!=null)&&(purchaseOrderLine.lineDiscountAmt!=undefined)){
                    // discount = purchaseOrderLine.lineDiscountAmt*1;
                    if(purchaseOrderLine.discountType=='PERCENTAGE'){
                        // discount = discount/100;
                     
                        purchaseOrderLine.lineDiscountAmt = (subTotalWithoutGst *purchaseOrderLine.discountPercentage)/100 ;
                  
                        purchaseOrderLine.amtBeforeDiscount = subTotalWithoutGst;
                        subTotalWithoutGst =subTotalWithoutGst -purchaseOrderLine.lineDiscountAmt;
                        purchaseOrderLine.amtAfterDiscount = subTotalWithoutGst;
                     
                    }
                    if(purchaseOrderLine.discountType=='FIXED-AMOUNT'){
                        subTotalWithoutGst = (subTotalWithoutGst*1) - (purchaseOrderLine.lineDiscountAmt*1) ;
                    }
                    
                    
                    
                   
                }
            }
            
            
        }
        total =subTotalWithoutGst;

        if(((purchaseOrderLine.cgstDiscount!=null)&&(purchaseOrderLine.cgstDiscount!=undefined))){
  
    purchaseOrderLine.cgst = (subTotalWithoutGst *purchaseOrderLine.cgstDiscount)/100 ;      
 
        }
        if((purchaseOrderLine.sgstDiscount!=null)&&(purchaseOrderLine.sgstDiscount!=undefined)&&(purchaseOrderLine.sgstDiscount>0)){
   
     
            purchaseOrderLine.sgst = (subTotalWithoutGst *purchaseOrderLine.sgstDiscount)/100 ;     

        }
        if((purchaseOrderLine.igstDiscount!=null)&&(purchaseOrderLine.igstDiscount!=undefined)&&(purchaseOrderLine.igstDiscount>0)){
            purchaseOrderLine.igst = (subTotalWithoutGst *purchaseOrderLine.igstDiscount)/100 ;         
           
        }

        total = subTotalWithoutGst+purchaseOrderLine.cgst+purchaseOrderLine.sgst+purchaseOrderLine.igst;
        purchaseOrderLine.lineGrossAmt = total;
  
        this.calculate();

    }
    calculate(){ 
        let totalCgst=0,totalIgst=0,totalSgst=0,totalTaxAmt=0,totalDiscountAmt=0,grandTotal=0,subTotal=0;
        this.purchaseOrder.purchaseOrderLines.forEach(x=>{
            if(x.cgst!=null&&x.cgst!=undefined){
                totalCgst=totalCgst+(x.cgst*1);
            }
            if(x.sgst!=null&&x.sgst!=undefined){
                totalSgst=totalSgst+(x.sgst*1);
            }
            if(x.igst!=null&&x.igst!=undefined){
                totalIgst=totalIgst+(x.igst*1);
            }
            if(x.lineGrossAmt!=null&&x.lineGrossAmt!=undefined){
                subTotal=subTotal+(x.lineGrossAmt*1);
            }
            totalTaxAmt = (totalCgst*1) + (totalSgst*1)+ (totalIgst*1);
            if(x.lineDiscountAmt!=null&&x.lineDiscountAmt!=undefined){
                totalDiscountAmt=totalDiscountAmt+(x.lineDiscountAmt*1);
            }

        })
   
        grandTotal = (subTotal*1)+parseInt((this.purchaseOrder.totalShippingAmt==null?"0":this.purchaseOrder.totalShippingAmt));

        this.purchaseOrder.cgst=totalCgst.toString();
        this.purchaseOrder.sgst=totalSgst.toString();
        this.purchaseOrder.igst=totalIgst.toString();
        this.purchaseOrder.totalTaxAmt=totalTaxAmt.toString();
        this.purchaseOrder.totalDiscountAmt=totalDiscountAmt.toString();
        this.purchaseOrder.grossPoAmt = grandTotal.toString();
    }
   
    addRows(){

        let p = new PurchaseOrderLine();
        p.purchaseOrderId = this.purchaseOrder.id;
        this.purchaseOrder.purchaseOrderLines.push(p);

    }

    delRow(item){

        this.purchaseOrder.purchaseOrderLines.splice(item,1)
    }

    updateDate($e, obj, param){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		obj[param] = new Date(e.getDate()).toJSON();
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
    save(){
        if(this.purchaseOrder.id==null){
          this.purchaseOrder.totalDueAmt=this.purchaseOrder.grossPoAmt;          
        }
         
       if(this.purchaseOrder.paymentDueDt.toString().length==10){
        this.purchaseOrder.paymentDueDt=this.purchaseOrder.paymentDueDt+" 10:00:00";
        console.log(this.purchaseOrder.paymentDueDt) ; 
    }
        if(this.purchaseOrder.id!=null){

            this.service.updatePurchaseOrder(this.purchaseOrder).subscribe(x=>{
                this.purchaseOrder = x
            });
            this.service.updatePurchaseOrderLine(this.purchaseOrder).subscribe(x=>{
                this.purchaseOrder = x

            });
            this.router.navigateByUrl("/job/purchase-order/purchase-order-view/"+this.purchaseOrder.id);
        }else{
            this.service.addPurchaseOrder(this.purchaseOrder).subscribe(x=>{
                this.purchaseOrder = x
                // console.log(x);
                // this.updatePoLines();
            })
            this.router.navigateByUrl("/job/purchase-order/purchase-order-list/");
        }
        
    }

    updatePoLines(){
        this.service.updatePurchaseOrderLine(this.purchaseOrder).subscribe(x=>{
            // console.log(x);
        })
    }
}
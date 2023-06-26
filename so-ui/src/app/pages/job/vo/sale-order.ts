import { Job } from './job';

export class SaleOrder{
	appSystemId?: string;
	appUserId?: string;
	customerName?: string;
	// deleiverySchedule?: string;
	// deleiveryTerms?: string;	
	// handlingSbu?: string;
	id?: string;
	// immediateClient?: string;
	location?:string;	
	status?: string;
	purchaseOrderId?: string;
	partnerId?: string;
	divisionId?:string;
	gstNo?: string;
	locationId?: string;
	lats?:string;
	longs?:string;
	emailId?:string;
	projectId?:string;
	projectName?: string;
	partnerName?:string;
	needsGoods?:string;
	needsServices?:string;
	saleOrderStatus?:string;
	submittedDt?:string;
	approvedDt?:string;
	m2ApprovedDt?:string;
	cancelledDt?:string;
	rejectedDt?:string;
	m2RejectedDt?:string;
	approvedRemarks?:string;
	m2ApprovedRemarks?:string;
	cancelledRemarks?:string;
	rejectedRemarks?:string;
	m2rejectedRemarks?:string;
	managerEmpId?:string;
	manager2EmpId?:string;
	deleiveryTerms?:string;
	deleiverySchedule?:string;
	notificationStatus?:string;
	notificationDt?:string;
	shippingAddress?: string;
	internalRemarks?: string;
	paymentTerms?: string;
	notificationContent?: string;
	soNumber?:string;
	isVirtualPo?:string;
	orderAmount?:number;
	virtualPoNum?:string;
	isEnabled?: string;
	listAuthFeatures?: string;	
	modifiedBy?: string;
	modifiedDt?: string;
	createdBy?: string;
	createdDt?: string;
	purchaseOrder?: string;
	saleGoods?: Array<Goods>;
	partnerGst?:Array <PartnerGsts> ;
	saleOrderCode?: string;
	saleServices: Array<Services>;
	jobs:Array<Job>;
}
export class Goods{
	createdBy?: string;
	createdDt?: string;
	goodAdditionalNotes?: string;
	goodDueOn?: string;
	goodQty?: string;
	goodUnitPrice?: string;
	materialId?: string;
	goodsName?: string='';
	hsnCode?: string;
	id?: string;
	isEnabled?: string;
	modifiedBy?: string;
	modifiedDt?: string;
	tSaleOrderId?: string;
}
export class Services{
	createdBy?: string;
	createdDt?: string;
	headCount?: string;
	id?: string;
	internalRemarks?: string;
	isEnabled?: string;
	modifiedBy?: string;
	modifiedDt?: string;
	sacCode?: string;
	serviceAdditionalNotes?: string;
	serviceDescription?: string;
	serviceName?: string;
	tSaleOrderId?: string;
}
export class PartnerGsts{
	id:string;
   partnerId:string;
   partnerName:string;
   gstNo:string;
   createdBy?: string;
   createdDt?: string;
  modifiedDt?: string;
  isEnabled?: string;
}
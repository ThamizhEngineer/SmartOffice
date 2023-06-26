export class PurchaseOrder {
    id?: string;
    vendorId?: string;
    poCode?: string;
    poDt?: string;
    vPoDt?: string;
    grossPoAmt?: string;
    totalTaxAmt?: string;
    totalDiscountAmt?: string;
    netPoAmt?: string;
    totalPaidAmt?: string="0";
    totalDueAmt?: string;
    poNote?: string;
    poStatus?: string;
    poRefNumber?: string;
    refType?: string;


    vendorCode?: string;
    vendorName?: string = "";
    poName?: string;
    billingRemarks?: string;
    address?: string;
    phoneNumber?: string;
    emailId?: string;
    shipping?: string;
    cgst?: string;
    sgst?: string;
    igst?: string;
    totalShippingAmt?: string="0";
    otherAmt?: string;
    supplierRefNumber?: string;
    otherRefNumber?: string;
    despatchThrough?: string;
    destination?: string;
    deliveryTerms?: string;
    dueOnDt?: string;
    discountPercentage?: string;
    vendorTinNumber?: string;
    vendorPanNumber?: string;
    docId?:string;
    emailSubject?: string;
	emailBody?: string;
    purchaseOrderLines: Array<PurchaseOrderLine>;
    purchaseOrderPayouts: Array<PurchaseOrderPayout>;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: Date;
    modifiedDt?: Date;
    paymentDueDt?: string;
}

export class PurchaseOrderLine {
    id?: string;
    lineDesc?: string;
    isLumpsum?: string;
    unitAmt?: number = 0;
    qty?: number = 0;
    lineGrossAmt?: number = 0;
    lineTaxAmt?: number = 0;
    lineDiscountAmt?: number = 0;
    lineStatus?: string;
    receivedQty?: number = 0;
    returnedQty?: number;
    ref?: string;
    materialId?: string = "";
    materialName?: string = "";
    materialDesc?: string = "";
    materialHsnCode?:string;
    cgst?: number = 0;
    sgst?: number = 0;
    igst?: number = 0;
    cgstDiscount?: number = 0;
    sgstDiscount?: number = 0;
    igstDiscount?: number = 0
    discountType?: string = "PERCENTAGE";
    amtBeforeDiscount?: number = 0;
    amtAfterDiscount?: number = 0;
    dueOnDt?: string;
    discountPercentage?: number = 0;
    purchaseOrderId?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: Date;
    modifiedDt?: Date;
}

export class PurchaseOrderPayout {
    id?: string;
    isAdvance?: string;
    payoutDate?: string;
    payoutAmount?: string;
    docId?: string;
    payoutRef?: string;
    payoutMethod?: string;

    purchaseOrderId?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: Date;
    modifiedDt?: Date;
}
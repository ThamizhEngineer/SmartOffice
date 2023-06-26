export class Partner{
    id:string; 
    flowType?:string;
    clientCode?:string;
    clientName?:string;
    companyTypeId?:string;
    mobileNo?:string;
    emailId?:string;
    vendorCode?:string;
    vendorName?:string;
    vendorAbbreviation?:string;
    gstNo?:string;
    panNo?:string;
    priFirstName?:string;
    priLastName?:string;
    countryId?:string;
    logoDocId?:string;
    countryName?:string;
    address?:string;
    addressLine1?:string;
    addressLine2?:string;
    city?:string;
    number?:string;
    state?:string;
    country?:string;
    tinNo?:string;
    isClient?:string;
    isVendor?:string;
    companyId?:string;
    companyName?:string;
    logoUrl?:string;
    referenceNumber?:string;
    isEnabled?:string;
    createdBy?: string;
    createdDt?: string;
    transactionCurrId?:string;
    partnerGsts?: Array<PartnerGsts>;
    partnerContacts?: Array<PartnerContact>;
    partnerEmployees?: Array<PartnerEmployee>;
    partnerDocuments?: Array<PartnerDocument>;
    partnerAccountInfos?:Array<PartnerAccountInfo>;

}
export class PartnerAccountInfo{
    id:string;
    partnerId: string;
    bankAccountName:string;
    bankAccountNo:string;
    bankBranchName:string;
    bankName:string;
    others:string;
    ifscCode:string;
    isClient?:string;
    isVendor?:string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;

}
export class PartnerEmployee{
    id:string;
    partnerId: string;
   addressLine1: string;
   addressLine2: string;
   empCity:string;
   empState: string;
   empCountry: string;
   pinCode: string;
   firstName: string;
   lastName: string;
   mobileNo: string;
   landlineNo: string;
   emailId: string;
   managerId: string;
   managerName: string;
    isClient: string;
    isVendor: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
}
export class PartnerGsts{
	 id:string;
    partnerId:string;
	gstNo:string;
    createdBy?: string;
    createdDt?: string;
   modifiedDt?: string;
   isEnabled?: string;
}
export class PartnerContact{
    id: string;
    partnerId: string;
    name :string;
    number :string;
    mobileNo :string;
    landlineNo :string;
    managerId :string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;


}
export class PartnerDocument{
    id: string;
    partnerId: string;
    docId :string;
    docNumber :string; 
    docTypeCode :string;
    docTypeId :string;
    docTypeName :string;
    isAttached :string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;

}
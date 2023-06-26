export class UploadPayslipHdr{
    id?:string;	
    upload_code ?:string;
    upload_datetime?:string;
    payMonth?:string;
    payYear ?:string;
	process_status ?:string;
	isOverwriteFlag?:string;
    records_uploaded ?:string;
    success_count ?:string;
    doc_id ?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:string;
    modifiedDt?:string;
	remarks?:string;
	salaryMonth?:string;
	salaryYear?:string;
	processId?:string;
	uploadPayslipLines?:Array<UploadPayslipLine>;	
}

export class UploadPayslipLine{
    id?:string;	
	
	employeeCode?:string;
	employeeName?:string;
	designation?:string;
	lossOfPay?:string;
	salaryMonth?:string;
	salaryYear?:string;
	salaryOfMonth?:string;
	salaryPaidMonth?:string;
	epf_uan_no?:string;
	epf_no?:string;
	esi_no?:string;
	payMode?:string;
	paidOn?:string;
	bankAccountNumber?:string;
	ifscCode?:string;
	a11Name?:string;
	a11Value?:string;
	a12Name?:string;
	a12Value?:string;
	a13Name?:string;
	a13Value?:string;
	a14Name?:string;
	a14Value?:string;
	a15Name?:string;
	a15Value?:string;
	a16Name?:string;
	a16Value?:string;
	a17Name?:string;
	a17Value?:string;
	a18Name?:string;
	a18Value?:string;
	a21Name?:string;
	a21Value?:string;
	a22Name?:string;
	a22Value?:string;
	a23Name?:string;
	a23Value?:string;
	a24Name?:string;
	a24Value?:string;
	a25Name?:string;
	a25Value?:string;
	t1Name?:string;
	t1Value?:string;
	t2Name?:string;
	t2Value?:string;
	b11Name?:string;
	b11Value?:string;
	b12Name?:string;
	b12Value?:string;
	d11Name?:string;
	d11Value?:string;
	d12Name?:string;
	d12Value?:string;
		d13Name?:string;
	d13Value?:string;
	d14Name?:string;
	d14Value?:string;
	d15Name?:string;
	d15Value?:string;
	d16Name?:string;
	d16Value?:string;
	d17Name?:string;
	d17Value?:string;
	t3Name?:string;
	t3Value?:string;
	email?:string;
	mobileNo?:string;
	isClean?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:string;
    modifiedDt?:string;
	remarks?:string;
	status?:string;

	isSelect?:boolean;
}
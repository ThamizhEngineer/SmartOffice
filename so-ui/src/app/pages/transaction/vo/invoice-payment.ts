export class InvoicePayment {
    id:             number;
    paymentCode:    string;
    clientId:       string;
    clientName:     string='';
    amountReceived: string;
    paymentDt:      Date;
    paymentRefNo:   string;
    paymentMode:    string;
    paymentNote:    string;
    docId:          string;
    paymentLine:    PaymentLine[];
    isEnabled:      string;
    createdBy:      string;
    modifiedBy:     string;
    createdDt:      string;
    modifiedDt:     string;
}

export class PaymentLine {
    id:            number;
    paymentHdrId:  string;
    invoiceHdrId:  string;
    date:          string;
    invoiceCode:   string;
    refInvoiceNo:  string;
    invoiceAmount: number;
    dueAmt:        number;
    paidAmt:       number;
    invoicePaidAmt:       number;
    adjPmtAmt:     number;
    isEnabled:     string;
    createdBy:     string;
    modifiedBy:    string;
    createdDt:     string;
    modifiedDt:    string;
}

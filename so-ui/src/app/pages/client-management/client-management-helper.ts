export default class ClientManagementHelper {
    static sort(list, type, isDescending) {
        switch (type) {
            case "poRefNo":
                if (isDescending == true) { return list.sort((a, b) => (a.poRefNo > b.poRefNo) ? 1 : -1) }
                else { return list.sort((a, b) => (a.poRefNo > b.poRefNo ? -1 : 1)); }
            case "saleOrderCode":
                if (isDescending == true) { return list.sort((a, b) => (a.saleOrderCode > b.saleOrderCode) ? 1 : -1) }
                else { return list.sort((a, b) => (a.saleOrderCode > b.saleOrderCode ? -1 : 1)); }
            case "refInvoiceNo":
                if (isDescending == true) { return list.sort((a, b) => (a.refInvoiceNo > b.refInvoiceNo) ? 1 : -1) }
                else { return list.sort((a, b) => (a.refInvoiceNo > b.refInvoiceNo ? -1 : 1)); }
            case "paymentCode":
                if (isDescending == true) { return list.sort((a, b) => (a.paymentCode > b.paymentCode) ? 1 : -1) }
                else { return list.sort((a, b) => (a.paymentCode > b.paymentCode ? -1 : 1)); }
            default:
                return list;
        }
    }
}
export default class ReportHelper {

    static formUrlOrderIntake(url, soCode?: string, soOrderAmount?: string, isVirtualPo?: string,
        virtualPoNum?: string, hasGoods?: string, hasServices?: string,
        buName?: string, divisionName?: string, functionName?: string,
        clientCode?: string, clientName?: string, countryName?: string,
        jobCodes?: string, monthNo?: string, year?: string) {

        if (this.nullCheck(soCode)) {
            url = url + "&soCode=" + soCode;
        }
        if (this.nullCheck(soOrderAmount)) {
            url = url + "&soOrderAmount=" + soOrderAmount;
        }
        if (this.nullCheck(isVirtualPo)) {
            url = url + "&isVirtualPo=" + isVirtualPo;
        }
        if (this.nullCheck(virtualPoNum)) {
            url = url + "&virtualPoNum=" + virtualPoNum;
        }
        if (this.nullCheck(hasGoods)) {
            url = url + "&hasGoods=" + hasGoods;
        }
        if (this.nullCheck(hasServices)) {
            url = url + "&hasServices=" + hasServices;
        }
        if (this.nullCheck(buName)) {
            url = url + "&buName=" + buName;
        }
        if (this.nullCheck(divisionName)) {
            url = url + "&divisionName=" + divisionName;
        }
        if (this.nullCheck(functionName)) {
            url = url + "&functionName=" + functionName;
        }
        if (this.nullCheck(clientCode)) {
            url = url + "&clientCode=" + clientCode;
        }
        if (this.nullCheck(clientName)) {
            url = url + "&clientName=" + clientName;
        }
        if (this.nullCheck(countryName)) {
            url = url + "&countryName=" + countryName;
        }
        if (this.nullCheck(jobCodes)) {
            url = url + "&jobCodes=" + jobCodes;
        }
        if (this.nullCheck(monthNo)) {
            url = url + "&monthNo=" + monthNo;
        }
        if (this.nullCheck(year)) {
            url = url + "&year=" + year;
        }
        return url;
    }

    static formUrlOrderRevenue(url?: string, soCode?: string, invoiceCode?: string, monthNo?: string, year?: string,
        quarterName?: string, buName?: string, divisionName?: string, functionName?: string, clientCode?: string,
        clientName?: string, countryName?: string, jobCodes?: string) {

        if (this.nullCheck(soCode)) {
            url = url + "&soCode=" + soCode;
        }
        if (this.nullCheck(buName)) {
            url = url + "&buName=" + buName;
        }
        if (this.nullCheck(divisionName)) {
            url = url + "&divisionName=" + divisionName;
        }
        if (this.nullCheck(functionName)) {
            url = url + "&functionName=" + functionName;
        }
        if (this.nullCheck(clientCode)) {
            url = url + "&clientCode=" + clientCode;
        }
        if (this.nullCheck(clientName)) {
            url = url + "&clientName=" + clientName;
        }
        if (this.nullCheck(countryName)) {
            url = url + "&countryName=" + countryName;
        }
        if (this.nullCheck(jobCodes)) {
            url = url + "&jobCodes=" + jobCodes;
        }
        if (this.nullCheck(monthNo)) {
            url = url + "&monthNo=" + monthNo;
        }
        if (this.nullCheck(year)) {
            url = url + "&year=" + year;
        }
        if (this.nullCheck(invoiceCode)) {
            url = url + "&invoiceCode=" + invoiceCode;
        }
        if (this.nullCheck(quarterName)) {
            url = url + "&quarterName=" + quarterName;
        }
        return url;
    }

    static nullCheck(input) {
        if (input != "" && input != undefined) {
            return true;
        } else {
            return false;
        }
    }
}
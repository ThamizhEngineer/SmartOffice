import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { environment } from '../../../../../environments/environment';
import { office } from '../vo/office';
import{OfficeMasterService} from '../office-master.service';
@Component({
    selector: 'office-master-list',
    templateUrl: './office-master.component.html'
}) export class OfficeMasterComponent implements OnInit {
    @ViewChild('vdetail') vdetail: TemplateRef<any>;

    
   
    form: FormGroup;
    clientId: string;
    clientName: string;
    employeeId: any;
    empName: any;
    page :number = 1;
    pageSize :number = 10;
    
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private officeMasterService: OfficeMasterService) {

    }
   
  
   office:office;
   offices:any=[];
   officeList :Array<office>
    emp:any=[];
    shifts:any=[];
    country:any=[];
    documentApiUrl: string = environment.documentApiUrl;
    ngOnInit() {
     
     
       this.office = new office();
        this.officeList= new Array<office>();
        
        this.officeMasterService.getAllOffices().subscribe(x => {
            this.offices = x;
            console.log(this.offices);
          
        });
        this.officeMasterService.getAllEmployees().subscribe(x=>{
            this.emp=x;
            console.log(this.emp);
        })

        this.officeMasterService.getAttendanceShift().subscribe(x=>{
            this.shifts=x;
        })

        this.officeMasterService.getAllCountry().subscribe(x=>{
            this.country=x;
        })
        this.form = this.formBuilder.group({
			Office: [null, [Validators.required, Validators.required]],
			
		});
       

       
    }
    getOffices(){
        this.officeMasterService.getAllOffices().subscribe(x => {
            this.offices = x;
            console.log(this.offices);
          
        });
    }
 

    getMapLocation($event){
        this.office.mapLocId=$event.id
        this.office.locName=$event.locName;
    }


    modalReference: any = null;

    showDetail(id?: any) {
        this.officeMasterService.getOfficeById(id).subscribe(x => {
            this.office = x;
            this.officeList.push(this.office);

            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        });
    }
    createNew() {
        this.office = new office();

        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }
     saveMsg: any;
    save() {
        if (this.office.id) {
            this.officeMasterService.createorUpdateOffice(this.officeList).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "Office Updated Successfully" };
                this.getOffices();
                
                this.modalReference.close();
            });
    }else{
        this.officeMasterService.createOffice(this.office).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Office Created Successfully" };
            this.getOffices();
            console.log(this.office);
            this.modalReference.close();
        });
    }


    }
    
    adminSelected($event){
        console.log($event)
        this.office.employeeId=$event.id;
        this.office.empName=$event.empName;
    }

    deleteRow(id?: any){
		this.officeMasterService.deleteOffice(id).subscribe(x=>{
            console.log(x);
            this.saveMsg = {'type': 'success', 'text': "Office Deleted Successfully"};
            this.getOffices();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
//         private jwt(paramArr?: any) {
//             let currentUser = JSON.parse(localStorage.getItem('currentUser'));
//             let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data','authorization': currentUser.appToken});
//             if(paramArr){
//                 let myParams = new URLSearchParams();
//                 paramArr.forEach( x => myParams.append(x.key, x.value) );
//                 return new RequestOptions({ headers: headers, params: myParams });
//             }
//             else return new RequestOptions({ headers: headers });
//         }
//         fileChange(event,type:any) {
//             let fileList: FileList = event.target.files;
         
//             // console.log(jobAsset)
//             // console.log(jobAssetDocs)
          
//             if (fileList.length > 0) {
//                 let files: File = fileList[0];
    
//                 let formData: FormData = new FormData();
//                 formData.append('uploadingFiles', files, files.name);
//                 let headers = new Headers();
    
//                 headers.append('Accept', 'application/json');
//                 headers.append('enctype', 'multipart/form-data');
//                 let options = new RequestOptions({ headers: headers });
//                 let documentUrl=this.documentApiUrl + "dm/upload/"+type
//                 this.http.post(documentUrl, formData, this.jwt()).map((response: Response) => response)
//                     .map(res => res.json())
//                     .catch(error => Observable.throw(error))
//                     .subscribe(
//                         data => {
                   
                               
//                                     // jobAssetDocs.docId = data[0].docId;
//                                this.clientPurchaseOrder.docId=data[0].docId;
//                          console.log(this.clientPurchaseOrder.docId);
    
                        
    
//                         },
    
//                         error => console.log(error)
//                     )
//             }
//             // console.log(jobAsset)
//         }
//         download(docId){

//             if(docId!=null&&docId!=""&&docId!=undefined){
    
//                 this.clientPurchaseOrderService.getDocument(docId).subscribe(x=>{
//                     saveAs(x.blob());
//                 })
    
//         } 
//         }
	
}
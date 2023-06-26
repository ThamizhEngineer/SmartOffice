import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserGroupMasterService } from '../user-group/user-group.service';
import { userGroup } from '../user-group/vo/userGroup';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
    selector: 'user-group-master',
    templateUrl: './user-group-master.component.html'
}) export class UserGroupComponent implements OnInit {
    @ViewChild('vdetail') vdetail: TemplateRef<any>;


    constructor(private modalService: NgbModal,private formBuilder: FormBuilder,private service: UserGroupMasterService) {

    }
    form: FormGroup;
   usergroup: userGroup;
   usergrouplist:Array<userGroup>;
    userGroups : any=[];
    page :number = 1;
    pageSize :number = 10;
 
    ngOnInit() {
        this.usergrouplist =new Array<userGroup>();
         this.usergroup =new userGroup;
        this.service.getAllUserGroup().subscribe(x => {
            this.userGroups = x;
            console.log(this.userGroups);
          
        });

        this.form = this.formBuilder.group({
			UserGroup: [null, [Validators.required, Validators.required]],
			
		});
        this.getUserGroups();
    }

 

    
    getUserGroups(){
        this.service.getAllUserGroup().subscribe(x => {
            this.userGroups = x;
           
          
        });
    }

    modalReference: any = null;


    showDetail(id?: any) {
        this.service.getUserGroupById(id).subscribe(x => {
            if(x.isHrL1=="N"){
              x.isHrL1=null;
            }
           if(x.isHrL2=="N"){
            x.isHrL2=null; 
           }
           if(x.isAcctL1=="N"){
            x.isAcctL1= null;
           }
           if(x.isAcctL2=="N"){
            x.isAcctL2= null;
           }if(x.isDir=="N"){
               x.isDir=null;
           }if(x.isMgnt=="N"){
            x.isMgnt=null;
           }if(x.isAdmin=="N"){
            x.isAdmin=null;
        }
            this.usergroup = x;
         
           this.usergrouplist.push(this.usergroup);
            console.log(this.usergrouplist);
            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        });
    }
    createNew() {
   
       this.usergroup = new userGroup();

        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }
  
     
    saveMsg: any;
    save() {
        this.usergroup.isHrL1=(this.usergroup.isHrL1?"Y":"N");
        this.usergroup.isHrL2=(this.usergroup.isHrL2?"Y":"N");
        this.usergroup.isAcctL1=(this.usergroup.isAcctL1?"Y":"N");
        this.usergroup.isAcctL2=(this.usergroup.isAcctL2?"Y":"N");
        this.usergroup.isDir=(this.usergroup.isDir?"Y":"N");
        this.usergroup.isMgnt=(this.usergroup.isMgnt?"Y":"N");
        this.usergroup.isAdmin=(this.usergroup.isAdmin?"Y":"N");
        console.log(this.usergroup.id)
       
       // 
       console.log(this.usergrouplist)
        if (this.usergroup.id) {
        
            this.service.createorUpdateUserGroup(this.usergrouplist).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "Office Updated Successfully" };
                this.getUserGroups();
                
                this.modalReference.close();
            });
    }else{
   
       
        this.service.createUserGroup(this.usergroup).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Office Created Successfully" };
            this.getUserGroups();
            console.log(this.usergroup);
            this.modalReference.close();
        });
    }


    }
    

   


        deleteRow(id?: any){
            this.service.deleteUserGroup(id).subscribe(x=>{
               
                this.saveMsg = {'type': 'success', 'text': "User Group Employee Mapping deleted Successfully"};
                this.getUserGroups();
            },error=>{
                this.saveMsg = {'type': 'danger', 'text': "Server Error"};
                this.getUserGroups();

            });
            }
}
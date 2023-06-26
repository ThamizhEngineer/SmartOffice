import { Component, OnInit } from '@angular/core';
import { ManagerSwap } from './manager-swap';
import { ManagerSwapService } from './manager-swap.service';
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Component({
    selector: 'manager-swap-detail',
    templateUrl: 'manager-swap-detail.component.html',
    styleUrls:['./manager-swap.css']
})


export class ManagerSwapDetailComponent {
    empName: any ="";
    e1Name: any = "";
    e2Name:any ="";
    constructor(private service:ManagerSwapService) { }
    isMan:boolean=false;
    managers:any=[];
    reviewManagers:any=[];
    managerDetail:any=[];
    page :number = 1;
    pageSize :number = 5;
    page1 :number = 1;
    pageSize1 :number = 5;
    managerId:any;
    
    managerList1:any=[];
    managerList2:any=[];
    manager:boolean=false;
    masterSelected:boolean = false;
    masterSelected2:boolean = false;

    m1List:any=[];
    m2List:any=[];

    saveMsg: any;
    managerSwap:ManagerSwap;

    ngOnInit() { 
      this.managerSwap = new ManagerSwap();
      this.managers=new Array<any>();
       this.getManagers();
       // this.reset();
    }

   
    getManagers(){
      this.service.getAllManager().subscribe(x=>{
        this.managerList1=x;
      })
    }

    fetchTeam(managerId:any){
      this.managerId=managerId;
        console.log(managerId);
        this.isMan=true;
        this.manager=true;
        this.service.getAllManager().subscribe(x=>{
          this.managerList2=x;
        })
        this.service.getManager(managerId).subscribe(x=>{
            this.managers=x;
            console.log(this.managers);
            this.findManager(managerId);
          })
          }

    findManager(managerId){
      this.managerDetail=[];
      this.reviewManagers=[];
     
      for(let element of this.managers){
        element.selected=false,element.rselected=false,element.hr1selected=false,element.hr2selected=false,element.acc1selected=false,element.acc2selected=false;
     console.log(element);
        if(element.n1EmpId == managerId){
          this.managerDetail.push(element)
        }
        if(element.n2EmpId == managerId){
          this.reviewManagers.push(element)
        }
      }
      console.log(this.managerDetail);
      console.log(this.reviewManagers);
  
    }

    reset(){
        this.managerSwap.existingId=this.managerId;
        this.isMan=false;
        this.manager=false;
        this.managerDetail=[];
        this.reviewManagers=[];
        this.managerSwap.m1Id='';
        this.managerSwap.m2Id='';
        this.masterSelected=false;
        this.masterSelected2=false;
        this.managerList2=[];
    }
   


    checkUncheckAll() {
        for (var i = 0; i < this.managerDetail.length; i++) {
          this.managerDetail[i].selected = this.masterSelected;
        }
        for (var a = 0; a < this.reviewManagers.length; a++) {
          this.reviewManagers[a].rselected = this.masterSelected2;
        }
      }
      

      isAllSelected() {

        this.masterSelected = this.managerDetail.every(function(mg1:any) {
          return mg1.selected == true;
        })
        this.masterSelected2 = this.reviewManagers.every(function(mg2:any) {
            return mg2.rselected == true;
          })                    
      }
  

      swapEmployee(exId:any,n1:any,n2:any){
        this.m1List = [];
        this.m2List = [];
       
        
        for (var i = 0; i < this.managerDetail.length; i++) {
          if(this.managerDetail[i].selected){
          this.m1List.push(this.managerDetail[i].id);
        }
        }
        for (var i = 0; i < this.reviewManagers.length; i++) {
          if(this.reviewManagers[i].rselected){
          this.m2List.push(this.reviewManagers[i].id);
        }
        }
        
        this.managerSwap.existingId=exId;
        this.managerSwap.m1Id=n1;
        this.managerSwap.m2Id=n2;

        this.managerSwap.m1List=this.m1List;
        this.managerSwap.m2List=this.m2List;

        
        console.log(this.managerSwap);
        
       this.batchUpdate(this.managerSwap);
       
      }
     
      batchUpdate(managerSwap:any){

        this.service.batchUpdate(managerSwap).subscribe(x => {
          this.saveMsg = { type: 'success', text: "Manager Updated successfully"}
          //this.router.navigateByUrl("/partcipants-list");
        this.ngOnInit();
          this.reset();
         this.fetchTeam(this.managerId);
      }, e=>{this.saveMsg = {'type': 'danger', 'text': "Error in update Manager"} 
    });
    
      }

      managerInterviewer($event){
        this.managerSwap.existingId = $event.id;
        this.empName=$event.empName;
      }

      N1Selected($event){
        this.managerSwap.m1Id = $event.id;
        this.e1Name=$event.empName;
      }

      N2Selected($event){
        this.managerSwap.m2Id = $event.id;
        this.e2Name=$event.empName;
      }
    }
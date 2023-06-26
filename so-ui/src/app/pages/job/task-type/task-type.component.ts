import { Component, OnInit,ViewEncapsulation, ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../job.service';
import { TaskType } from '../vo/task-type';
import { CommonService } from '../../../shared/common/common.service';
import { TaskTypeService } from './task-type.service';
@Component({
    selector: 'task-type',
    templateUrl: './task-type.component.html'
})

export class TaskTypeComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;
    rows: any=[];
    taskType: TaskType;
    modalData: any;
    modalReference: any;
    page :number = 1;
    pageSize :number = 10;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private router:Router, private modalService: NgbModal, private service:TaskTypeService, private commonService: CommonService){ }

    ngOnInit() {
        this.taskType=new TaskType();
        console.log(this.taskType)
        this.getData();
     }

    getData(){
        this.service.getTaskType().subscribe(x=> {
            this.rows = x;
            console.log(this.rows)
        })
    }

    create(){
        this.taskType=new TaskType();
        this.modalData = new TaskType();
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
        
    }

    show(id:string){
        this.service.getTaskTypeById(id).subscribe(x=>{
            this.taskType=x;
            console.log(this.taskType)
            this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
            });
    }
    save(){
        if(this.taskType.id){
                this.service.updateTaskType(this.taskType, this.taskType.id).subscribe(x => { 
                this.saveMsg = { 'type': 'success', 'text': "Updated Successfully" };
                this.getData();
                this.modalReference.close();
            } );             
         }
        
         else{
            this.service.addTaskType(this.taskType).subscribe(x =>{
            this.saveMsg = { 'type': 'success', 'text': "Created Successfully" };
            this.getData();
            this.modalReference.close();
            
        });
        }
    }

    delete(id:string){
        this.service.deleteTaskType(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }
}
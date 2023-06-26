import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyTarRequestService } from '../../my-tar.service';
import { status_css } from '../../../vo/status'

@Component({
    selector: 'my-tar-acc2-approve-list',
    templateUrl: 'my-tar-acc2-approve-list.component.html'
})

export class MyTarAcc2ApprovceComponent implements OnInit {
    list:any=[];
    binding:any='true';
    collectionSize:any;
    pageNumber = 1;
    pageSize = 10;

    active:any=[];
    inactive:any=[];
	statuses:any=status_css;

    constructor( private router: Router,private service:MyTarRequestService) { }

    ngOnInit() {

        this.service.getAllAcc2List().subscribe(x=>{
            for(let list of x){
                if(list.tarStatus=='ACC2-APPROVAL-PENDING'){
                    this.active.push(list);
                }else{
                    this.inactive.push(list);
                }  
            }
           
        })
        this.collectionSize = this.list.length;
     }

     pageChanged(pN: number): void {
        this.pageNumber = pN;
      }

      changeView(value){
        this.binding=value;
    }

     navigateToDetailPage(id:number){
         console.log(id)
         this.router.navigate(['/my-task/my-tar-acc2-approve/approve'] ,{queryParams:{flowType:"Acc2-Manager",id:id}});
    }
}
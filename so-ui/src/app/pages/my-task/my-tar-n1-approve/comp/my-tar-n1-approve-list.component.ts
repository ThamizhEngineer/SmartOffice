import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyTar } from '../../../vo/my-tar';
import { MyTarRequestService } from '../../my-tar.service';
import { status_css } from '../../../vo/status'

@Component({
    selector: 'list',
    templateUrl: 'my-tar-n1-approve-list.component.html'
})

export class MyTarN1ApproveComponent implements OnInit {
   
    active:any=[];
    inactive:any=[];
    binding:any='true';
    statuses:any=status_css;   
    pageNumber = 1;
    pageSize = 10;

    constructor( private router: Router, private service:MyTarRequestService) { }

    ngOnInit() {
        this.service.getAllN1List().subscribe(x=>{
            for(let list of x){
                if(list.tarStatus=='N1-APPROVAL-PENDING' || list.tarStatus=='N2-APPROVAL-PENDING' ){
                    this.active.push(list);
                }else{
                    this.inactive.push(list);
                }  
            }
        })   
     }

     pageChanged(pN: number): void {
        this.pageNumber = pN;
      }

      changeView(value){
        this.binding=value;
    }

     navigateToDetailPage(id:number){
         console.log(id)
         this.router.navigate(['/my-task/my-tar-n1/approve'] ,{queryParams:{flowType:"N1-Manager",id:id}});
    }
}
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MyTar } from '../../../vo/my-tar';
import {User} from '../../../../auth/_models/user';
import { UserService } from '../../../../auth/_services/user.service';
import { MyTarRequestService } from '../my-tar-request.service';

@Component({
    selector: 'my-tar-request-list',
    templateUrl: 'my-tar-request-list.component.html'
})

export class MyTarRequestListComponent implements OnInit {
 
active:any=[];
inActive:any=[];    
list:any=[];
user:User;
pageNumber = 1;
pageSize = 10;
collectionSize:any;
binding:string;

constructor( private router: Router,
    private userService:UserService,
     private MyTarService:MyTarRequestService) { }

ngOnInit() {

    this.binding='true'
    this.user = this.userService.getCurrentUser();
  
this.MyTarService.getAllTarList().subscribe(x=>{
    this.list=x;
    for(let list of this.list){
        
        if(list.tarStatus == 'CREATED' || list.tarStatus == 'N1-APPROVAL-PENDING' || list.tarStatus == 'N1-APPROVED' || list.tarStatus == 'ACC2-APPROVAL-PENDING'){
            this.active.push(list);
               }
        
        
        if(list.tarStatus == 'CANCELLED' || list.tarStatus == 'N1-REJECTED' || list.tarStatus == 'ACC2-APPROVED' || list.tarStatus =='ACC2-REJECTED' || list.tarStatus =='APPROVED'){
            this.inActive.push(list);
        }
        
    }
    
    this.collectionSize = this.list.length;
})

 }

 pageChanged(pN: number): void {
    this.pageNumber = pN;
  }


  changeActive(id:any){ 
    console.log(id);
    this.binding=id;
   }

 navigateToDetailPage(id:number){
     this.router.navigate(['/my-space/my-tar-request/edit'] ,{queryParams:{flowType:"Employee-Request",id:id}});
     
}
}
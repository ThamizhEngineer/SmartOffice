import { MyTarRequestService } from './../../my-space/my-tar-request/my-tar-request.service';
import { MyTar } from '../../vo/my-tar';

import { Component, OnInit } from '@angular/core';
@Component({
    selector: '',
    templateUrl: './travel-advance-list.component.html'
})

export class TravelAdvanceListComponent implements OnInit {
    constructor(private Service: MyTarRequestService ) { }
    row:Array<MyTar>;
    ngOnInit() {
        this.getList();
     }
   

     getList(){
         this.Service.getTarApproved().subscribe( x =>{
             this.row = x;
             console.log(this.row);

         });
     }

}
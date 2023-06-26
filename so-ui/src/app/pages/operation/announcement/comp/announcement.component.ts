import { Component, OnInit } from '@angular/core';

import { AnnouncementService } from '../announcement.service'
import { Announcement, Office } from '../vo/Announcement';

@Component({
    selector: '',
    templateUrl: 'announcement.component.html'
})

export class AnnouncementComponent implements OnInit {
  
    office:Office;
    offices:any=[];
    announcement :Announcement;
    dropdownSettings= {};
  
    constructor(
        private service:AnnouncementService
    ) { }

    ngOnInit() {
        this.announcement = new Announcement();
        this.announcement.offices= new Array<Office>();
        this.office = new Office();

        this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'officeName',
      itemsShowLimit: 2,
      allowSearchFilter: true
    };

    this.service.getOffices().subscribe(off=>{
        this.offices=off;
        console.log(this.offices);
    })

     }
     onItemSelect(item: any) {
        console.log(item);
        this.announcement.offices.push(item);
      }
      onSelectAll(items: any) {
        console.log(items);
       items.forEach(x => {
           this.announcement.offices.push(x);
       });
       console.log(this.announcement);
      }
     saveMsg:any;
     send(){
       
        this.service.sendAnnouncemnt(this.announcement).subscribe(x=>{
        
            this.saveMsg={ type: 'success', text: "Announcement Sent Successfully" }
        })

     }


}
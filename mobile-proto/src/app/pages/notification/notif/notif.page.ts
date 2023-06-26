import { Component, OnInit } from '@angular/core';
import { NotifService } from './notif.service';

@Component({
  selector: 'app-notif',
  templateUrl: './notif.page.html',
  styleUrls: ['./notif.page.scss'],
})
export class NotifPage implements OnInit {
data:any;
  constructor(private _service: NotifService) { }

  ngOnInit() {
    this._service.getNofifData().subscribe(x=>{
      this.data = x;
      console.log(this.data)
	  });
  }

}

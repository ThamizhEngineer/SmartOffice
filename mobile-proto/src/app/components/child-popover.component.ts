import { Component } from '@angular/core';
import { CommonService } from '../services/common.service';
import { Events, PopoverController } from '@ionic/angular';

@Component({
selector: 'app-child-popover',
template: ` <ion-list no-padding *ngFor="let kid of myKids" lines="full">
                <ion-item (click)="selectChild(kid)">{{kid.studentName}}</ion-item>
            </ion-list>
        `
})

export class ChildPopoverComponent{


constructor(private commonService: CommonService,
            private events: Events,
            private popoverCtrl: PopoverController) { }

	ionViewWillEnter(){
		
	}

}
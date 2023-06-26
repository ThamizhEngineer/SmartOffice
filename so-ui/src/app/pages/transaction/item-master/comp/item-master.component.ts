import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemMasterService } from '../item-master.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: '',
    templateUrl: 'item-master.component.html'
})

export class ItemMasterComponent implements OnInit {
    items: any=[];
    upage :number = 1;
    upageSize :number = 10;
    saveMsg: any;
    itemCode: any="";
    referenceNumber: any="";
    forPurchase: any="";
    hsnSacCode: any="";
    itemName: any="";
    constructor(private router:Router,private service:ItemMasterService,private modalService: NgbModal) { }

    ngOnInit() {
        this.service.getItemMaster().subscribe(x=>this.items=x);
     }

     deleteRow(id?: any){
		this.service.deleteItemMaste(id).subscribe(x=>{
            console.log(x);
            this.saveMsg = {'type': 'success', 'text': "Applicant Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }

        search() {
            this.service.advanceSearch(this.itemCode, this.referenceNumber, this.forPurchase, this.hsnSacCode, this.itemName).subscribe(x => {
                this.items = x;
            })
        }
    
        reset() {
            this.itemCode = null; this.referenceNumber = null; this.forPurchase = null; this.hsnSacCode = null; this.itemName = null;
            this.search();
        }
}
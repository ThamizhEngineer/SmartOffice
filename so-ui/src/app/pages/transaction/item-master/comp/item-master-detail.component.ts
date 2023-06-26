import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ItemMasterService } from '../item-master.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Item } from '../vo/item';

@Component({
    selector: '',
    templateUrl: 'item-master-detail.component.html'
})

export class ItemMasterDetailComponent implements OnInit {
    item : Item;
    saveMsg: any;
    forPurchase : boolean;
    constructor(private router:Router,private service:ItemMasterService,private modalService: NgbModal,private activatedRoute:ActivatedRoute,) { }

    ngOnInit()  {
        this.item = new Item();
        // this.payment.paymentLine=[new Item];
        this.activatedRoute.params.subscribe(x=>{
            console.log(x)
            if(x.id!=null){
                this.service.getItemMaster().subscribe(results=>{
                    console.log(results)
                    for(let items of results){
                        console.log(items)
                        if(items.id==x.id){
                            if(items.forPurchase=="N"){
                                items.forPurchase=null;
                            }
                            if(items.forSale=="N"){
                                items.forSale=null;
                            }
                            this.item=items;
                            console.log(this.item)
                        }
                    }
                });
            }           
        });
       
     }

	save(){
        this.item.forPurchase=(this.item.forPurchase?"Y":"N");
        this.item.forSale=(this.item.forSale?"Y":"N");

		if(this.item.id){
			this.service.updateItemMaster(this.item.id, this.item).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Manufacturer Updated Successfully"};
                this.router.navigate(['/transaction/item-master/']);
			});	
		}
		else{
			this.service.createItemMaster(this.item).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "Manufacturer Created Successfully"};
                this.router.navigate(['/transaction/item-master/']);
			});	
		}
    }
    
// checkValue(event: any){
//    console.log(event);
//    if(event=="Y"){
//        document.getElementsByName("forPurchase");
//    }
// }
    }
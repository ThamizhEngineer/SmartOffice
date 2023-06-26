import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{VendorService} from '../vendor.service';
@Component({
    selector:'vendor-list',
    templateUrl:'vendor-list.component.html'
})

export class VendorListComponent implements OnInit{
    vendors:any=[];
    country: any=[];
    vendorCode: any;
    vendorName: any;
    countryName: any;
    constructor(private router:Router,private vendorService:VendorService){

    }
    ngOnInit(){
        console.log(this.router.url);

this.vendorService.getAllVendors().subscribe(x=>{
this.vendors=x;
console.log(this.vendors);
})

this.getAllCountries();
    }

    getAllCountries() {
        this.vendorService.getAllCountries().subscribe(x => {
            this.country = x;
            console.log()
        });

    }

    navigateToAddPage(){
        this.router.navigateByUrl("vendor/new"); 
    }

    navigateToDetailPage(){
        this.router.navigateByUrl("vendor/view");   
    }

    navigateToEditPage(){
        this.router.navigateByUrl("vendor/edit");   
    }
    search() {
        this.vendorService.advanceSearch(this.vendorCode, this.vendorName, this.countryName).subscribe(x => {
            this.vendors = x;
        })
    }

    reset() {
        this.vendorCode = null; this.vendorName = null; this.countryName = null;
        // this.search();
        this.ngOnInit();
    }

}
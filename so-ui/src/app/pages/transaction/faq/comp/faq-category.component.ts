import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';
import { FAQService } from '../faq.service';
import { FAQ,FAQComment,FAQLike,FAQRefLink,FAQTag } from '../vo/faq';
import { FAQCategorye } from '../vo/faqCategorye';

@Component({
    selector: '',
    templateUrl: 'faq-category.component.html'
})

export class FAQCategoryComponent implements OnInit {

    @ViewChild('create') create: TemplateRef<any>;

    faqCategories:any=[];
    modalReference:any = null;
    faqCategory:FAQCategorye;
    user:User;
    isHr1:boolean=false;
    isDir:boolean=false;
    isAdmin:boolean=false;


    constructor(
        private service:FAQService,
        private _userService:UserService,
        private modalService: NgbModal
    ) { }

    ngOnInit() { 
        this.user = this._userService.getCurrentUser();   
        this.user.userGroupMappings.forEach(element => {
            if(element.isHrL1=='Y'||element.isHrL2=='Y'){              
                this.isHr1=true;                               
            }else if(element.isDir=='Y'){              
                this.isDir=true;                               
            }else if(element.isAdmin=='Y'){              
                this.isAdmin=true;                               
            }                       
        });     
        this.service.getAllFAQCategory().subscribe(x=>this.faqCategories=x);
    }

    createFaqCategory(id){
        this.faqCategory = new FAQCategorye();
        if(id!='new'){
            this.service.getAllFAQCategoryById(id).subscribe(x=>this.faqCategory=x);
        }
        this.modalReference = this.modalService.open(this.create, {size: 'lg'});
    }

    checkDuplicateName(){
        var response = this.faqCategories.filter((faqCategory:FAQCategorye)=>faqCategory.name==this.faqCategory.name);
        if(response.length!=0){
            this.faqCategory.dummyIsName=false
        }else{
            this.faqCategory.dummyIsName=true
        }

        console.log(this.faqCategory);
               
    }

    createFaq(){
        this.service.createFAQCategory(this.faqCategory).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit;
        })
    }

    updateFaq(){
        this.service.updateFAQCategory(this.faqCategory).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit;
        })
    }
}
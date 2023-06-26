import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';
import { FAQService } from '../faq.service';
import { FAQCategorye } from '../vo/faqCategorye';
import { FAQ,FAQComment,FAQLike,FAQRefLink,FAQTag } from '../vo/faq';

@Component({
    selector: '',
    templateUrl: 'faq-list.component.html'
})

export class FAQListComponent implements OnInit {
   
    faqCategory:FAQCategorye
    faqs:[FAQ]
    faq:FAQ
    modalReference:any = null;
    tags:any=[];
    user:User;
    isHr1:boolean=false;
    isDir:boolean=false;
    isAdmin:boolean=false;

    @ViewChild('create') create: TemplateRef<any>;

    constructor(
        private service:FAQService,
        private activatedRoute: ActivatedRoute,
        private modalService: NgbModal,
        private _userService:UserService,
        private router:Router
  
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
        this.faqCategory = new FAQCategorye();
        this.faqs = [new FAQ]
        this.activatedRoute.params.subscribe(params=>{
            this.service.getAllFAQCategoryById(params.id).subscribe(x=>{
                this.faqCategory=x
                this.faqs=x.faqs;
            });
        })      
        
        this.service.getAllTags().subscribe(x=>{
            x.forEach(element => {
             element.value=element.id   
             element.display=element.name   
            });
            this.tags=x
        });
    }

    createFaqCategory(id){
        this.faq = new FAQ();       
        this.modalReference = this.modalService.open(this.create, {size: 'lg'});
    }

    createFaq(){
        this.faq.mFaqCatId=this.faqCategory.id.toString();
        this.service.createFAQ(this.faq).subscribe(x=>{
            this.ngOnInit();
        })
    }

    newFaq(){
        this.router.navigate(['/faq/create-post','new',this.faqCategory.id]);
      }
    backToMain(){
        console.log("in back")
        this.router.navigate(['/faq/']);
        console.log("after back")
      }
}
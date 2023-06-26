import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';

import ClassicEditor from '@ckeditor/ckeditor5-build-classic';


import { FAQService } from '../faq.service';
import { FAQ, FAQComment, FAQLike, FAQRefLink, FAQTag } from '../vo/faq';

export interface AutoCompleteModel {
    value: any;
    display: string;
 }

@Component({
    selector: '',
    templateUrl: 'faq-detail.component.html',
})

export class FAQDetailComponent implements OnInit {

    faq: FAQ
    editor = ClassicEditor;
    data: string='';
    tags:any=[];
    viewType:string;
    user:User;
    faqComment:FAQComment
    isLiked:string='';

    constructor(
        private service: FAQService,
        private activatedRoute: ActivatedRoute,
        private userService:UserService,
        private route: Router

    ) { }

    ngOnInit() {
        this.faq = new FAQ();
        this.faq.faqTags = [new FAQTag];
        this.faq.faqTags.splice(0,1);
        this.faq.faqRefLinks = [new FAQRefLink]; 
        this.faq.faqComments = [new FAQComment];
        this.faqComment = new FAQComment();
        this.user = this.userService.getCurrentUser();       
        this.activatedRoute.params.subscribe(params => {
            this.viewType=params.view;
            if(params.id!=null){
                this.service.getFAQById(params.id).subscribe(x => {
                    x.faqTags.filter(element => {element.dummyTagId=element.mtagId});
                    this.faq = x
                    if(this.viewType=='view'){                                   
                        this.viewTypePrep();
                    }
                    this.data = atob(this.faq.post);
                });
            }else if(params.faqCatId!=null){
                this.faq.mFaqCatId=params.faqCatId;
            } 
        })

        this.service.getAllTags().subscribe(x=>{
            x.forEach(element => {
             element.value=element.id   
             element.display=element.name   
            });
            this.tags=x
        });
    }

    goToLink(url: string){
        window.open(url, "_blank");
    }
    viewTypePrep(){
        let like = new FAQLike();
        like = this.faq.faqLikes.filter(x=>x.createdBy==this.user.employeeId)[0];
        if(like!=null){
            this.isLiked=like.isLiked;
        }
    }
   
    onItemRemoved(event){
        for (let i = 0; i < this.faq.faqTags.length; i++) {
            if(this.faq.faqTags[i].tagName==event.tagName && this.faq.faqTags[i].dummyTagId==event.mtagId){
                this.faq.faqTags.splice(i,1);
            }
            
        }
    }

    onItemAdded(event){
        console.log(event);
        let tag = new FAQTag();
        tag.tagName=event.tagName;
        tag.dummyTagId=event.mtagId;
        if(event.mtagId>0){
            tag.mtagId=event.mtagId
        }
        this.faq.faqTags.push(tag);
    }
    save(){                    
        this.faq.post=null;    
        this.faq.faqComments=[]         
        this.faq.post = btoa((this.data));
        console.log(this.faq)
        this.service.createFAQ(this.faq).subscribe(x=>{
            this.ngOnInit();
            this.route.navigateByUrl('/my-space/appraisal/'); 
        })
    }
    update(){
        this.faq.post=null;             
        this.faq.post = btoa((this.data));
        console.log(this.faq)
        this.service.updateFAQ(this.faq.id,this.faq).subscribe(x=>{
            this.ngOnInit();
        })
    }

    addRows(){
        let refLink = new FAQRefLink()
        this.faq.faqRefLinks.push(refLink);
    }

    postFaqComment(){
        this.service.updateFAQComment(this.faq.id,this.faqComment).subscribe(x=>{
            this.faqComment = new FAQComment();
            this.ngOnInit();
        })
    }

    faqLike(isLiked){
        this.service.updateFAQLike(this.faq.id,isLiked).subscribe(x=>{
            this.ngOnInit();
        })
    }

}
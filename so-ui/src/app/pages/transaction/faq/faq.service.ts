import { Injectable } from '@angular/core';
import { Http, Response,ResponseContentType,Headers} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class FAQService {

    constructor(
        private http:Http,
        private commonService:CommonService
    ){}

    // FAQ Category
    getAllFAQCategory(){
        return this.http.get(environment.serviceApiUrl + 'transaction/faq-category', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getAllFAQCategoryById(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/faq-category/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    createFAQCategory(faqCategory){
        return this.http.post(environment.serviceApiUrl + 'transaction/faq-category',faqCategory, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateFAQCategory(faqCategory){
        return this.http.patch(environment.serviceApiUrl + 'transaction/faq-category/update',faqCategory, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    deleteFAQCategory(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/faq-category/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }

        // FAQ 

    getAllFAQ(){
        return this.http.get(environment.serviceApiUrl + 'transaction/faq', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getFAQById(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/faq/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    createFAQ(faq){
        return this.http.post(environment.serviceApiUrl + 'transaction/faq',faq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateFAQ(id,faq){
        return this.http.patch(environment.serviceApiUrl + 'transaction/faq/'+id,faq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateFAQComment(id,faqComment){
        return this.http.patch(environment.serviceApiUrl + 'transaction/faq/fqa-comments/'+id,faqComment, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateFAQLike(id,isLiked){
        let dummy
        return this.http.patch(environment.serviceApiUrl + 'transaction/faq/fqa-like/'+id+'/'+isLiked,dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    deleteFAQ(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/faq/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }
    deleteFAQComment(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/faq-comment/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }
    deleteFAQRefLink(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/faq-reflink/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }

    deleteFAQTag(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/faq-tag/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }

    // TAGS
    getAllTags(){
        return this.http.get(environment.serviceApiUrl + 'master/tag', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getTagById(id){
        return this.http.get(environment.serviceApiUrl + 'master/tag/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    createTag(tag){
        return this.http.post(environment.serviceApiUrl + 'master/tag',tag, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    deleteTagById(id){
        return this.http.delete(environment.serviceApiUrl + 'master/tag/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }
}
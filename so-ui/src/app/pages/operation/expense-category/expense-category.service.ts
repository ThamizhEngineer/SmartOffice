import { Injectable } from '@angular/core';
import { Http,Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from './../../../../environments/environment';
import { ExpenseCategory } from './vo/expense-category';

@Injectable()
export class ExpenseCategoryService {
    constructor(private http:Http,private commonService:CommonService) { }

    getExpenseSubCategory(){
        return this.http.get(environment.serviceApiUrl + 'master/expense-category/sub-category', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getCategory(){
        return this.http.get(environment.serviceApiUrl + 'master/expense-category', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getExpenseSubCategoryId(id: string) {
        return this.http.get(environment.serviceApiUrl+'master/expense-category/'+id+'/sub-category',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    addExpenseSubCategory(expenseSubCategory: ExpenseCategory){
        console.log(expenseSubCategory)
        return this.http.post(environment.serviceApiUrl+'master/expense-category/sub-category' ,expenseSubCategory,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
       
    }
    updateExpenseSubCategory(expenseSubCategory: ExpenseCategory,id){
        return this.http.patch(environment.serviceApiUrl+'master/expense-category/sub-category/'+id ,expenseSubCategory,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteExpenseSubCategory( id: any) {
        return this.http.delete(environment.serviceApiUrl + 'master/expense-category/'+id+'/sub-category',this.commonService.jwt()).map((response) => response);
    }

    advanceSearch(categoryName,subCategoryName) {
    var url = 'master/expense-category/search?' 

    if(categoryName!=null){
        url= url+'categoryName='+categoryName   
    } 
    if(subCategoryName!=null){
        url= url+'&&subCategoryName='+subCategoryName   
    }   
  
    return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

}
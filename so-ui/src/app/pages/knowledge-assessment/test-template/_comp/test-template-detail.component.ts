import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { TestPreparation, TestPrepSection, TestParticipant } from '../vo/TestPreparation';
import { DecimalPipe } from '@angular/common';
import{TestTemplateService} from '../test-template.service';
import { c } from '@angular/core/src/render3';
import { TestTemplate, TestTemplateCatagory } from '../vo/TestTemplate';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'test-template-detail',
  templateUrl: './test-template-detail.component.html',
  providers: [DecimalPipe]
})
export class TestTemplateDetailComponent implements OnInit {
    addScreen: boolean = false;
    questionBanks=[];
    mTestTemplateId:any;
    testtemplate:TestTemplate
    testTemplateCatagory:TestTemplateCatagory;
    testTemplateNames:any[];
    addTestTemplateCategoryCount=1;
    testCategories=[];
    saveMsg:any
    id:string;
    myGroup: FormGroup;
    questions=[];

    testTemplateName:FormControl;
    description:FormControl;
    duration:FormControl;
    // negativeMarking:FormControl;
    // marksPerQuestion:FormControl;
    totalQuestions:FormControl;
    passPercentage:FormControl;
    questionCount = 0;
    test: TestTemplateCatagory[];
    sample: TestTemplateCatagory[];
    // marksPerQuestion :any;

    searchCategory = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.testCategories : this.testCategories.filter(v => v.testCategoryName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    formatterCategory = (x:{testCategoryName: string}) =>x.testCategoryName;

	constructor(private router:Router,private route:ActivatedRoute,private testTemplateService:TestTemplateService) { }

	ngOnInit() {
        // this.marksPerQuestion='1';
        this.testtemplate = new TestTemplate();
        this.testTemplateService.getAllTestTemplate().subscribe(x=>{
            this.testTemplateNames=x;
        })
        this.testtemplate.testTemplateCatagory = new Array<TestTemplateCatagory>(); 

        if (this.route.params['_value']['_id'] === undefined) {
            this.addScreen = true;
        }
        
        if(this.addScreen){
            this.testtemplate.testTemplateCatagory =  [new TestTemplateCatagory]
            
            
        }
        else {
            this.route.params.switchMap((par:Params) => this.testTemplateService.getAllTestTemplateById(par['_id'])).subscribe(x => {
                this.testtemplate = x;
                this.objModify(this.testtemplate);
                this.mTestTemplateId=x.id;
                if(this.testtemplate.testTemplateCatagory.length<1){
                    this.testtemplate.testTemplateCatagory.push(new TestTemplateCatagory);
                }                                               
			});
        }

        this.testTemplateService.getAllTestCategory().subscribe(x=>{
            this.testCategories=x;
            console.log(this.testCategories)
        })
		this.validate();
        this.createForm();

        this.testTemplateService.getAllTestCategory().subscribe(x=>{

        this.testtemplate.testTemplateCatagory.forEach(row=>{
            console.log(row.marksPerQuestion)
            if(row.marksPerQuestion==null || row.marksPerQuestion=='0'){
                row.marksPerQuestion='1';
                console.log(row.marksPerQuestion)
            }
        })
                this.testtemplate.testTemplateCatagory.forEach(row=>{
                    this.testTemplateService.getByCategoryIdAndLevel(row.mTestCatagoryId,row.difficultyCode).subscribe(x=>{
                       
                        row.availableQuestions = x.length
                        console.log(x.length)
                    })                    
                });
           
            })
         
    }    

    
    

    // onSelectCategory(data, i){
    //     console.log(data)
    //     console.log(i)
    //     this.testCategories.forEach(cat=>{
    //         console.log(cat)
    //         if(data.mTestCatagoryId==cat.id){
    //             this.testtemplate.testTemplateCatagory[i].mTestCatagoryId=cat.id;
    //             this.testtemplate.testTemplateCatagory[i].mTestCatagoryName=cat.testCategoryName;
    //         }
    //     });
    //     console.log(this.testtemplate.testTemplateCatagory)

    // }


    onSelectLevel(row,index){
        this.testTemplateService.getByCategoryIdAndLevel(row.mTestCatagoryId,row.difficultyCode).subscribe(x=>{
            this.questions=x;
            this.testCategories.forEach(c=>{
                if(row.mTestCatagoryId==c.id){
                    this.testtemplate.testTemplateCatagory[index].mTestCatagoryId=c.id;
                    this.testtemplate.testTemplateCatagory[index].mTestCatagoryName=c.testCategoryName;
                    this.testtemplate.testTemplateCatagory[index].availableQuestions=this.questions.length;
                }
            });
        })
    }

    onChangeTotalQuestions(){
        if(this.testtemplate.totalQuestions==null || this.testtemplate.totalQuestions==undefined){
            this.testtemplate.totalQuestions=0;
        }else{
            let total=0;
        for(let list of this.testtemplate.testTemplateCatagory)
          if(list.totalQuestions!=null&&list.totalQuestions!=undefined){
              total=total+(list.totalQuestions*1);
              this.testtemplate.totalQuestions=total;
              console.log("total"+this.testtemplate.totalQuestions);
          }
        }
        this.objModify(this.testtemplate);
   }

    updateTemplate(){
        if(this.myGroup.valid){       
            console.log(this.testtemplate)   
            console.log(this.testtemplate.id)  
            this.testTemplateService.updateTemplate(this.testtemplate,this.testtemplate.id).subscribe(x=>{
                this.saveMsg ={
                    type: 'success', text: "Update successfully"
                }
                this.router.navigateByUrl("/recruitment/test-template/test-template-list");
            });
        }

    }

    navigateToListPage(){
        this.router.navigateByUrl("/recruitment/test-template/test-template-list");   
       }


       categorySelected($event, s:TestTemplateCatagory){
           console.log($event)
           console.log(s)
           s.mTestCatagoryId = $event.item.id;
           s.mTestCatagoryName = $event.item.testCategoryName;

       }
//        create(){
//         if(this.myGroup.valid){
//         this.applicant.jrId=this.jrId;
//         console.log(this.applicant);
//         this.service.addWebSiteApplicant(this.applicant).subscribe(x=>{
//             this.modalReference.close();
//             this.saveMsg = {'type': 'success', 'text': x.eligibleRemarks}
//         })
//     }
// }
createTemplate(){
    if(this.myGroup.valid){
        this.testtemplate.id=this.id;
        console.log(this.testtemplate);
        this.testTemplateService.createTemplate(this.testtemplate).subscribe(x=>{
            this.saveMsg ={
                type: 'success', text: "Added successfully"
            }
            this.router.navigateByUrl("/recruitment/test-template/test-template-list");
        });
    }
}
    addRow(){
        this.addTestTemplateCategoryCount++;
        let data= new TestTemplateCatagory();
        this.testtemplate.testTemplateCatagory.push(data);
        console.log(this.testtemplate.testTemplateCatagory)

    }
    delRow(item){
      console.log(item)
      this.testtemplate.testTemplateCatagory.splice(item,1);
      this.onChangeTotalQuestions();
     
  
  }
  validate(){
    this.testTemplateName = new FormControl('', [Validators.required]);
    this.description = new FormControl('', [Validators.required]);
    this.duration = new FormControl('', [Validators.required]);
    // this.negativeMarking = new FormControl('', [Validators.required]);
    // this.marksPerQuestion = new FormControl('', [Validators.required,Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);
    // this.marksPerQuestion = new FormControl('', [Validators.required]);
    this.totalQuestions = new FormControl('', [Validators.required]);
    this.passPercentage = new FormControl('', [Validators.required]);
}
createForm(){
    this.myGroup = new FormGroup({
        testTemplateName:this.testTemplateName,
        description:this.description,
        duration:this.duration,
        // negativeMarking:this.negativeMarking,
        // marksPerQuestion:this.marksPerQuestion,
        totalQuestions:this.totalQuestions,
        passPercentage:this.passPercentage
    });		  
}
objModify(testtemplate:TestTemplate){
    this.myGroup.patchValue({
        testTemplateName: testtemplate.testTemplateName,
        description: testtemplate.description,
        duration: testtemplate.duration,
        // negativeMarking: testtemplate.negativeMarking,
        // marksPerQuestion: testtemplate.marksPerQuestion,
        totalQuestions: testtemplate.totalQuestions,
        passPercentage: testtemplate.passPercentage
    });
}
}


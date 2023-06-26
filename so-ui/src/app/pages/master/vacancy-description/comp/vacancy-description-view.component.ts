import { Component, OnInit } from '@angular/core';
import { Route,ActivatedRoute,Params } from '@angular/router'
import { VacancyDecriptionService } from '../vacancy-description.service';
import { VacancyDescription,VacancyDescriptionSkill } from '../../../vo/vacancy-description';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';


@Component({
    selector: '',
    templateUrl: 'vacancy-description-view.component.html'
})

export class VacancyDescriptionViewComponent implements OnInit {
    
    vacancyDesc:VacancyDescription;
    myGroup: FormGroup;
    errorMsg:any;
    saveMsg:any;
    summary:FormControl;
    designation:FormControl;
    yearsOfExp:FormControl;
    location:FormControl;
    salary:FormControl;
    role:FormControl;
    responsibility:FormControl;
    qualification:FormControl;
    experience:FormControl;

    constructor(
        private service:VacancyDecriptionService,
        private route:Router,
        private activedRouter:ActivatedRoute 
    ) { }

    ngOnInit() {

        this.vacancyDesc = new VacancyDescription();
        this.vacancyDesc.vacancyDescriptionSkills = [new VacancyDescriptionSkill];

        if (this.activedRouter.params['_value']['id']){
            this.activedRouter.params.switchMap((params: Params) => this.service.getVacancyDescriptionById(params['id'])).
            subscribe(x=>{
                this.vacancyDesc=x;
            });   
        }else{
            let jdfskill = new VacancyDescriptionSkill()
            let jdbskill = new VacancyDescriptionSkill()
            jdbskill.type='BEHAVIORAL';
            jdfskill.type='FUNCTIONAL';
            this.vacancyDesc.vacancyDescriptionSkills.push(jdbskill,jdfskill);
        }       
        this.validate();
		this.createForm();
     }
     
     get getBehavioralSkills(){
         if(this.vacancyDesc && this.vacancyDesc.vacancyDescriptionSkills){
             return this.vacancyDesc.vacancyDescriptionSkills.filter( x => x.type == 'BEHAVIORAL');
         }
         else
            return [];
     }
     
     get getFunctionalSkills(){
        if(this.vacancyDesc && this.vacancyDesc.vacancyDescriptionSkills){
            return this.vacancyDesc.vacancyDescriptionSkills.filter( x => x.type == 'FUNCTIONAL');
        }
        else
           return [];
    }

    addVacancyDescription(){     
        this.service.addVacancyDescription(this.vacancyDesc).subscribe(x=>{
            this.route.navigateByUrl("/recruitment/job-description/");
        },
        error => {                       
            this.errorMsg = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }
    updateVacancyDescription(){               
        this.service.updateVacancyDescription(this.vacancyDesc.id,this.vacancyDesc).subscribe(x=>{
            this.route.navigateByUrl("/recruitment/job-description/");
        },
        error => {                       
            this.errorMsg = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }

    addJdSkill(type){
         let func = new VacancyDescriptionSkill();
         func.type=type;
         this.vacancyDesc.vacancyDescriptionSkills.push(func);
     }
     
    deleteRow(id,type){
        for(let i = 0; i < this.vacancyDesc.vacancyDescriptionSkills.length; ++i){
            if (this.vacancyDesc.vacancyDescriptionSkills[i].id === id&&this.vacancyDesc.vacancyDescriptionSkills[i].type==type) {
                this.vacancyDesc.vacancyDescriptionSkills.splice(i,1);
            }
        }
    }
    deleteFunctionalRow(id,type){
        for(let i = 0; i < this.vacancyDesc.vacancyDescriptionSkills.length; ++i){
            if (this.vacancyDesc.vacancyDescriptionSkills[i].id === id&&this.vacancyDesc.vacancyDescriptionSkills[i].type==type) {
                this.vacancyDesc.vacancyDescriptionSkills.splice(i,1);
            }
        }
    }

    validate(){
        this.summary = new FormControl('', [Validators.required]);
        this.designation = new FormControl('', [Validators.required]);
        this.yearsOfExp = new FormControl('', [Validators.required]);
        this.location = new FormControl('', [Validators.required]);
        this.salary = new FormControl('', [Validators.required]);
        this.role = new FormControl('', [Validators.required]);
        this.responsibility = new FormControl('', [Validators.required]);
        this.qualification = new FormControl('', [Validators.required]);
        this.experience = new FormControl('', [Validators.required]);
    }
    createForm(){
        this.myGroup = new FormGroup({
            summary:this.summary,
            designation:this.designation,
            yearsOfExp:this.yearsOfExp,
            location:this.location,
            salary:this.salary,
            role:this.role,
            responsibility:this.responsibility,
            qualification:this.qualification,
            experience:this.experience
        });		
    }
    objModify(vacancyDesc:VacancyDescription){
        this.myGroup.patchValue({
            summary: vacancyDesc.summary,
            designation: vacancyDesc.designation,
            yearsOfExp: vacancyDesc.yearsOfExp,
            location: vacancyDesc.location,
            salary: vacancyDesc.salary,
            role: vacancyDesc.role,
            responsibility: vacancyDesc.responsibility,
            qualification: vacancyDesc.qualification,
            experience: vacancyDesc.experience
        });
    }    
}
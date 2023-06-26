import { Component, OnInit, ViewChild } from '@angular/core';
import { VacancyRequestService } from '../vacancy-request.service';
import { VacancyRequest } from '../../../vo/vacancy-request';
import { VacancyDescription } from '../../../vo/vacancy-description';
import { ActivatedRoute, Params, Router } from '@angular/router'
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';


// import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
// import { Observable, Subject } from 'rxjs';
// import { debounceTime, distinctUntilChanged, filter, map } from 'rxjs/operators';
// import { merge } from 'rxjs/observable/merge';

import {NgbTypeahead} from '@ng-bootstrap/ng-bootstrap';
import {Observable, Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map, merge} from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';



@Component({
    selector: '',
    templateUrl: 'vacancy-request-view.component.html'
})

export class VacancyRequestComponent implements OnInit {

    @ViewChild('instance', { static: true } as any) instance: NgbTypeahead;
    focus$ = new Subject<string>();
    click$ = new Subject<string>();
    errorMsg:any;
    saveMsg:any;

    profileAC = (text$: Observable<string>) => 
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            merge(this.focus$),
            merge(this.click$.pipe(filter(() => !this.instance.isPopupOpen()))),
            map(term => (term === '' ? this.vacancyDescList
            : this.vacancyDescList.filter(v => v.summary.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );

    formatter = (x: { summary: string }) => x.summary;

    vacancyReq: VacancyRequest;
    office: any = [];
    vacancyDesc: VacancyDescription;
    vacancyDescList: any = [];

    dropdownSettings = {};
    selectedItems: any = [];

    view: string;

    myGroup: FormGroup;

    summary: FormControl;
    vacancyCount: FormControl;
    remarks: FormControl;

    constructor(
        private service: VacancyRequestService,
        private route: Router,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit() {
        this.vacancyDesc = new VacancyDescription();
        this.vacancyReq = new VacancyRequest();

        this.service.getVacancyDescriptions().subscribe(x => {
            this.vacancyDescList = x;
        })


        this.activatedRoute.queryParams.subscribe(x => {
            this.view = x.view;
            if (x.id == null) {
                this.vacancyReq.summary = null;
            } else {
                this.service.getVacancyrequestById(x.id).subscribe(x => {
                    this.vacancyReq = x;
                    console.log(x);
                    this.getVacancyDesc(this.vacancyReq.vdId);
                });
            }

        })
        this.validate();
        this.createForm();
    }

    addVacancyRequest() {
        this.service.addVacancyrequests(this.vacancyReq).subscribe(x => {
            this.route.navigateByUrl('/recruitment/job-request');
        })
    }


    updateVacancyRequest(action) {
        this.service.updateVacancyrequests(this.vacancyReq.id, action, this.vacancyReq).subscribe(x => {
            if (this.view == 'HR') {
                this.route.navigateByUrl('/recruitment/job-request');
            } else {
                this.route.navigateByUrl('/director/job-request');
            }

        },
        error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }

    vacancyDescSelect($event) {
        console.log($event)
        this.vacancyReq.vdId = $event.item.id;
        this.vacancyReq.summary = $event.item.summary;
        this.getVacancyDesc(this.vacancyReq.vdId);

    }


    get getBehavioralSkills() {
        if (this.vacancyDesc && this.vacancyDesc.vacancyDescriptionSkills) {
            return this.vacancyDesc.vacancyDescriptionSkills.filter(x => x.type == 'BEHAVIORAL');
        }
        else
            return [];
    }

    get getFunctionalSkills() {
        if (this.vacancyDesc && this.vacancyDesc.vacancyDescriptionSkills) {
            return this.vacancyDesc.vacancyDescriptionSkills.filter(x => x.type == 'FUNCTIONAL');
        }
        else
            return [];
    }

    getVacancyDesc(id) {
        this.service.getVacancyDescriptionById(id).subscribe(des => {
            this.vacancyDesc = des;
            if (this.vacancyReq.location == null && this.vacancyReq.salary == null && this.vacancyReq.yearsOfExp == null) {
                this.vacancyReq.location = this.vacancyDesc.location;
                this.vacancyReq.salary = this.vacancyDesc.salary;
                this.vacancyReq.yearsOfExp = this.vacancyDesc.yearsOfExp;
            }

        });
    }

    validate() {
        this.summary = new FormControl('', [Validators.required]);
        this.vacancyCount = new FormControl('', [Validators.required]);
        this.remarks = new FormControl('', [Validators.required]);
    }
    createForm() {
        this.myGroup = new FormGroup({
            summary: this.summary,
            vacancyCount: this.vacancyCount,
            remarks: this.remarks
        });
    }
    objModify(vacancyReq: VacancyRequest) {
        this.myGroup.patchValue({
            summary: vacancyReq.summary,
            vacancyCount: vacancyReq.vacancyCount,
            remarks: vacancyReq.remarks
        });
    }

}
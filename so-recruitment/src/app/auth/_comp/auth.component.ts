import { Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef, TemplateRef } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import * as CryptoJS from 'crypto-js';

import { AuthenticationService } from "../_services/authentication.service";
import { AlertService } from "../_services/alert.service";
import { UserService } from "../_services/user.service";
import { AlertComponent } from "./alert.component";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Applicant } from '../../pages/my-space/vo/applicant';
import { ApplicantService } from '../../pages/my-space/applicant/applicant.service';


@Component({
    selector: "auth-login",
    templateUrl: './login.component.html',
    styleUrls: ['./login.css']
})

export class AuthComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;
    isRecover: boolean = false;
    modalReference: any = null;
    response: any = {};
    applicant: Applicant;
    message: string;

    myGroup: FormGroup;
    emailForm: FormGroup;
    emails: FormControl;
    fName: FormControl;
    lName: FormControl;
    email: FormControl;
    mNumber: FormControl;
    gender:FormControl;
    data: any = [];
    status: string;

    // Encp
    title = 'sosmartoffice123';
    applicantionCode: string = "recruitment";


    @ViewChild('alertSignin', { read: ViewContainerRef }) alertSignin: ViewContainerRef;
    @ViewChild('alertSignup', { read: ViewContainerRef }) alertSignup: ViewContainerRef;
    @ViewChild('alertForgotPass', { read: ViewContainerRef }) alertForgotPass: ViewContainerRef;
    @ViewChild('createUser') createUser: TemplateRef<any>;
    @ViewChild('createPassword') createPassword: TemplateRef<any>;

    constructor(private _router: Router,
        private _userService: UserService,
        private _route: ActivatedRoute,
        private _authService: AuthenticationService,
        private _alertService: AlertService,
        private cfr: ComponentFactoryResolver,
        private modalService: NgbModal,
        private applicantService: ApplicantService
    ) {
    }

    ngOnInit() {
        this.model.remember = true;
        // get return url from route parameters or default to '/'
        this.returnUrl = this._route.snapshot.queryParams['returnUrl'] || '/index';
        this._router.navigate([this.returnUrl]);
        this.validate();
        this.createForm();
        this.valid();
        this.crForm();
        console.log(this.model)
        this.applicant = new Applicant();
        this._authService.healthService().subscribe(x => {
            console.log("inside", x);
            this.data = x;
            console.log("data", this.data);
            this.data.forEach(element => {
                if (element.status == 'DOWN') {
                    this.status = 'Error';
                    console.log(this.status)
                }
            });
        })
    }

    signin() {
        var encryptedPassword = this.encryptString(this.model.password);
        var encryptedApplicationCode =  this.encryptString(this.applicantionCode);
        // this.model.password = encryptedPassword;

        // console.log(this.commonService.getClientIpAddress());
        this.loading = true;
        this._authService.login(this.model.email, encryptedPassword, encryptedApplicationCode)
            .subscribe(
                data => {
                    this.loading = false;
                    if(data.attList.loginSuccess == "false"){
                        this.showAlert('alertSignin');
                        this._alertService.error(data.attList.errorMessage);
                        return false;
                    } 
                    this._router.navigate([this.returnUrl]);
                },
                error => {
                    let e = error._body;
                    let displayMsg = ''; 
                    let msg = typeof e == 'object' ? e : JSON.parse(e); 
                    if(msg.message){
                        displayMsg = msg.message;
                    }
                    else{ 
                        displayMsg = msg.status+" - System Error";
                    }
                    this.showAlert('alertSignin');
                    this._alertService.error(displayMsg);
                    this.loading = false;
                }
            );
    }

    private encryptString(x) {
        // CryptoJS.AES.encryp
        const key = CryptoJS.enc.Utf8.parse(this.title);

        const ecryptedString = CryptoJS.AES.encrypt(x, key, {
            keySize: 16,
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        }).toString();
        return ecryptedString;
    }

    showAlert(target) {
        this[target].clear();
        let factory = this.cfr.resolveComponentFactory(AlertComponent);
        let ref = this[target].createComponent(factory);
        ref.changeDetectorRef.detectChanges();
    }

    recoverUname: string;
    recoverPwd() {
        this._authService.recover(this.recoverUname).subscribe(data => {
            this.showAlert('alertSignin');
            this._alertService.success(data.message);
        },
            error => {
                let e = error._body;
                let msg = typeof e == 'object' ? e : JSON.parse(e);
                msg = msg.message ? msg.message : msg;

                this.showAlert('alertSignin');
                this._alertService.error(msg);
            }
        );
    }

    validate() {
        this.fName = new FormControl('', [Validators.required]);
        this.lName = new FormControl('', [Validators.required]);
        this.email = new FormControl('', [Validators.required]);
        this.gender = new FormControl('', [Validators.required]);
        this.mNumber = new FormControl('', [Validators.required, Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);
    }

    valid() {
        this.emails = new FormControl('', [Validators.required]);
    }

    createForm() {
        this.myGroup = new FormGroup({
            fName: this.fName,
            lName: this.lName,
            email: this.email,
            mNumber: this.mNumber,
            gender:this.gender
        });
    }

    crForm() {
        this.emailForm = new FormGroup({
            emails: this.emails
        });
    }
    objModify(applicant: Applicant) {
        this.myGroup.patchValue({
            fName: applicant.firstName,
            lName: applicant.lastName,
            email: applicant.contactEmailId,
            mNumber: applicant.contactMobileNo,
            gender:applicant.gender
        });
    }
    objModifye(applicant: Applicant) {
        this.emailForm.patchValue({
            emails: applicant.contactEmailId
        });
    }

    createUsers() {
        // console.log("In Function",this.applicant)
        this.modalReference = this.modalService.open(this.createUser, { size: 'lg' });
    }

    createPasswd() {
        this.modalReference = this.modalService.open(this.createPassword, { size: 'lg' });
    }

    saveUser(app) {
        if (app.isExperienced == true) {
            this.applicant.isExperienced = "Y";
        }
        if (app.isExperienced == false) {
            this.applicant.isExperienced = "N";
        }
        console.log(this.applicant)
        this.applicantService.createApplicantLogin(this.applicant).subscribe(x => {
            this.response = x;
            this.message = this.response._body;
        })
    }

    forgotPswd(mail) {
        console.log(mail)
        this.applicantService.forgotUserNamePwd(mail).subscribe(x => {
            this.response = x;
            // console.log(this.response)
            // console.log(this.response._body)
            this.message = this.response._body;
            console.log(this.message)
        })
    }
}
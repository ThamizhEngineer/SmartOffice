import { Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef, TemplateRef } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import * as CryptoJS from 'crypto-js';

import { AuthenticationService } from "../_services/authentication.service";
import { AlertService } from "../_services/alert.service";
import { UserService } from "../_services/user.service";
import { AlertComponent } from "./alert.component";

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../shared/common/common.service';
import { Employee } from "../../pages/vo/employee";

@Component({
    selector: "auth-login",
    templateUrl: './login.component.html',
    styleUrls: ['./login.css']
})

export class AuthComponent implements OnInit {
    model: any = {};
    loading = false;
    response: any = {};
    message: string;
    employee: Employee;
    returnUrl: string;
    isRecover: boolean = false;
    modalReference: any;
    clic: boolean = false;
    data: any = [];
    result: string = "success";
    public url: string = "./assets/data/healthcheck.json";
    status: string;
    applicantionCode: string = "smart-office";


    // Encp
    title = 'sosmartoffice123';

    @ViewChild('alertSignin', { read: ViewContainerRef }) alertSignin: ViewContainerRef;
    @ViewChild('alertSignup', { read: ViewContainerRef }) alertSignup: ViewContainerRef;
    @ViewChild('alertForgotPass', { read: ViewContainerRef }) alertForgotPass: ViewContainerRef;
    @ViewChild('modalContent') modalContent: TemplateRef<any>;
    @ViewChild('createPassword') createPassword: TemplateRef<any>;
    constructor(private _router: Router,
        private _userService: UserService,
        private _route: ActivatedRoute,
        private modalService: NgbModal,
        private _authService: AuthenticationService,
        private _alertService: AlertService,
        private commonService: CommonService,
        private cfr: ComponentFactoryResolver, private modal: NgbModal) {
    }

    ngOnInit() {
        this.model.remember = true;
        // get return url from route parameters or default to '/'
        this.returnUrl = this._route.snapshot.queryParams['returnUrl'] || '/index';
        this._router.navigate([this.returnUrl]);
        this.employee = new Employee();
        this._authService.healthCheckService().subscribe(x=>{  
                this.data = x; 
                this.data.forEach(element => {
                    if (element.status == 'DOWN') {
                        this.status = 'Error';
                        console.log(this.status)
                    }
                }); 
        },error=>{
            console.log(error)
            this.status = 'Error';
        })
       

    }

    userData: any;
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
                    if (data.acceptedAgmt == 'N') {
                        this.userData = data;
                        this.modalReference = this.modal.open(this.modalContent, { size: 'lg' });
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


    accept() {
        this.userData.acceptedAgmtLocation = this.commonService.getClientIpAddress();
        this._authService.acceptTerms(this.userData).subscribe(x => {
            localStorage.setItem('currentUser', JSON.stringify(this.userData));
            console.log(this.commonService.getClientIpAddress());
            this._router.navigate([this.returnUrl]);
            this.modalReference.close();
        });
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
    createPasswd() {
        this.modalReference = this.modalService.open(this.createPassword, { size: 'lg' });
    }
    forgotPswd(mail) {
        console.log(mail)
        this.clic = true;
        console.log(this.clic)
        this._authService.forgotPwd(mail).subscribe(x => {
            this.response = x;
            // console.log(this.response)
            // console.log(this.response._body)
            this.message = this.response._body;

            console.log(this.message)
        });



    }

}
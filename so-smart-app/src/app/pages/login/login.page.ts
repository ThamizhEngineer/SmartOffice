import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UtilsService } from 'src/app/services/utils.service';
import * as CryptoJS from 'crypto-js'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  model: any = {};
  users: any;
  passwordType: string;

  // Encp
  title = 'sosmartoffice123';
  applicantionCode: string = "smart-office-mobile";


  constructor(private router: Router, private utilsService: UtilsService, private auth: AuthenticationService) { }

  ngOnInit() {
    this.model.email = "";
    this.model.password = "";
  }

  login() {
    this.utilsService.presentLoader().then((x) => {
      this.loginUser();
    });
  }

  loginUser() {
    var encryptedPassword = this.encryptString(this.model.password);
    var encryptedApplicationCode = this.encryptString(this.applicantionCode);
    this.model.password = encryptedPassword;

    this.auth.loginUser(this.model.email, this.model.password, encryptedApplicationCode).subscribe(x => {
      // this.event.publish('currentUser',x);
      if(x["attList"].loginSuccess =="false"){
        let message = this.utilsService.getServiceStatus(x["attList"].errorMessge);
        this.utilsService.dismissLoader();
        this.utilsService.presentAlert(message);

      }
      else{
        this.utilsService.setCurrentUser(x); // used for validation in app.component
        this.utilsService.dismissLoader();
        this.redirect();
      }
    }, err => {
      let message = this.utilsService.getServiceStatus(err.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
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

  redirect() {
    this.router.navigate(['home/dashboard']);
  }
  forgotPswd() {
    this.router.navigate(['/forgot-password']);
  }

}

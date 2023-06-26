import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CommonService } from 'src/app/services/common.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  model:any={};
  users:any;
  passwordType:string;

  constructor(
    private authService: AuthenticationService,
    private commonService: CommonService,
    private router: Router
    ) { }

  ngOnInit() {

    this.model.email="";
    this.model.password="";
   
  }

  login(){
    this.commonService.presentLoader().then((x)=>{
      this.loginUser();
    });
  }

  loginUser(){
    this.authService.loginUser(this.model.email,this.model.password).subscribe(x=>{
      this.commonService.dismissLoader();
      this.redirect();
    },err=>{
      let message = this.commonService.getServiceStatus(err.status);
      this.commonService.dismissLoader();
      this.commonService.presentAlert(message);
    });
  }
   
  redirect() {
     this.router.navigate(['/dashboard']);
  }

  normalRedirect(){
    this.router.navigate(['/home']);
  }

  showPassword(){
    if(this.passwordType === 'text'){
      this.passwordType = 'password';
    }else{
      this.passwordType = 'text';
    }
  }
  redirectToSignUp() {
    this.router.navigate(['/auth/signup']);
 }
 redirectToForgot() {
  this.router.navigate(['/auth/forgot']);
}
}

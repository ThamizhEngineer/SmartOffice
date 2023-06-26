import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UtilsService } from 'src/app/services/utils.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.page.html',
  styleUrls: ['./forgot-password.page.scss'],
})
export class ForgotPasswordPage implements OnInit {
  model: any = {};
  users: any;
  constructor(private router:Router,public navCtrl: NavController, public _authenticationService: AuthenticationService, public _utilsService: UtilsService) { }

  ngOnInit() {
  }

  recoverPswd(){
    console.log(this.model.email)
    this._utilsService.presentLoader().then((x)=>{
      this.recoverPwd(this.model.email);
    });
  }


  recoverPwd(e) {
    this._authenticationService.recover(e).subscribe(x=>{
      console.log(x)
      this._utilsService.presentAlertPositive("Password has been sent to "+e);    
      this._utilsService.dismissLoader();
      this.redirect();
    },err=>{
      let message = this._utilsService.getServiceStatus(err.status);
      this._utilsService.dismissLoader();
      this._utilsService.presentAlert(message);    
    });
  }

  redirect(){
    this.router.navigate(['/login']);
  }

}

import { Injectable } from '@angular/core';
import { Platform } from '@ionic/angular';
import { switchAll } from 'rxjs/operators';
import { Network } from '@ionic-native/network/ngx';
import { LoadingController, ToastController, AlertController, Events } from '@ionic/angular';

export enum ConnectionStatusEnum {
  Online,
  Offline
}

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  loading:any;
  message: string;
  previousStatus:any;

  constructor(private platform: Platform, private loadingCtrl: LoadingController,
              private alertCtrl: AlertController,
              private toastCtrl: ToastController,
              private network: Network, private eventCtrl: Events) { 
				 this.previousStatus = ConnectionStatusEnum.Online;
			  }
             
	public initializeNetworkEvents(): void {
		this.network.onDisconnect().subscribe(() => {
			if (this.previousStatus === ConnectionStatusEnum.Online) {
				this.eventCtrl.publish('network:offline');
			}
			this.previousStatus = ConnectionStatusEnum.Offline;
		});
		this.network.onConnect().subscribe(() => {
			if (this.previousStatus === ConnectionStatusEnum.Offline) {
				this.eventCtrl.publish('network:online');
			}
			this.previousStatus = ConnectionStatusEnum.Online;
		});
	}
	
	  async presentLoader() {
		const loading = await this.loadingCtrl.create({
		  spinner: "crescent",
		  message: 'Please wait...',
		  translucent: true
		});
		return await loading.present();
	  }

	  async dismissLoader(){
		await this.loadingCtrl.dismiss();
	  }

	  async presentToast(message) {
		const toast = await this.toastCtrl.create({
		  message: message,
		  duration: 2000
		});
		toast.present();
	  }

	  async presentClosableToast(message:string) {
		const toastData = {
		  message: message,
		  duration: 2000,
		  position: 'bottom',
		  showCloseButton: true,
		  closeButtonText: 'OK'
		};
		this.showToast(toastData);
	  }  

	  private async showToast(data:any){
		this.toastCtrl ? this.toastCtrl.dismiss(data) : false;
		const toast = await this.toastCtrl.create(data);
		return await toast.present();
	  }

	  async presentAlert(subHeader){
		const alert = await this.alertCtrl.create({
		  header: 'Error',
		  subHeader: subHeader,
		  buttons: ['Ok']
		});
		return await alert.present();
	  }

	  getServiceStatus(statusCode): string{
		switch(statusCode){
		  case 0:
			this.message = 'Server Connection Error';
			break;
		  case 401:
			this.message = 'Incorrect Username/Password';
			break;
		  case 500:
			this.message = 'Internal Server Error';
			break;
		  default:
			this.message = 'Incorrect Username/Password'
			break
		}
		return this.message;
	  }

	  getData(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		console.log("currentUser-"+currentUser)
	}

	formatDate(date) {
		let d = new Date(date),
				month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }

}

import { Injectable, RendererFactory2, Inject, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { LoadingController, AlertController } from '@ionic/angular';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Downloader, DownloadRequest, NotificationVisibility } from '@ionic-native/downloader/ngx';
import { File } from '@ionic-native/file/ngx'; 
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  message: string;
  renderer: Renderer2;
  documentApiUrl: string = environment.baseUrl + environment.documentApiUrl;
  applicationCode: string = "smart-office-mobile";
  private currentUser = new Subject<any>();
  constructor(private file: File, private downloader: Downloader, private http: HttpClient, rendererFactory: RendererFactory2, @Inject(DOCUMENT) private document: Document, public loadingController: LoadingController, private alertCtrl: AlertController) {
    this.renderer = rendererFactory.createRenderer(null, null);
  }

  setCurrentUser(data: any) {
    this.currentUser.next(data);
  }

  getCurrentUserChange(): Subject<any> {
    return this.currentUser;
}
  enableDarkTheme(shouldEnable) {
    document.body.classList.toggle("dark", shouldEnable);
  }

  enableDarkMode() {
    this.renderer.addClass(this.document.body, 'dark');
  }

  enableLightMode() {
    this.renderer.removeClass(this.document.body, 'dark');

  }

  async presentLoader() {
    const loading = await this.loadingController.create({
      spinner: "bubbles",
      message: 'Please wait...',
      translucent: true,
      showBackdrop: true
    });
    return await loading.present();
  }

  async dismissLoader() {
    await this.loadingController.dismiss();
  }

  async presentAlert(subHeader) {
    const alert = await this.alertCtrl.create({
      header: 'Error',
      subHeader: subHeader,
      buttons: ['Ok']
    });
    return await alert.present();
  }

  async presentAlertPositive(subHeader) {
    const alert = await this.alertCtrl.create({
      header: 'Message',
      subHeader: subHeader,
      buttons: ['Ok']
    });
    return await alert.present();
  }


  getServiceStatus(statusCode): string {
    switch (statusCode) {
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

  getErrorStatus(statusCode): string {
    switch (statusCode) {
      case 0:
        this.message = 'Server Connection Error';
        break;
      case 401:
        this.message = 'Unauthorized error';
        break;
      case 404:
        this.message = 'Not Found';
        break;
      case 400:
        this.message = 'Bad Request';
        break;
      case 408:
        this.message = 'Request Timeout';
        break;
      case 500:
        this.message = 'Internal Server Error';
        break;
      default:
        this.message = 'Network error'
        break
    }
    return this.message;
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


  getFileFromAPi(docId: any) {
    let _currentUser = JSON.parse(localStorage.getItem('currentUser'));
    let headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', _currentUser.appToken);
    return this.http.get(this.documentApiUrl + 'dm/' + docId, { responseType: 'arraybuffer', headers: headers });
  }

  // NOTE: Downloader to be used, as angular downloader not working as expected
  downloadFile(docId) {
    let _currentUser = JSON.parse(localStorage.getItem('currentUser'));

    var request: DownloadRequest = {
      uri: this.documentApiUrl + 'dm/' + docId,
      title: 'pay-slip-' + docId,
      description: '',
      mimeType: '',
      visibleInDownloadsUi: true,
      notificationVisibility: NotificationVisibility.VisibleNotifyCompleted,
      destinationUri: this.file.externalRootDirectory + '/Download/',
      headers: [{ header: 'Authorization', value: _currentUser.appToken }]
    };

    this.downloader.download(request)
      .then((location: string) => console.log('File downloaded at:' + location))
      .catch((error: any) => console.error(error));
  }

  convertFileSrc(url: string): string {
    if (!url) {
      return url;
    }
    if (url.startsWith('/')) {
      return window['WEBVIEW_SERVER_URL'] + '/_app_file_' + url;
    }
    if (url.startsWith('file://')) {
      return window['WEBVIEW_SERVER_URL'] + url.replace('file://', '/_app_file_');
    }
    if (url.startsWith('content://')) {
      return window['WEBVIEW_SERVER_URL'] + url.replace('content:/', '/_app_content_');
    }
    return url;
  }

  jwt() {
    let _currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(_currentUser)
    var currentToken = this.getCurrentToken(_currentUser);
    console.log(currentToken)
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': currentToken
    });
    return { headers: httpHeaders };
  }

  getCurrentToken(_currentUser): string {
    if (_currentUser && _currentUser.appToken) 
      return _currentUser.appToken;
    else
        return '';
  }
  getCurrentUser() {
    let _currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (_currentUser && _currentUser.appToken) return _currentUser;
    return null;
}
 

  getMenuItems() {
    return this.http.get('/assets/menu.json')
  }

}

import { IService } from './IService';
import { AngularFireDatabase } from 'angularfire2/database';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { AppSettings } from './app-settings'
import { LoadingService } from './loading-service'
import { ToastService } from './toast-service';

@Injectable()
export class TabsService implements IService {

  constructor(public af: AngularFireDatabase, private loadingService: LoadingService, private toastCtrl: ToastService) { }

  getId = (): string => 'tabs';

  getTitle = (): string => 'Tabs';

  getAllThemes = (): Array<any> => {
    return [
      { "title": "Footer tab - text", "theme": "layout1" },
      { "title": "Footer tab - icons", "theme": "layout2" },
      { "title": "Header tab - text", "theme": "layout3" }
    ];
  };

  getDataForTheme = (menuItem: any): Array<any> => {
    return this[
      'getDataFor' +
      menuItem.charAt(0).toUpperCase() +
      menuItem.slice(1)
    ]();
  };


 getEventsForTheme = (menuItem: any): any => {
    var that = this;
    return {
      'onItemClick': function(item: any) {
          that.toastCtrl.presentToast(item);
      }
    };
  };

  prepareParams = (item: any) => {
    let result = {
      title: item.title,
      data: [],
      events: this.getEventsForTheme(item)
    };
    result[this.getShowItemId(item)] = true;
    return result;
  };

  getShowItemId = (item: any): string => {
    return this.getId() + item.theme.charAt(0).toUpperCase() + "" + item.theme.slice(1);
  };

  load(item: any): Observable<any> {
    var that = this;
    that.loadingService.show();
    if (AppSettings.IS_FIREBASE_ENABLED) {
        return new Observable(observer => {
            this.af
              .object('tab/' + item)
                .valueChanges()
                .subscribe(snapshot => {
                    that.loadingService.hide();
                    observer.next(snapshot);
                    observer.complete();
                }, err => {
                    that.loadingService.hide();
                    observer.error([]);
                    observer.complete();
                });
        });
    } else {
        return new Observable(observer => {
            that.loadingService.hide();
            observer.next(this.getDataForTheme(item));
            observer.complete();
        });
    }
}
}

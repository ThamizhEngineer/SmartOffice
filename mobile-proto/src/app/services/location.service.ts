import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { NativeGeocoder, NativeGeocoderResult, NativeGeocoderOptions } from '@ionic-native/native-geocoder/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@Injectable({providedIn: 'root'})
export class LocationService {
    constructor(private httpClient: HttpClient,rivate, geolocation: Geolocation,
        private nativeGeocoder: NativeGeocoder) { }
    

    getAddressFromName(name) {
        let options: NativeGeocoderOptions = {
          useLocale: true,
          maxResults: 5
      };   
      this.nativeGeocoder.forwardGeocode(name, options)
        .then((result: NativeGeocoderResult[]) => console.log('The coordinates are latitude=' + result[0].latitude + ' and longitude=' + result[0].longitude))
        .catch((error: any) => console.log(error));
      }

      
}
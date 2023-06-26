import { Component, OnInit, ViewChild, Input,Output,EventEmitter,TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MapLocation } from '../vo/map-location';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MapLocationService } from '../_services/map-location.service';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
@Component({
    selector: 'map-location',
    templateUrl: './map-location.component.html',
    providers: [MapLocationService],
}) export class MapLocationComponent implements OnInit {
    @Output() mapLocationAdded = new EventEmitter<boolean>();
    countryAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.countries.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    country_formatter = (x: {countryName: string}) => x.countryName;
    mapLocation: MapLocation;
    countries:any=[];
    coordinates:Array<Coordinates>;
    constructor(public service: MapLocationService) {
    }
    ngOnInit() {
        this.mapLocation = new MapLocation();
        this.mapLocation.country="IN";
        this.service.getAllCountry().subscribe(_countries=>{
            this.countries = _countries;
        });
    }
    convertAddressToCoords() {

        this.service.searchGeoposition(this.mapLocation.address,this.mapLocation.country,this.mapLocation.locName).subscribe(_map => {
        

                this.service.getByAddressAndCountryCodeAndStatusAndLocName(this.mapLocation.address,this.mapLocation.country,this.mapLocation.locName).subscribe(r=>{

                    this.mapLocation.lats=r.lats;
                    this.mapLocation.longs=r.longs;
                })


        });
    
   
    } 

    save(){
        let mapLocations= new Array<MapLocation>();
        mapLocations.push(this.mapLocation);
        
        this.service.addMapLocation(mapLocations).subscribe(x=>{
       
        })
        this.mapLocationAdded.emit(true);
    }
}
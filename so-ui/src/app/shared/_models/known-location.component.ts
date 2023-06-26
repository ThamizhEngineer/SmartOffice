import { Component, OnInit, ViewChild, Input,Output,EventEmitter,TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MapLocation } from '../vo/map-location';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MapLocationService } from '../_services/map-location.service';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
@Component({
    selector: 'map-location',
    templateUrl: './known-location.component.html',
    providers: [MapLocationService],
}) export class KnownLocationComponent implements OnInit {
    @Input() type:string;   
    @Output() mapLocationAdded = new EventEmitter<boolean>();
    @Output() locationMap = new EventEmitter();
    countryAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.countries.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    country_formatter = (x: {countryName: string}) => x.countryName;
    mapLocation: MapLocation;
    countries:any=[];
    coordinates:Array<Coordinates>;
    alertMsg:any;
    mapLocations:Array<MapLocation>;
   
    constructor(public service: MapLocationService) {
    }
    ngOnInit() {
        this.mapLocation = new MapLocation();
        this.mapLocations= new Array<MapLocation>();
        this.mapLocation.country="IN";
        this.service.getAllCountry().subscribe(_countries=>{
            this.countries = _countries;
        });
    }
    convertAddressToCoords() {
        if(this.mapLocation.locName==null||this.mapLocation.locName==undefined){
            this.mapLocation.locName='';
        }
        if(this.mapLocation.address==null||this.mapLocation.address==undefined){
            this.mapLocation.address='';
        }
        this.service.searchKnowLocation(this.mapLocation.locName,this.mapLocation.address,'',this.mapLocation.country).subscribe(x=>{
            this.mapLocations=x;
        })           
    } 

    getLocation(location){
        console.log(location)
        console.log({"item":location})
        this.locationMap.emit({"item":location});
    }

    save(){
         this.mapLocations= new Array<MapLocation>();
        this.mapLocations.push(this.mapLocation);
        
        this.service.addMapLocation(this.mapLocations).subscribe(x=>{
            this.alertMsg={ type: 'success', text: "Map Location Saved Successfully..!!" }   
        },error=>{
            this.alertMsg={ type: 'danger', text: "The Filled Map Location Have Already Present ..!!" }   
        })
        this.mapLocationAdded.emit(true);
    }
}
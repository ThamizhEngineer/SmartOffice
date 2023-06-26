import { Component, OnInit, ViewChild, TemplateRef, Output, EventEmitter, Input, ViewEncapsulation } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { MapLocationService } from '../_services/map-location.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'map-view',
    templateUrl: 'map.component.html',    
    providers:[MapLocationService],
    
})

export class MapComponent implements OnInit {
  
    @Input() placeholder: string;
	@Input() value:string;
    @Output() location = new EventEmitter();
    
    @ViewChild('addMap') addMap: TemplateRef<any>;

  
    locationAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.locations.filter(v => v.locName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    location_formatter = (x: {locName: string}) => x.locName;

    constructor(
        private service:MapLocationService,
        private modalService: NgbModal
    ) { }

    locations:any=[];
    type:string;
    modalReference:any = null;

    ngOnInit() { 

        this.service.getMapLocation().subscribe(x=>{
            this.locations=x;
            console.log(this.location);
        })
        if(this.value==null){
            this.value=null;
        }
    }

    onLocationSelected($event){
        console.log($event);
        this.location.emit($event.item);
    }

    mapPopUp(type){
        this.type=type;
        this.modalReference = this.modalService.open(this.addMap, { size: 'lg' });
    }
    close(){
        this.modalReference.close();
        console.log("hit");
        this.ngOnInit();
    }
}
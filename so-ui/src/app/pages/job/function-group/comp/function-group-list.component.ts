import { Component, OnInit, Pipe } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Observer } from 'rxjs';
import { ResponseContentType, Http } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { FunctionGroupService } from '../function-group.service';
import { FunctionGroup } from '../vo/function-group';

@Component({
    selector: '',
    templateUrl: 'function-group-list.component.html'
})

export class FunctionGroupListComponent implements OnInit {
    saveMsg: { 'type': string; 'text': string; };
    upage :number = 1;
    upageSize :number = 10;
    rows: any=[];
    constructor(private router:Router,private http: Http,private service:FunctionGroupService) { }

    ngOnInit() { 
        this.getData();
    }

    getData(){
        this.service.getFunctionGroup().subscribe(x=> {
            this.rows = x;
            console.log(this.rows);
        })
    }

    deleteRow(id?: any){
		this.service.deleteFunctionGroup(id).subscribe(x=>{
            console.log(x);
            this.saveMsg = {'type': 'success', 'text': "Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}
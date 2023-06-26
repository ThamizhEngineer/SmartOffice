import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { Menu } from '../../vo/menu';

@Component({
    selector: 'menu',
    templateUrl: 'menu.component.html'
})

export class MenuComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;
    menu: any=[];
    page :number = 1;
    pageSize :number = 10;
    menus: Menu;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
    { }

    ngOnInit() {
        this.menus = new Menu();
        this.getMenu();
     }

    getMenu(){
        this.authorisationService.getAllMenu().subscribe(x => {
            this.menu = x;
        });
    }

    showDetail(id?: any) {
        console.log(id)
        this.authorisationService.getMenuById(id).subscribe(x => {
            this.menus = x;
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.menus = new Menu();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    deleteRow(id?: any){
		this.authorisationService.deleteFeature(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "menu Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }

        save() {
            if (this.menus.id) {
                console.log(this.menu.id)
                this.authorisationService.updateMenu(this.menus.id,this.menus).subscribe(x => {
                    this.saveMsg = { 'type': 'success', 'text': "menu Updated Successfully" };
                    this.getMenu();
                    this.modalReference.close();
                });
        }else{
            console.log(this.menus)
            this.authorisationService.createMenu(this.menus).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "menu Created Successfully" };
                this.getMenu();
                this.modalReference.close();
            });
        }
        }
}
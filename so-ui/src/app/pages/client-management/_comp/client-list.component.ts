import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '../client.service';
import { Partner } from '../../job/vo/partner';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'client-list',
    templateUrl: 'client-list.component.html'
})

export class ClientListComponent implements OnInit {
    clients: any = [];
    partner: Partner;
    saveMsg: any;

    page = 0;
    pageSize = 10;
    clientCode: any;
    companyName: any;
    countryName: any;
    country: any=[];
    constructor(private router: Router, private clientService: ClientService) {

    }
    ngOnInit() {
        this.getAllClient();
        this.getAllCountries();
    }

    getAllClient() {
        this.clientService.getAllClient().subscribe(x => {
            this.clients = x;
        });

    }
    getAllCountries() {
        this.clientService.getAllCountries().subscribe(x => {
            this.country = x;
        });

    }

    deleteRow(id) {
        this.clientService.deleteClientById(id).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Client Deleted Successfully" };
            this.getAllClient();
        }, error => {
            this.saveMsg = { 'type': 'danger', 'text': "server error" };
        });
    }

    navigateToAddPage() {
        this.router.navigateByUrl("client/new");
    }

    navigateToDetailPage() {
        this.router.navigateByUrl("client/view");
    }

    navigateToEditPage() {
        this.router.navigateByUrl("client/edit/" + this.partner.id);
    }

     search() {
            this.clientService.advanceSearch(this.clientCode, this.companyName, this.countryName).subscribe(x => {
                this.clients = x;
            })
        }

        reset() {
            this.clientCode = null; this.companyName = null; this.countryName = null;
            this.search();
        }
}


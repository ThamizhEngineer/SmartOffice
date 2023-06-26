import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';


@Component({
    selector: 'authorisation',
    templateUrl: 'authorisation.component.html'
})

export class AuthorisationComponent implements OnInit {
    constructor(private router:Router,private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
    { }

    ngOnInit() { 

    }
}
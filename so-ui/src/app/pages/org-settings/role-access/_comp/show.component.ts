import { Component, OnInit, ViewChild, TemplateRef} from '@angular/core';

import { RoleAccess } from '../../../vo/role-access';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleAccessService } from '../role-access.service';


@Component({
    selector: 'role-access-show',
    templateUrl: './show.component.html'
})

export class RoleAccessShowComponent implements OnInit {

    @ViewChild('cdelete') cdelete: TemplateRef<any>;
	@ViewChild('cedit') cedit: TemplateRef<any>;
    constructor(private modalService: NgbModal, private _service:RoleAccessService){

    }

    ngOnInit() {
        this.getData();
    }

    getData(){

    }

    newRA(){

    }
    
    modalReference:any = null;
	modalData: any;
	ra: any;
    deleteFY(ra: RoleAccess){
		this.modalData = ra;
		this.modalReference = this.modalService.open(this.cdelete);
    }
    
    saveMsg: any;
	confirmDelete(data){
		this._service.deleteRoleAccess(data.id).subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': "Fiscal Year Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		}, e=> {
			this.saveMsg = {'type': 'success', 'text': "Fiscal Year Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		});
	}
}
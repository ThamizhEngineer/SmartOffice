import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { DocTypeService } from '../doc-type.service';
import { Doc } from '../vo/doc-type';


@Component({
    selector: '',
    templateUrl: 'doc-type.component.html'
})

export class DocTypeComponent implements OnInit {
    // @Input() message: any;
    
    Documents: Array<Doc>;
    doc: any = [];
    doct: Document;
    documentType: Doc;
    errorMessage:string;
    doFade: boolean;
    // ngAlert:boolean;
    page :number = 1;
    pageSize :number = 8;
    cpage :number = 1;
    cpageSize :number = 8;
    cgpage :number = 1;
    cgpageSize :number = 8;
    saveMsg: any;

    constructor(private router: Router, private service: DocTypeService) { }

    ngOnInit() {
        this.doct = new Document();
        this.Documents = new Array<Doc>();
        this.documentType = new Doc();  
        this.onInit();
        
    }

    onInit() {
        this.service.getDoc().subscribe(x => {
            this.Documents = x;

        })
    }

    // ngOnChanges(){
	// 	if(this.ngAlert) {
	// 		setTimeout(()=>this.ngAlert = false, 4000);
	// 	}
	// }

    // close() {
    //     this.ngAlert = false;
    // }
    addDoc() {
        let document = new Doc();
        this.Documents.push(document);
    }

    removeDoc(id, index) {
        // this.alert=false;
        if (id != null) {
            var idString: string = (id).toString();
            this.service.deleteDoc(idString).subscribe(x => {
                this.Documents.splice(index, 1);
               
            },
            error => {
                var errorResponse = error._body
                this.saveMsg = {'type': 'danger', 'text': "Doc info exists for docType. Cannot compelete deletion"}

                var obj = JSON.parse(errorResponse);
                console.log("variables",obj)
                // this.alert=true;
                this.errorMessage=obj.message;
                // this.saveMsg = {this.errorMessage}
                // this.onInit();
                // this.saveMsg = {'errorMessage':'Warning : Doc info exists for docType. Cannot compelete deletion'}

            }
           
        )
        } else {
            this.Documents.splice(index, 1);
            this.ngOnInit();
        }
    }

    updateDoc(c) {
        console.log(c)
        this.service.patchDoc(c).subscribe(x => {
            console.log(x)
            this.onInit();

        })
    }
    saveDeg() {
        console.log(this.Documents)
        console.log(this.doc)
        console.log(this.doct)

        this.doc.documentType = [];
        this.Documents.forEach(element => {
            if (element.id == null) {
                this.doc.push(element);
            }
            console.log(this.doc)

        });
        this.service.postDoc(this.doc.documentType).subscribe(x => {
            this.onInit();
        })
        console.log(this.documentType)

    }
    // function(){
    //     			setTimeout(()=>this.doFade = false, 4000);

        // $scope.doFade = true;
    //   }, 2500);}
    
}

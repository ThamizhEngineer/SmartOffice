import { Component, OnInit } from '@angular/core';
import { FileManagerService } from '../file-manager.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../../shared/common/common.service';
import { Observable } from 'rxjs';

@Component({
    selector: 'file-manager',
    templateUrl: 'file-manager.component.html',
    styleUrls: ['./file-manager.component.scss']
})

export class FileManagerComponent implements OnInit {
    buckets: any =[]
    data: any=[]
    isReadOnly:boolean=true
    previousPoint: number = 0 //Stores previously selected bucket

    videoSource: any;
    modalReference: NgbModalRef;

    breadCrumHdr: any=[]
    breadCrum: any =[]
    fileToUpload: File = null;

    currentParentType=""
    currntParentId=""

    folderName=""

    constructor(private _service: FileManagerService, private modalService: NgbModal, private commonService: CommonService,) { }

    ngOnInit() { 
        this._service.fetchHdrList().subscribe(x => {
            if(x){
                this.buckets = x;
                if(this.buckets && this.buckets.length)
                    this.selectFile(this.buckets[0], 0);
            }
        });
    }

    refresh() {
        this.ngOnInit()
    }


    selectFile(x, i) {
        console.log(x)
        if(x){
            if (x.readOnly == "N") {
                this.isReadOnly = false
            }
            this.buckets[this.previousPoint].currentPoint = ""  //Clears previously selected bucket 
            this.data = []
            this.buckets[i].currentPoint = "active"
            this.previousPoint = i
            this.getFolderUndrHdr(this.buckets[i].id);
        }
    }

    selectFolder(x, i, iModel) {
        switch (this.data[i].type) {
            case "folder":
                this.getFolderUndrFolder(this.data[i].id)
                break;
            case "video":
                console.log("its video")
                this.loadVideo(this.data[i].docId)
                this.openImageViewModel(iModel)
                break;
            case "image":
                console.log("its image")
                break;
            case "pdf":
                console.log("its pdf")
                break;              
            default:
                console.log("Action not supported")
                break;
        }
    }

    selectBreadCrum(){
        console.log("select breadcrum")
    }

    openImageViewModel(m) {
        this.modalReference = this.modalService.open(m, { size: 'lg' });
    }


    // Rename -------------------------------------------------------

    rename(i) {
        console.log(this.data[i])
        this.data[i].editMode = "N"
    }

    cancelRename(i) {
        this.data[i].editMode = "Y"
        console.log("Cancel")
    }

    saveRename(i) {
        this.data[i].editMode = "Y"
        console.log("Save")
    }

    // Api calls ---------------------------------------------------------
    
    loadVideo(docId) {
        this.videoSource = this._service.getVideoUrl(docId)
    }

    folderCreationModel(m){
        this.modalReference = this.modalService.open(m, { size: 'sm' });
    }

    getFolderUndrHdr(hdrId){
        this.currentParentType = "header"
        this.currntParentId = hdrId
        this._service.fetchFolderUndrHdr(hdrId).subscribe(x => {
            if (x) {
                this.data = x;
                this.data.forEach(element => {
                    var icon = this.manageIcon("folder");
                    element.iconType = icon
                    element.editMode = "Y"
                });
            this.getFilesUndrHdr(hdrId)
            }
        });
    }

    getFolderUndrFolder(folderId){
        this.currentParentType = "folder"
        this.currntParentId = folderId
        this._service.fetchFoldersUndrFolder(folderId).subscribe(x => {
            if (x) {
                this.data = x;
                this.data.forEach(element => {
                    var icon = this.manageIcon("folder");
                    element.iconType = icon
                    element.editMode = "Y"
                });
            this.getFilesUndrFolder(folderId)
            }
        });
    }

    getFilesUndrHdr(hdrId){
        this._service.fetchFilesUndrHder(hdrId).subscribe(x => {
            if (x) {
                console.log(x)
                this.formDataFromList(x)
            }
        })
    }


    chooseFile(fileList: FileList){
        this.fileToUpload = fileList[0];
    }

    uploadFile(docTypeCode) {
        console.log(docTypeCode)
        console.log(this.fileToUpload)
			 this._service.customUpload(this.fileToUpload, docTypeCode,this.currentParentType, this.currntParentId).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
                        console.log(data)
                    },
                    error =>{
                         console.log(error)
                    }
                )
    }

    downloadFile(event){
        console.log("download")
        console.log(event)
        this.commonService.fileDownloader(event.id, event.name);
    }

    getFilesUndrFolder(folderId){
        this._service.fetchFilesUndrFolder(folderId).subscribe(x =>{
            console.log(x)
            if (x) {
                this.formDataFromList(x)
            }
        })
    }

    formDataFromList(x) {
        x.forEach(element => {
            var icon = this.manageIcon(element.extension);
            var o = { id:element.id, code: element.name, name: element.name, createdDt: element.createdDt, modifiedDt: element.modifiedDt, iconType: icon, docTypeName: element.docTypeName, editMode: "Y", type: element.type, docId: element.docId }
            this.data.push(o)
        });
    }


    createFolder(){
        console.log(this.folderName)
        this._service.createFolder(this.currentParentType, this.currntParentId,this.folderName).subscribe(x =>{
            console.log(x)
            this.folderName=""; //resetting folder name
            this.modalReference.close() 
        },error => {
            console.log(error)
        })
    }


    //Table sort -----------------------------------------------------

    sortArray(type) {
        switch (type) {
            case "docTypeName":
                this.data.sort((a, b) => (a.docTypeName > b.docTypeName) ? 1 : -1)
                break;
            case "name":
                this.data.sort((a, b) => (a.name > b.name) ? 1 : -1)
                break;
            default:
                break;
        }
    }

    manageIcon(type) {
        switch (type) {
            case "xlsx":
                return "fa fa-file-excel-o fa-2x"
            case "csv":
                return "fa fa-table fa-2x"
            case "txt":
                return "fa fa-file-text-o fa-2x"
            case "folder":
                return "fa fa-folder-o fa-2x"
            case "pdf":
                return "fa fa-file-pdf-o fa-2x"
            case "mp4":
                return "fa fa-file-video-o fa-2x"   
            case "png":
                return "fa fa-picture-o fa-2x"
            case "jpg":
                return "fa fa-picture-o fa-2x"
            case "jpeg":
                return "fa fa-picture-o fa-2x"
            default:
                return "fa fa-question fa-2x"
        }
    }
}
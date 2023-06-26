import { Component, OnInit, ViewChild, TemplateRef, Output, EventEmitter, Input, ViewEncapsulation } from '@angular/core';
import { FileQueueObject, FileUploaderService } from './../_services/uploader.service';

import { Observable } from 'rxjs/Observable';

@Component({
    selector: 'file-upload',
	templateUrl: './file.upload.component.html',
	styles: ['.pace-active { display: none; }'],
	providers: [FileUploaderService],
})
export class FileUploadComponent implements OnInit {
	
	@Input() fileDetail: any

	@Input() buttonName:any;

	@Input() icon:any;

	@Input() isNew:any;

	@Input() btnColor:any;
    
	@ViewChild('fileInput') fileInput;
    constructor(public uploader: FileUploaderService) {
    }

	@Output() onCompleteItem = new EventEmitter();	
	queue: Observable<FileQueueObject[]>;
	fileList = [];
	
	ngOnInit() {
		console.log("ngOnInit")
		this.queue = this.uploader.queue;
		console.log(this.queue)
		this.uploader.onCompleteItem = this.completeItem;

		if(this.buttonName==null){
			this.buttonName='Select file to Upload';
		}
		if(this.icon==null){
			this.icon='fa-cloud-upload';
		}
		if(this.isNew==null){
			this.isNew='N';
		}
		if(this.btnColor==null){
			this.btnColor='success';
		}
	}
	

	completeItem = (item: FileQueueObject, response: any) => {
		console.log("completeItem")
		this.onCompleteItem.emit({ item, response });
	}

	
	addToQueue() {
		console.log("addToQueue")
		this.uploader.clearQueue();
		const fileBrowser = this.fileInput.nativeElement;
		let q = new FileQueueObject(fileBrowser.files);		
		this.uploader.addToQueue(fileBrowser.files,this.fileDetail);
		this.fileList.push(q);
		setTimeout(()=>{ 
			this.uploader.uploadAll(this.fileDetail); 
		}, 300);
	}
	
	clearQueue() {
		console.log("clearQueue")
		this.fileList = [];
		this.uploader.clearQueue();
	}
}
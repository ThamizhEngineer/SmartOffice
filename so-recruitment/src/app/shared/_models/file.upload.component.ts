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
    
	@ViewChild('fileInput') fileInput;
    constructor(public uploader: FileUploaderService) {
    }

	@Output() onCompleteItem = new EventEmitter();	
	queue: Observable<FileQueueObject[]>;
	fileList = [];
	
	ngOnInit() {
		this.queue = this.uploader.queue;
		this.uploader.onCompleteItem = this.completeItem;
	}

	completeItem = (item: FileQueueObject, response: any) => {
		this.onCompleteItem.emit({ item, response });
	}

	
	addToQueue() {
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
		//this.fileList = [];
		//this.uploader.clearQueue();
	}
}
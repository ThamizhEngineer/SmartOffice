import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';

import { EmbedVideoService } from 'ngx-embed-video';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LearningTrack } from './vo/LearningTrack';
import{LearningTrackService} from '../learning-track/learning-track.service';

@Component({
  selector: 'learning-track-detail',
  templateUrl: './learning-track-detail.component.html',
  styleUrls:['./learning-track.css'],
})
export class LearningTrackDetailComponent implements OnInit{

   learningTrack:LearningTrack
   
    constructor( private modalService: NgbModal,private service:LearningTrackService) {
   
    }
    ngOnInit(){
      this.learningTrack= new LearningTrack();


      
    }
    saveMsg:any
    videoUpload(value){
      this.service.addMinioVideo(value).subscribe(x=>{
        console.log(value);
        this.saveMsg = {
            type: 'success', text: "Video Added successfully"
        }
        
    })
    this.service.addLearningTrack(this.learningTrack).subscribe(x=>{
      this.saveMsg = {
        
    }
    })
    
   
}
}
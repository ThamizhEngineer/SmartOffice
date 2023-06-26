import { Component, OnInit, ViewChild, TemplateRef, HostListener } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { VIDEOS, Videos} from './video';
import { EmbedVideoService } from 'ngx-embed-video';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';



@Component({
  selector: 'learning-track-list',
  templateUrl: './learning-track-list.component.html',
  styleUrls:['./learning-track.css'],
})
export class LearningTrackListComponent{
  @ViewChild('uploadVideo') uploadVideo: TemplateRef<any>;
  @ViewChild('videoContent') videoContent: TemplateRef<any>;
  @ViewChild('videoContent1') videoContent1: TemplateRef<any>;
  @ViewChild('videoContent2') videoContent2: TemplateRef<any>;
  @ViewChild('videoContent3') videoContent3: TemplateRef<any>;
  @ViewChild('videoContent4') videoContent4: TemplateRef<any>;
  @ViewChild('videoContent5') videoContent5: TemplateRef<any>;
  @ViewChild('videoContent6') videoContent6: TemplateRef<any>;
  @ViewChild('videoContent7') videoContent7: TemplateRef<any>;
    vimeoUrl = 'https://vimeo.com/197933516';
    youtubeUrl = 'https://www.youtube.com/watch?v=r3MFuAdkHEM&list=PLah6faXAgguOeMUIxS22ZU4w5nDvCl5gs&index=2';
    dailymotionUrl =
      'https://www.dailymotion.com/video/x20qnej_red-bull-presents-wild-ride-bmx-mtb-dirt_sport';
   
    vimeoId = '197933516';
    youtubeId = 'iHhcHTlGtRs';
    dailymotionId = 'x20qnej';
  duration: number;
  currentTime:number;
  id:any;
  timeLeft:number=0;
  recordTime:number=0;
  saveMsg:any;
  interval;
  msg:any;
  videos:any;
    constructor( private modalService: NgbModal) {
   
    }

    ngOnInit() {
      
   this.videos = VIDEOS;
      
   
    }

    

    @HostListener('document:keyup', ['$event'])
  onKeyUp (event: KeyboardEvent) {
    if (event.keyCode == 44 || event.keyCode == 123) {
      event.preventDefault()
    }
  }
    
    modalReference:any = null;
    modalData: any;

    uploadVideos($event){
      this.modalReference = this.modalService.open(this.uploadVideo, { size: 'lg' });
      $event.preventDefault();
      $event.stopPropagation();

    }
    videoDetail($event){
      this.modalReference = this.modalService.open(this.videoContent, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }

    videoDetail1($event){
      this.modalReference = this.modalService.open(this.videoContent1, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }
    videoDetail2($event){
      this.modalReference = this.modalService.open(this.videoContent2, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }
    videoDetail3($event){
      this.modalReference = this.modalService.open(this.videoContent3, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }
    videoDetail4($event){
      this.modalReference = this.modalService.open(this.videoContent4, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }
    videoDetail5($event){
      this.modalReference = this.modalService.open(this.videoContent5, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }
    videoDetail6($event){
      
      this.modalReference = this.modalService.open(this.videoContent6, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }
    videoDetail7($event){
      this.modalReference = this.modalService.open(this.videoContent7, { size: 'lg' });
      this.Duration();
      $event.preventDefault();
      $event.stopPropagation();
    }


    Duration(){
      this.interval = setInterval(() => {
        
        this.recordTime++;
        //console.log(this.recordTime);
        if(this.duration<=this.recordTime){
          console.log("completed22");
       } 
     
    },1000)
    }
    
    dummy:number;
    onMetadata(e, video,playhead) {
      console.log('metadata: ', e);
      console.log('duration: ',video.duration);
      this.currentTime=video.currentTime;
      this.duration = video.duration;
      if(playhead != null){
       video.currentTime=this.videos[playhead].playHead;
       video.play();
      }
      //console.log('video.currentTime: ', video.currentTime);
      if(video.duration==video.currentTime){
            
        //console.log(this.timeLeft);
         console.log("completed");
      }
      this. startTimer(video);
      this.dummy = this.duration;
      this.timeLeft = Math.round(this.dummy);
     console.log(Math.round(this.timeLeft));
    }

    startTimer(video) {
     
      this.interval = setInterval(() => {
        if(this.timeLeft > 0) {
          this.timeLeft--;
          console.log('video.currentTime: ', video.currentTime);
          console.log(video.duration);
          if(video.duration==video.currentTime){
             console.log("completed");
          }
        }
       
      },1000)
    }
    
    pauseTimer() {
      clearInterval(this.interval);
    }
}

import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { TrackJobService} from '../../track-jobs/track-jobs.service';
import { Job} from '../../vo/job';

@Component({
    selector: 'track-jobs-list',
    templateUrl: './track-jobs-list.component.html'
})
export class TrackJobsListComponent implements OnInit {

    constructor(private router:Router,private _service:TrackJobService){

    }
    jobs=[];
    ngOnInit() {
    this._service.getJobTracks().subscribe(x=>{
        console.log(x.content)
        this.jobs =x.content;
    })
    }
    
    navigateToDetailPage(jobId:string){
        console.log(jobId)
     this.router.navigateByUrl("job/track-jobs/detail/"+jobId);   
    }
}



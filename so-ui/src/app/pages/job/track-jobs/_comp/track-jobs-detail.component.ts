import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { TrackJobService } from '../track-jobs.service';
import { Job, JobMilestone, JobTaskList, JobAsset, JobAssetDocs } from '../../vo/job';
import { Task, SubTask } from '../../vo/task';
import { Comment } from '../../vo/comment';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
    selector: 'track-jobs-detail',
    templateUrl: './track-jobs-detail.component.html',
    styleUrls: ['./track-jobs.css', './switchery.css', './ticket.css'],
    encapsulation: ViewEncapsulation.None,
})

export class TrackJobsDetailComponent implements OnInit {
    comment: Comment;
    trackJob: any;
    task: Task;
    job: Job;
    jobMilestone: JobMilestone;
    jobTaskList: JobTaskList;
    jobAsset: JobAsset;
    jobMilestoneCount: number = 0;
    bay: any;
    relay: any;
    ticket: any;
    fileDetails: any = { 'for': 'Please upload the MOM' };
    assets: any;
    modalReference: any = null;
    assetReference: any = null;
    @ViewChild('modalContent') modalContent: TemplateRef<any>;
    @ViewChild('assetContent') assetContent: TemplateRef<any>;
    constructor(private route: ActivatedRoute, private commonService: CommonService, private modalService: NgbModal, private service: TrackJobService) { }

    ngOnInit() {
        this.job = new Job();
        this.jobMilestone = new JobMilestone();
        this.jobTaskList = new JobTaskList();
        this.jobAsset = new JobAsset();
        this.task = new Task();
        this.task.comments = new Array<Comment>();
        this.comment = new Comment();
        if (this.route.params['_value']['_id']) {
            this.route.params.switchMap((par: Params) => this.service.getJobTracksByJobId(par['_id'])).subscribe(x => {
                this.job = x;
                this.service.getAllAsset().subscribe(x => {
                    var res = x.content.filter(x => x.materialCategory === "asset");
                    this.assets = res;
                    console.log(this.assets)
                });
                this.jobMilestoneCount = this.job.jobMilestones.length;
            });
        }
    }

    addMilestones() {
        let alreadyExistingMilestoneCount = this.job.jobMilestones.length;
        let toBeAddedMilestoneCount = this.jobMilestoneCount - alreadyExistingMilestoneCount;
        if (toBeAddedMilestoneCount > 0) {
            for (let i = 0; i < toBeAddedMilestoneCount; i++) {
                this.job.jobMilestones.push(Object.assign({}, new JobMilestone()));
            }
        }
    }

    saveMilestones() {
        this.service.saveWorkspace(this.job.id, this.job).subscribe(x => {
            this.getJobByID();
        })
    }

    resetTasks() {
        this.service.resetTasks(this.job.id).subscribe(x => {
            this.getJobByID();
        })
    }

    addTaskList(jobMilestone?: JobMilestone) {
        this.job.jobMilestones.forEach((x, i) => {
            if (x == jobMilestone) {
                if (!x.jobTaskList || !x.jobTaskList.length) x.jobTaskList = [];
                x.jobTaskList.push(Object.assign({}, new JobTaskList()));
            }
        });
    }

    addTasks(jobMilestones?: any, taskList?: JobTaskList) {
        let t = new Task();
        t.taskListId = taskList.id;
        t.jobId = this.job.id;
        t.taskStatus = "CREATED";
        t.milestoneId = jobMilestones.id;
        taskList.tasks.push(t);
    }

    saveTask(task?: Task) {
        let taskList = new Array<Task>();
        taskList.push(task)
        this.service.addTask(taskList).subscribe(x => {
            this.service.updateJobStatus(this.job.id).subscribe(x => {
            })
            this.getJobByID();
        })
    }

    assignEmployeeToTask(task?: Task) {
        this.service.assignEmployeeToTask(task).subscribe(x => {
        })
    }

    modalData: any;
    commentDesc?: string = "";
    delJobMilestone(jobMilestone?: JobMilestone, index?: any) {
        this.job.jobMilestones.splice(index, 1);
    }

    delJobTaskList(relays?: any, index?: any) {
        relays.splice(index, 1);
    }

    me() {
        console.log('here');
    }
    navigateToListPage() {

    }

    jobPtwReceived(job) {
        console.log(job)
        console.log(job.id)
        this.service.jobPtwReceived(job.id, job).subscribe(x => {
            this.getJobByID();
        })
    }

    jobSiteSurveyCompleted() {
        this.service.jobSiteSurveyCompleted(this.job.id, this.job).subscribe(x => {
            this.getJobByID();
        })
    }

    jobMomPrepared() {
        this.service.jobMomPrepared(this.job.id, this.job).subscribe(x => {
            this.getJobByID();
        })
    }

    jobMomApproved() {
        this.service.jobMomCompleted(this.job.id, this.job).subscribe(x => {
            this.getJobByID();
        })
    }
    getJobByID() {
        this.service.getJobTracksByJobId(this.job.id).subscribe(x => {
            this.job = x;
        });
    }

    openJobAssetRows(jobAssetId?: string) {
        this.jobAsset = new JobAsset();
        if (jobAssetId != null || jobAssetId != undefined) {
            this.jobAsset = this.job.jobAssets.filter(x => x.id == jobAssetId)[0];
            if (this.jobAsset.jobAssetDocs.length == 0) {
                let jobAssetDocs = new JobAssetDocs();
                jobAssetDocs.jobId = this.job.id;
                jobAssetDocs.docTypeCode = "JOB-ASSET-BEFORE";
                jobAssetDocs.jobAssetsId = this.jobAsset.id;
                this.jobAsset.jobAssetDocs.push(jobAssetDocs);
                let jobAssetDocs1 = new JobAssetDocs();
                jobAssetDocs1.jobId = this.job.id;
                jobAssetDocs1.docTypeCode = "JOB-ASSET-AFTER";
                jobAssetDocs1.jobAssetsId = this.jobAsset.id;
                this.jobAsset.jobAssetDocs.push(jobAssetDocs1);
            }
        } else {
            this.jobAsset.jobAssetDocs = new Array<JobAssetDocs>();
            let jobAssetDocs = new JobAssetDocs();
            jobAssetDocs.jobId = this.job.id;
            jobAssetDocs.docTypeCode = "JOB-ASSET-BEFORE";
            this.jobAsset.jobAssetDocs.push(jobAssetDocs);
            let jobAssetDocs1 = new JobAssetDocs();
            jobAssetDocs1.jobId = this.job.id;
            jobAssetDocs1.docTypeCode = "JOB-ASSET-AFTER";
            this.jobAsset.jobAssetDocs.push(jobAssetDocs1);
        }
        this.assetReference = this.modalService.open(this.assetContent, { size: 'lg' });

    }
    addJobAssetDocsRows(jobAsset?: JobAsset, type?: string) {
        let jobAssetDocs = new JobAssetDocs();
        jobAssetDocs.jobId = this.job.id;
        jobAssetDocs.jobAssetsId = jobAsset.id;
        jobAssetDocs.docTypeCode = type;
        this.jobAsset.jobAssetDocs.push(jobAssetDocs);
    }

    delJobAssetRow(jobAssetId?: string) {
        this.service.deleteJobAsset(this.job.id, jobAssetId).subscribe(x => {
            this.getJobByID();
        })
    }

    saveJobAsset(jobAsset?: JobAsset) {
        if (jobAsset.id != null || jobAsset.id != undefined) {
            this.job.jobAssets.forEach(x => {
                if (x.id == jobAsset.id) {
                    x = jobAsset;
                }
            })
        } else {
            this.job.jobAssets.push(jobAsset);
        }
        this.service.jobAssetSave(this.job.id, this.job).subscribe(x => {
            this.getJobByID();
            this.assetReference.close();
        });
    }

    taskDetail(task?: Task) {
        this.commentDesc = "";
        this.service.getTasks(this.job.id, task.id).subscribe(x => {
            x.isDelayedFlag = (x.isDelayed == 'Y' ? true : false);
            x.isBlockedFlag = (x.isBlocked == 'Y' ? true : false);
            x.subTasks.forEach(s => {
                s.taskStatusFlag = (s.taskStatus == 'COMPLETED' ? true : false);
            })
            this.task = x;
            this.modalReference = this.modalService.open(this.modalContent, { size: 'lg' });
        })
    }

    addSubTaskRows(task?: Task) {
        let s = new SubTask();
        s.taskId = task.id;
        s.assignedToUserId = task.assignedToUserId;
        s.taskStatus = "CREATED";
        task.subTasks.push(s);
        this.service.saveSubTask(task.id, task).subscribe(x => {
            this.service.getTasks(this.job.id, task.id).subscribe(x => {
                this.task = x;
            });
        });


    }

    updateSubTaskData(task?: Task) {
        this.service.saveSubTask(task.id, task).subscribe(x => {
        })
    }

    completeSubTaskData(task?: Task, subTask?: SubTask) {
        subTask.taskStatus = (subTask.taskStatusFlag ? "COMPLETED" : "ASSIGNED");
        this.service.completeSubTask(task.id, subTask).subscribe(x => {
        })
    }
    deleteSubtask(task?: Task, subTask?: SubTask) {
        this.service.deleteSubTask(subTask.id).subscribe(x => {
            this.service.getTasks(this.job.id, task.id).subscribe(x => {
                this.task = x;
            });
        })
    }

    saveComment(task?: Task) {
        let comment = new Comment();
        comment.jobId = this.job.id;
        comment.taskId = task.id;
        comment.commentDesc = this.commentDesc;
        comment.jobMilestoneId = task.milestoneId;
        comment.jobTasklistId = task.taskListId;
        comment.partnerId = task.partnerId;
        comment.endClientId = task.endClientId;
        comment.employeeId = task.assignedToUserId;
        this.service.addComment(comment).subscribe(x => {
        });

    }
    updateTaskData(task?: any) {
        this.service.updateTask(task).subscribe(x => {
        });

    }

    updateTaskDelayStatus(task?: Task) {
        task.isDelayed = (task.isDelayedFlag ? "Y" : "N");
        this.service.updateTaskDelayStatus(task).subscribe(x => {
        });
    }

    updateTaskBlockStatus(task?: Task) {
        task.isBlocked = (task.isBlockedFlag ? "Y" : "N");
        this.service.updateTaskBlockStatus(task).subscribe(x => {
        });
    }
    completeTask(task?: Task) {
        this.service.completeTask(task).subscribe(x => {
            this.service.updateJobStatus(this.job.id).subscribe(x => {
            })
            this.modalReference.close();
        })

    }
    deleteTask(task?: Task) {
        this.service.deleteTask(task).subscribe(x => {
            this.service.updateJobStatus(this.job.id).subscribe(x => {
            })
            this.getJobByID();
            this.modalReference.close();
        })
    }

    closeTask() {
        this.getJobByID();
        this.modalReference.close();
    }

    fileChange(event, type: any) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            this.commonService.uploadDocument(file, type)
                .subscribe(
                    data => {
                        if (type == "JOB-PTW") {
                            this.job.ptwDocId = data[0].docId;
                        }
                        if (type == "JOB-SITE-SURVEY") {
                            this.job.surveyDocId = data[0].docId;
                        }
                        if (type == "JOB-MOM-DRAFT-PREPARED") {
                            this.job.momDraftPreparedDocId = data[0].docId;
                        }
                        if (type == "JOB-MOM-DRAFT-APPROVED") {
                            this.job.momDraftApprovedDocId = data[0].docId;
                        }
                    },
                    error => console.log(error))
        }
    }

    fileChangeAsset(event, type: any, jobAsset: JobAsset, jobAssetDocs: JobAssetDocs) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            this.commonService.uploadDocument(file, type)
                .subscribe(
                    data => {
                        jobAssetDocs.docId = data[0].docId;
                        jobAssetDocs.isSourceOnsite = "Y";
                    },
                    error => console.log(error))
        }
    }

    download(docId) {
        if (docId != null && docId != "" && docId != undefined) {
            this.commonService.downloadDocument(docId, "");
        }
    }

}



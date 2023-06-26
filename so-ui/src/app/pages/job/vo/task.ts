import { Comment}  from '../vo/comment';
export class Task{

    id?:string;
    taskName?:string;
    taskDesc?:string;
    taskType?:string;
    taskStatus?:string;
    taskListId?:string;
    taskListName?:string;
    assignedToUserId?:string;
    assignedOnDt?:Date;
    isDelayedFlag?:boolean=false;
    isBlockedFlag?:boolean=false;
    isDelayed?:string;
    isBlocked?:string;
    blockedDt?:Date;
    delayedDt?:Date;
    completedDt?:Date;
    jobId?:string;
    partnerId?:string;
    endClientId?:string;
    milestoneId?:string;
    taskTypeId?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
    openFlag:boolean = false;
    comments:Array<Comment>;
    subTasks:Array<SubTask>;

}

export class SubTask{
    id?:string;
    taskId?:string;
    subtaskName?:string;
    subTaskDesc?:string;
    taskStatus?:string;
    taskStatusFlag?:boolean = false;
    
    assignedToUserId?:string;
    assignedOnDt?:Date;
    completedDt  ?:Date;  
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}
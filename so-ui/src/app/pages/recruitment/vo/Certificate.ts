export class Certificate{
    id:number;
    incidentApplicantId:string;
    certificateCode:string;
    fname:string;
    lname:string;
    location:string;
    score:string;
    issueDt:string;
    expiryDt:string;
    certTemplateId:string;
    certificateDocId:string;
    skillsAqd:string;
    cmntsFromTrainer:string;
    isExpired:string;
    isEnabled?: string;
    createdBy?: string;
     modifiedBy?: string;
     createdDt?: Date;
    modifiedDt?: Date;
}
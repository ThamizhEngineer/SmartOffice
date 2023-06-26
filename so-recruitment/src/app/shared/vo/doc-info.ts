export class DocInfo{
    id  ?:string;
   docLocation  ?:string;
   docExtension  ?:string;
   docNameFromUser  ?:string;
   docName  ?:string;
   docId ?:string;
   createdBy  ?:string;
   modifiedBy  ?:string;
   metadataList ?:Array<DocMetadata>;
 }
 
 export class DocMetadata{
   id?:string;
   docId?:string;
   mdCode?:string;
   docInfoId?:string
   mdValue?:string;
   isKey?:string;
   createdBy  ?:string;  
   modifiedBy  ?:string;      
   createdDt?:string;
   modifiedDt  ?:string;      
 }
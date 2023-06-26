export class DocumentManagement {
    id?: number;
    docLocation?: string;
    newDocLocation?: string;
    docExtension?: string;
    docNameFromUser?: string;
    docName?: string;
    docId?: string;
    docTypeId?: string;
    docSize?: string;
    isEnabled?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
    metadataList?: Array<DocMetadata>;

  }

  export class DocMetadata {
    id?: number;
    docInfoId?: string;
    mdCode?: string;
    mdValue?: string;
    isKey?: string;
    createdBy?: string;
    modifiedBy?: string;
    createdDt?: string;
    modifiedDt?: string;
  }
  
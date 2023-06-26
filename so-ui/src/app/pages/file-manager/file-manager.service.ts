import { Injectable } from '@angular/core';
import { Http, Response, ResponseContentType, RequestOptions, Headers } from '@angular/http';

import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';

@Injectable()
export class FileManagerService {
  constructor(private http: Http, private commonService: CommonService) { }

  fetchHdrList() {
    return this.http.get(environment.documentApiUrl + 'fm/hdrs', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
  }

  fetchFolderUndrHdr(hdrId) {
    return this.http.get(environment.documentApiUrl + 'fm/folders-undr-hdr/' + hdrId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
  }

  fetchFoldersUndrFolder(folderId) {
    return this.http.get(environment.documentApiUrl + 'fm/folders-undr-folder/' + folderId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
  }

  fetchFilesUndrHder(hdrId) {
    return this.http.get(environment.documentApiUrl + 'fm/files-undr-hdr/' + hdrId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
  }

  fetchFilesUndrFolder(folderId) {
    return this.http.get(environment.documentApiUrl + 'fm/files-undr-folder/' + folderId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
  }

  getFileFromServer(): Observable<Blob> {
    let options = new RequestOptions({ responseType: ResponseContentType.Blob });
    return this.http.get('assets/img/users/en2.jpg', options)
      .map(res => res.blob());
  }

  getVideoUrl(docId) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser')); //Current logged in user
    var currToken = this.commonService.getCurrentToken(currentUser); // returns current token for current logged in user
    var url = environment.documentApiUrl + "dm/byterange/" + docId + "?authorization=" + currToken;
    console.log(url)
    return url
  }

  customUpload(file: File, type: any, parentType, parentId) {
    const formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "fm/custom-upload/"+ type + "/" + parentType + "/" + parentId;
    return this.http.post(documentUrl, formData, this.commonService.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  createFolder(parentType, parentId, newFolderName){
    let documentUrl = environment.documentApiUrl + "fm/create-folder/"+ parentType + "/" + parentId + "/" + newFolderName;
    return this.http.post(documentUrl, {}, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
  }

}
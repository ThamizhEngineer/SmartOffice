import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';
import { Role } from './vo/role';
@Injectable()
export class AuthorisationService {


    constructor(private http: Http, private commonService: CommonService) {
    }

    //------------------------------< Feature >---------------------------------------------------
    getAllFeature() {
        return this.http.get(environment.authUrl + 'auth/features', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getFeatureById(id: string) {
        return this.http.get(environment.authUrl + 'auth/features/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateFeature(id, authFeature) {
        return this.http.patch(environment.authUrl + 'auth/features/' + id, authFeature, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createFeature(authFeature: any) {
        return this.http.post(environment.authUrl + 'auth/features', authFeature, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteFeature(id: string) {
        return this.http.delete(environment.authUrl + 'auth/features/' + id, this.commonService.jwt()).map((response) => response);
    }

    // ---------------------------------------------------< Role >--------------------------------------------------

    getAllrole() {
        return this.http.get(environment.authUrl + 'auth/roles', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getroleById(id: string) {
        return this.http.get(environment.authUrl + 'auth/roles/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updaterole(id, authRole) {
        return this.http.patch(environment.authUrl + 'auth/roles/' + id, authRole, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createrole(authRole: any) {
        return this.http.post(environment.authUrl + 'auth/roles', authRole, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleterole(id: string) {
        return this.http.delete(environment.authUrl + 'auth/roles/' + id, this.commonService.jwt()).map((response) => response);
    }

    // ---------------------------------------------------<Auth-role-feature>--------------------------------------------------------------------
    getAllAuthRole(roleId,featureId) {
        let url='auth/roles-features?dummy=1';
        if(roleId!=null || roleId!=undefined){
            url=url+'&roleId='+roleId;
        }
        if(featureId!=null || featureId!=undefined){
            url=url+'&featureId='+featureId;
        }
        return this.http.get(environment.authUrl + url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getAuthRoleById(id: string) {
        return this.http.get(environment.authUrl + 'auth/roles-features/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateAuthRole(id, authRoleFeature) {
        return this.http.patch(environment.authUrl + 'auth/roles-features/' + id, authRoleFeature, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createAuthRole(authRoleFeature: any) {
        return this.http.post(environment.authUrl + 'auth/roles-features', authRoleFeature, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteAuthRole(id: string) {
        return this.http.delete(environment.authUrl + 'auth/roles-features/' + id, this.commonService.jwt()).map((response) => response);
    }

    // ----------------------------------------------------------<User-Type>--------------------------------------------------------------------------------------

    getAllUserType() {
        return this.http.get(environment.authUrl + 'auth/users-type', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getUserTypeById(id: string) {
        return this.http.get(environment.authUrl + 'auth/users-type/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateUserType(id, UserType) {
        return this.http.patch(environment.authUrl + 'auth/users-type/' + id, UserType, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createUserType(UserType: any) {
        return this.http.post(environment.authUrl + 'auth/users-type', UserType, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteUserType(id: string) {
        return this.http.delete(environment.authUrl + 'auth/users-type/' + id, this.commonService.jwt()).map((response) => response);
    }

    // ------------------------------------------------------------------<User>-------------------------------------------------------------------------------------------------------------

    getAllUser() {
        return this.http.get(environment.authUrl + 'auth/users', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getUserById(id: string) {
        return this.http.get(environment.authUrl + 'auth/users/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateUser(id, user) {
        return this.http.patch(environment.authUrl + 'auth/users/' + id, user, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createUser(user: any) {
        return this.http.post(environment.authUrl + 'auth/users', user, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteUser(id: string) {
        return this.http.delete(environment.authUrl + 'auth/users/' + id, this.commonService.jwt()).map((response) => response);
    }

    // ------------------------------------------------------------------<Menu>----------------------------------------------------------------

    getAllMenu() {
        return this.http.get(environment.authUrl + 'auth/menu-items', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getMenuById(id: string) {
        return this.http.get(environment.authUrl + 'auth/menu-items/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateMenu(id, menu) {
        return this.http.patch(environment.authUrl + 'auth/menu-items/' + id, menu, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createMenu(authMenu: any) {
        return this.http.post(environment.authUrl + 'auth/menu-items', authMenu, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteMenu(id: string) {
        return this.http.delete(environment.authUrl + 'auth/menu-items/' + id, this.commonService.jwt()).map((response) => response);
    }

    // ---------------------------------------------------------------<User-Menu>------------------------------------------------------------------------------------------

    getAllSubMenu() {
        return this.http.get(environment.authUrl + 'auth/sub-menu-items', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getSubMenuById(id: string) {
        return this.http.get(environment.authUrl + 'auth/sub-menu-items/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateSubMenu(id, menu) {
        return this.http.patch(environment.authUrl + 'auth/sub-menu-items/' + id, menu, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createSubMenu(authMenu: any) {
        return this.http.post(environment.authUrl + 'auth/sub-menu-items', authMenu, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteSubMenu(id: string) {
        return this.http.delete(environment.authUrl + 'auth/sub-menu-items/' + id, this.commonService.jwt()).map((response) => response);
    }


    // ---------------------------------------------------------------<Functionality>------------------------------------------------------------------------------------------

    getAllFunctionality() {
        return this.http.get(environment.authUrl + 'auth/functionality', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getFunctionalityById(id: string) {
        return this.http.get(environment.authUrl + 'auth/functionality/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateFunctionality(id, authFun) {
        return this.http.patch(environment.authUrl + 'auth/functionality/' + id, authFun, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createFunctionality(authFun: any) {
        return this.http.post(environment.authUrl + 'auth/functionality', authFun, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteFunctionality(id: string) {
        return this.http.delete(environment.authUrl + 'auth/functionality/' + id, this.commonService.jwt()).map((response) => response);
    }
}

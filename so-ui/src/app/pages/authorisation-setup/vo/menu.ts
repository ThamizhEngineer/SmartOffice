export class Menu{
    id?:string;
    name?:string;
    state?:string;
    type?:string;
    icon?:string;
    userId?:string;
    featureId?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
    authSubMenus?: Array<AuthSubMenus>;
}


export class AuthSubMenus{
    id?:string;
    authMenuId?:string;
    name?:string;
    state?:string;
    subState?:string;
    type?:string;
    authFeatureId?:string;
    authFeature?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}
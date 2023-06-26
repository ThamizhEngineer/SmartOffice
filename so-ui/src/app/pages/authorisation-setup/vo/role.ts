export class Role{
    id?:string;
    roleCode?:string;
    roleName?:string;
    remarks?:string;
    systemMapping?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
    authRoleFeatures?: Array<AuthRoleFeatures>;
}


export class AuthRoleFeatures{
    id?:string;
    authRoleId?:string;
    roleName?:string;
    authFeature?:string='';
    authFeatureId?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}
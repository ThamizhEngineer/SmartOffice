export class Material {
    id:               number;
    materialCategory: string;
    materialCode:     string;
    materialName:     string;
    materialDesc:     string;
    remarks:          string;
    hasInventory:     string;
    statusCode:       string;
    unitOfMeasure:    string;
    hsnCode:          string;
    unitPrice:        string;
    manufacturerId:   string;
    productFamilyId:  string;
    assetMake:        string;
    assetModel:       string;
    assetOwner:       string;
    feederEst:        string;
	motorEst:         string;
	generatorEst:     string;
	lineEst:          string;
	lineDiffEst:      string;
	stockValue:       string;
	trafoEst:         string;
    isEnabled:        string;
    createdBy:        string;
    modifiedBy:       string;
    createdDt:        string;
    modifiedDt:       string;
    materialServices: MaterialService[];
}

export class MaterialService {
    id:          number;
    materialId:  string;
    abilityId:   string;
    abilityCode: string;
    abilityName: string="";
    serviceDesc: string;
    isEnabled:   string;
    createdBy:   string;
    modifiedBy:  string;
    createdDt:   string;
    modifiedDt:  string;
}

export class TestTemplate{
    id:string;
    testTemplateName:string;
    description:string;
    duration:string;
    negativeMarking:string;
    marksPerQuestion:string;
    totalQuestions:any;
    passPercentage:string;
    createdBy:string;
    createdDate:string;
    modifiedBy:string;
    modifiedDate:string;
    testTemplateCatagory:Array<TestTemplateCatagory>;
}

export class TestTemplateCatagory{
    id:string;
    mTestTemplateId:string;
    mTestCatagoryId:string;
    difficultyCode:string;
    passPercentage:string;
    marksPerQuestion:string;
    totalQuestions:any;
    createdBy:string;
    createdDate:string;
    modifiedBy:string;
    modifiedDate:string;
    mTestCatagoryName:string="";
    availableQuestions:any;

}
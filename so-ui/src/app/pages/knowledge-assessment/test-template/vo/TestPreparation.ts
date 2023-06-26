export class TestPreparation{
    id:string;
    testId:string;
    testName:string;
    testType:string;
    displaySectionName:string;
    totalQuestion:string;
    negativeMarking:string;
    markPerQuestion:string;
    duration:string;
    proposedStartDate:string= new Date().toJSON();
    passPercentage:string;
    isEnabled:string;
    createdBy:string;
    modifiedBy:string;
    createdDt: string;
    modifiedDt:  string;
    testParticipants:Array<TestParticipant>;
    testPrepSections:Array<TestPrepSection>;
}
export class TestParticipant{
    id:string;
    testPrepId:string;
    testPrepName:string;
    noOfParticipants:string;
    participantId:string;
    isEnabled:string;
    createdBy:string;
    modifiedBy:string;
    createdDt: string;
    modifiedDt:  string;
}
export class TestPrepSection{
    id:string;
    testPrepId:string;
    sectionName:string;
    questionCategoryId:string;
    difficultyLevel:string;
    noOfQuestions:string;
    marksPerQuestion:string;
    negativeMarking:string;
    passPercentage:string;
    isEnabled:string;
    createdBy:string;
    modifiedBy:string;
    createdDt: string;
    modifiedDt:  string;
    testPrepQuestions:Array<TestPrepQuestion>;
}
export class TestPrepQuestion{
    id:string;
    testPrepSecId:string;
    qbId:string;
    markPerQuestion:string;
    negativeMarks:string;
    isEnabled:string;
    createdBy:string;
    modifiedBy:string;
    createdDt: string;
    modifiedDt:  string;

    
}
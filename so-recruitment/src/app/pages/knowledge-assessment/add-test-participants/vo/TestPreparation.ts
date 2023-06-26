

export class TestPreparation{
    id:string;
    testId:string;
    testName:string;
    testType:string;
    displaySectionName:string;
    totalQuestion:string;
    negativeMarking:string;
    duration:string;
    proposedStartDate:string= new Date().toJSON();;
    passPercentage:string;
    isEnabled: string;
	createdBy: string;
	modifiedBy: string;
	createdDt: string;
    modifiedDt:  string;
    testParticipants:Array<TestParticipant>
  
}
export class TestParticipant{
    id:string;
    testPrepId:string;
    participantId:string;
    isEnabled: string;
	createdBy: string;
	modifiedBy: string;
	createdDt: string;
    modifiedDt:  string;

}
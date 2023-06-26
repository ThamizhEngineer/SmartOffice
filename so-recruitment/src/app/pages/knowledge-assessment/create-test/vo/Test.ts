

export class Test{
    id:string;
    participantId:string;
    testPrepId:string;
score:string;
    testDate:string=  new Date().toJSON();
    startTime:string;
    endTime:string;
    noOfQuestions:string;
    totalCorrect:string;
    totalWrong:string;
    totalUnAttended:string;
    testStatus:string;
    invigilatorId:string;
    invigilatorComments:string;
    participantComment:string;
    isEnabled: string;
	createdBy: string;
	modifiedBy: string;
	createdDt: string;
    modifiedDt:  string;
    testQuestions:Array<TestQuestion>
    incidentTestQuestions:Array<IncidentTestQuestion>
    testPreparations:Array<TestPreparation>
    testParticipants:Array<TestParticipant>
}

export class IncidentTestQuestion{
    id:number
    tIncidentTestId:number;
    Question:  string;
    question:  string;
	correctOption :  string;
	option1 :  string;
	option2 :  string;
	option3 :  string;
	option4 :  string;
	userPicked :  string;
	difficultyLevel :  string;
	markPerQuestion :  string;
	marksAwarded :  string;
	negativeMarks :  string;
	OptionsCorrect :  string;
	questionId :  string;
	testStatus :  string;
	remainingTime :  string;
	duration :  string;
	createdBy :  string;
	createdDt :  string;
	modifiedBy :  string;
	modifiedDt :  string;
	questionOrder:number
	totalQuestions :  string;
	categoryId :  string;
	categoryName :  string;
	positiveMarkObtained :  string;
	negativeMarkObtained :  string;
}
export class TestQuestion{
    id:string;
    testId:string;
    qbId:string;
    testSectionId:string;
    questionStatus:string;
    optionsCorrect:string;
    marksAwarded:string;
    markPerQuestion:string;
    negativeMarks:string;
    questionDescription:string;
    hasMultipleAnswers:string;
    isEnabled: string;
	createdBy: string;
	modifiedBy: string;
	createdDt: string;
    modifiedDt:  string;
    testQuestionOptions:Array<TestQuestionOption>
}
export class TestQuestionOption{
    id:string;
    testQnId:string;
    sameAsMaster:string;
    qbId:string;
    option1:string;
    option2:string;
    option3:string;
    option4:string;
    isCorrect:string;
    userPicked:string;
    isEnabled: string;
	createdBy: string;
	modifiedBy: string;
	createdDt: string;
    modifiedDt:  string;
}
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
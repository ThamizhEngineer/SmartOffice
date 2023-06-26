export class QuestionBank{
    id:string;
    questionCategoryName:string;
    difficultyLevel:string;
    noOfQuestions:string; 
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
    questionBankOptions?:Array<QuestionBankOption>

}
export class QuestionBankOption{
    id:string;
    qbId:string;
    questionCode:string;
    difficultyLevel:string;
    question:string;
    option:string;
    option1:string;
    option2:string;
    option3:string;
    option4:string;
    isCorrect:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}
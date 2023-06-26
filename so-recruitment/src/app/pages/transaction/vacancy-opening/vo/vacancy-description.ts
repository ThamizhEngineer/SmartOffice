export class VacancyDescription {
    id:                   number;
    jdCode:               string;
    summary:              string;
    yearsOfExp:           string;
    designation:          string;
    location:             string;
    salary:               string;
    role:                 string;
    responsibility:       string;
    qualification:        string;
    experience:           string;
    isEnabled:            string;
    createdBy:            string;
    createdDt:            string;
    modifiedBy:           string;
    modifiedDt:           string;
    vacancyDescriptionSkills: VacancyDescriptionSkill[];
}

export class VacancyDescriptionSkill {
    id:         number;
    jobDescId:  string;
    type:       string;
    name:       string;
    isEnabled:  string;
    createdBy:  string;
    createdDt:  string;
    modifiedBy: string;
    modifiedDt: string;
}

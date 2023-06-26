import { FAQ } from './faq';

export class FAQCategorye {
    id:          number;
    code:        string;
    name:        string;
    description: string;
    tfaqCount:   string;
    faqs:        FAQ[];
    isEnabled:   string;
    createdBy:   string;
    createdDt:   string;
    modifiedBy:  string;
    modifiedDt:  string;

    dummyIsName: boolean=true;
}


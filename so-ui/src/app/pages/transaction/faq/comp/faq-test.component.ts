import { Component, OnInit } from '@angular/core';
import { country } from '../vo/country'


@Component({
    selector: '',
    templateUrl: 'faq-test.component.html'
})


export class FaqComponent implements OnInit {
    
    display:string;
    saveCountry:[country];

    countries2 = [
        {
          id: 1,
          name: 'Albania',
        },
        {
          id: 2,
          name: 'Belgium',
        },        
      ];

   countries = [
    {
      value: 1,
      display: 'Albania',
    },
    {
      value: 2,
      display: 'Belgium',
    },
    {
      value: 3,
      display: 'Denmark',
    },
    {
      value: 4,
      display: 'Montenegro',
    },
    {
      value: 5,
      display: 'Turkey',
    },
    {
      value: 6,
      display: 'Ukraine',
    },
    {
      value: 7,
      display: 'Macedonia',
    },
    {
      value: 8,
      display: 'Slovenia',
    },
    {
      value: 9,
      display: 'Georgia',
    },
    {
      value: 10,
      display: 'India',
    },
    {
      value: 11,
      display: 'Russia',
    },
    {
      value: 12,
      display: 'Switzerland',
    }
  ];
    
    constructor() { }

    ngOnInit() { 
     this.saveCountry = [new country]
     this.saveCountry.splice(0,1);
    }


    onItemRemoved(event){
        console.log(event);
    }

    onItemAdded(event){
        console.log(event);
        let con = new country
        con.name=event.name
        if(event.id>0){
          con.id=event.id;
        }        
        this.saveCountry.push(con);
    }

   

      save(){
          console.log(this.saveCountry)
      }
}
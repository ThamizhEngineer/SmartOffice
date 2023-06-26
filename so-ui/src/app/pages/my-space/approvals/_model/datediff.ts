import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dtdiff'
})
export class DtdiffPipe implements PipeTransform {

	transform(date1: any, date2: any, s1: any, s2: any): any {
		if(!date1 || !date2 || !s1 || !s2) return 0;
		
		let one_day=1000*60*60*24;

		let date1_ms = new Date(date1).getTime();
		let date2_ms = new Date(date2).getTime();

		let difference_ms = date2_ms - date1_ms;

		let added_days = 0;
		added_days += (s1 == 'FN') ? 0.5 : 0;
		added_days += (s2 == 'AN') ? 0.5 : 0;

		return Math.round(difference_ms/one_day)+added_days; 
	}
}
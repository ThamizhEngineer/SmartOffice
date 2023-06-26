import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dtdiff'
})
export class DtdiffPipe implements PipeTransform {

	transform(date1: any, date2: any): any {
		if(!date1 || !date2 ) return 0;
		
		let one_day=1000*60*60*24;

		let date1_ms = new Date(date1).getTime();
		let date2_ms = new Date(date2).getTime();

		let difference_ms = date2_ms - date1_ms;

		return Math.round(difference_ms/one_day); 
	}
}
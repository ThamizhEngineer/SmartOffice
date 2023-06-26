import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timediff'
})
export class TimediffPipe implements PipeTransform {

	transform(startTime: any, endTime: any): any {
		if(!startTime || !endTime ) return 0;

		let time1_ms:any = new Date("January 1, 1970 " + startTime);;
        let time2_ms:any = new Date("January 1, 1970 " + endTime);
        
        console.log("time2_ms",time2_ms);
        console.log("time1_ms",time1_ms);

        let difference_ms = Math.abs(time2_ms - time1_ms);

        console.log("difference_ms",difference_ms);
        
        let hh:any = Math.floor(difference_ms / 1000 / 60 / 60);
        if(hh < 10) {
            hh = '0' + hh;
        }
        difference_ms -= hh * 1000 * 60 * 60;
        let mm:any = Math.floor(difference_ms / 1000 / 60);
        if(mm < 10) {
            mm = '0' + mm;
        }
        difference_ms -= mm * 1000 * 60;
        let ss:any = Math.floor(difference_ms / 1000);
        if(ss < 10) {
            ss = '0' + ss;
        }

        console.log("duration",hh + ":" + mm + ":" + ss);

		return hh + ":" + mm + ":" + ss; 
    }
}
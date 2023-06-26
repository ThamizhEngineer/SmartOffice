import { NgModule } from '@angular/core'

import { FormatTimePipe } from './formatTime.pipe';
import { OrderByPipe } from './orderBy.pipe';
import { TimediffPipe } from './timeDiff.pipe';

@NgModule({
    declarations:[
        FormatTimePipe,
        OrderByPipe,
        TimediffPipe
    ],
    exports:[
        FormatTimePipe,
        OrderByPipe,
        TimediffPipe
    ]
})

export class PipesModule{ }
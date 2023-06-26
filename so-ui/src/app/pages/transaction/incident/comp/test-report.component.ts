import { Component, OnInit, Directive, Input, Output, PipeTransform, EventEmitter, ViewChildren, QueryList } from '@angular/core';
import { IncidentService } from '../incident.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { Incident } from '../../../vo/incident';
import { status_css } from '../../../vo/status'

import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';


export type SortDirection = 'asc' | 'desc' | '';
const rotate: { [key: string]: SortDirection } = { 'asc': 'desc', 'desc': '', '': 'asc' };
export const compare = (v1, v2) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

export interface SortEvent {
  column: string;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class NgbdSortableHeader {

  @Input() sortable: string;
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({ column: this.sortable, direction: this.direction });
  }
}

// --------------------------------------------------------------

@Component({
  selector: 'test-report',
  templateUrl: 'test-report.component.html',
  providers: [DecimalPipe]
})

export class TestReportComponent implements OnInit {
  incidentTest: any = [];
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;
  sort: any = [];
  sorted: any = [{ historyExists: 0 }];
  filter = new FormControl('');
  searchString: string;
  masterSelected: boolean = false;
  decisionList: any = [];
  incident: any;
  advSearch: boolean = false;
  statuses: any = status_css;
  score: any = "0-100";
  mark: any = "0-100";
  startMark: number = 0;
  endMark: number = 100;
  startScore: number = 0;
  endScore: number = 100;
  incidentId: any;
  testResult: any;
  testStatus: any;
  page: number = 1;
  closeResult: string;
  errorMsg:any;
  saveMsg:any;
  pgNumber: number;
  pageSize: number = 10
  view: string;
  // incident:Incident;
  historyList: any[];
  filteredHistoryList: any[];
  dummyList: any = [];
  constructor(
    private service: IncidentService,
    private route: Router,
    private activedRouter: ActivatedRoute, private modalService: NgbModal) {
  }

  ngOnInit() {
    // this.incidents = new Incident();

    this.onInit();
    // this.finalDecision;
    // this.service.getIncidents().subscribe(x => {
    //   this.incidents = x;
    //   this.incidentId = this.incidents[0].id;
    //   // this.route.navigateByUrl('recruitment/event/recruitment/test-report/1')


    // })
  }


  onInit() {
    if (this.activedRouter.params['_value']['id']) {
      this.activedRouter.params.subscribe(params => {
        this.pgNumber = params.page;
        this.view = params.view
        this.service.getIncidentById(params.id).subscribe(x => {
          this.incident = x;
          console.log(this.incident)
        }, error => {
          console.log(error.status)
        })
        this.service.fetchTestReportByIncidentId(params.id).subscribe(x => {
          this.sort = x

          this.dummyList = [];
          for (let element of this.sort) {
            console.log(element.incidentId, element.participantId, element.incidentTtId)
            this.service.fetchTestHistoryByTestId(element.incidentId, element.participantId, element.incidentTtId).subscribe(th => {
              element.historyExists = th.length;
              element.historyList = th;
            }, error => {
              console.log(error)
              element.historyExists = 0;
            });

            element.selected = false;
            if (element.passPercentage >= element.score) {
              element.isPass = 'N'
            } else {
              element.isPass = 'Y'
            }
            if (element.score >= element.scoreMedian) {
              element.isGreater = 'Y';
            } else {
              element.isGreater = 'N';
            }
            this.dummyList.push(element);
          }
          this.sorted = this.dummyList;
          console.log(this.sorted)
        });
      });

    }
  }

  openModelShowHistory(content, history) {
    this.historyList = [];
    console.log(history)
    this.historyList = history
    this.modalService.open(content, { size: 'lg' });
  }

  reschedule(testReport) {
    console.log(testReport)
    for (var _i = 0; _i < this.incident.incidentApplicants.length; _i++) {
      var element = this.incident.incidentApplicants[_i];
      if (testReport.applicantId == element.applicantId) {
        element.isReallocate = "Y";
        element.reallocateMessage="not-started";
      }
    }

    console.log(this.incident)

    this.service.updateIncident(this.incident.id,'reallocate-test',this.incident).subscribe(x=>{
    },error => {
      console.log(error.status)
      console.log(error)
    })

  }

  // this.service.updateIncident(this.incident.id, 'reallocate-test', this.incident).subscribe(x => {
  // },error => {
  //   console.log(error.status)
  // })

  filterHistoryByFilter(incId, parId, ttId) {
    console.log(this.historyList)
    this.filteredHistoryList = this.historyList.filter(u =>
      u.incidentId == incId && u.participantId == parId && u.incidentTtId == ttId);
    console.log(JSON.stringify(this.filteredHistoryList));
    return JSON.stringify(this.filteredHistoryList);
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    if (direction === '') {
      this.sorted;
    } else {
      this.sorted = [...this.sorted].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }



  checkUncheckAll() {
    for (var i = 0; i < this.sorted.length; i++) {
      this.sorted[i].selected = this.masterSelected;
    }
  }

  isAllSelected() {
    this.masterSelected = this.sorted.every(function (mg1: any) {
      return mg1.selected == true;

    })
  }


  reset() {
    this.testResult = '';
    this.testStatus = '';
    this.score = "0-100"
    this.mark = "0-100"
    this.startScore = 0;
    this.endScore = 100;
    this.startMark = 0;
    this.endMark = 100;
    this.decisionList = [];
    this.sorted = [];
    this.sort = [];
    this.dummyList = [];
    this.onInit();
    // this.route.navigateByUrl('recruitment/event/recruitment/test-report/1')

  }

  search() {
    this.startScore = Number(this.score.split("-")[0])
    this.endScore = Number(this.score.split("-")[1])
    this.startMark = Number(this.mark.split("-")[0]);
    this.endMark = Number(this.mark.split("-")[1])
    this.sorted = [];
    this.service.fetchBewtweenScores(this.startScore, this.endScore, this.testResult, this.sort[0].incidentId, this.testStatus, this.startMark, this.endMark).subscribe(x => {
      for (let element of x) {
        element.selected = false;
        this.sorted.push(element);
      }
      //this.sorted=x;
    })
  }

  advanceSearch() {
    if (this.advSearch == false) {
      this.advSearch = true;
    } else if (this.advSearch == true) {
      this.advSearch = false;
    }
  }

  finalDecision(decision) {
    for (var i = 0; i < this.sorted.length; i++) {
      if (this.sorted[i].selected) {
        this.decisionList.push(this.sorted[i]);


      }
    }

    this.decisionList.forEach(element => {
      this.service.finalDecision(element.id, element.participantId, decision, this.sorted).subscribe(x => {
        this.ngOnInit();

      })
    });
    // this.sorted=[];
    this.reset();


  }

  textSearch() {
    this.sorted = this.sort.filter(s =>
      // (s.incidentName.includes(this.searchString))
      (s.incidentName.toLowerCase().includes(this.searchString.toLowerCase()))
      || (s.testTemplateName.toLowerCase().includes(this.searchString.toLowerCase()))
      || (s.applicantName.toLowerCase().includes(this.searchString.toLowerCase()))
      //  || (s.testStatus.toLowerCase().includes(this.searchString.toLowerCase()))
      //  || (s.totalCorrect && s.totalCorrect.toLowerCase().includes(this.searchString.toLowerCase()))
      //  || (s.totalUnAttended && s.totalUnAttended.toLowerCase().includes(this.searchString.toLowerCase()))
      //  || (s.totalWrong && s.totalWrong.toLowerCase().includes(this.searchString.toLowerCase()))

    );
  }

  navigateToList() {
    this.route.navigate(['/recruitment/event/' + this.view, { page: this.pgNumber }]);
  }


}
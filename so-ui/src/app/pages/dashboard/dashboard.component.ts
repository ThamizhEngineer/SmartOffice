import { Component, ErrorHandler, Input, OnInit, NgZone, OnChanges, ViewChild, ElementRef } from '@angular/core';
import { DashboardService } from './dashboard.service';
import helper from './dashboard-helper';
import { ChartOptions } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';
import { Color } from 'ng2-charts';


@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.css']
})
export class DashboardComponent {
	@ViewChild(BaseChartDirective) chart: BaseChartDirective;

	public barChartOptions: ChartOptions = {
		responsive: true,
		scaleShowVerticalLines: false,
		scales: { xAxes: [{}], yAxes: [{}] },
		plugins: {
			datalabels: {
				anchor: 'end',
				align: 'end',
			}
		}
	};

	public barChartLabels: string[] = [];
	public barChartType: string = 'bar';
	public barChartLegend: boolean = true;
	public barChartData: any[] = [];

	public doughnutChartType: string = 'doughnut';
	metrics;
	forecasts;

	revenuePerBu;
	revenuePerBuChartLabel: string[] = [];
	revenuePerBuChartData: number[] = [];

	revenuePerCustomer;
	revenuePerCustomerChartLabel: string[] = [];
	revenuePerCustomerChartData: number[] = [];
	isDir: boolean = false;
	attDash:any;

	constructor(private _service: DashboardService) {
		this.checkIfDir();
		this.starter();
	}

	public dayStatusLablel:string[] = ['checkIn', 'checkOut', 'Yet To CheckIn'];
	dayStatusData:any=[];
	dayStatusColor :any[] = [{backgroundColor: ["#33FF42", "#FFC300", "#33BEFF"]}]
	public dayStatusDesign:string = 'doughnut';

	public attendanceStatusLablel:string[] = ['Total Users', 'Present', 'Absent','Leave'];
	attendanceStatusData:any=[];
	attendanceStatusColor :any[] = [{backgroundColor: ["#3380FF", "#33FF4F", "#FF4F33", "#FFC300"]}]
	public attendanceStatusDesign:string = 'pie';

	public barChartColors: Color[] = [
		{ backgroundColor: 'blue' },
		{ backgroundColor: 'green' },
		{ backgroundColor: 'red' },
		{ backgroundColor: 'yellow' },
	  ]

	isHide:boolean=true;  
	isManager:string='N'
	ngOnInit() { 
		
	}

	private checkIfDir() {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		let userRoles = currentUser.userGroupMappings;
		let authUserRoles = currentUser.authUserRoles;		
		userRoles.forEach(x => {
			if (x.isDir == 'Y') {
				this.isDir = true;
				this.isHide=false;
			}else if(x.isHrL1 == 'Y'){
				this.isHide=false;
			}			
		});
		authUserRoles.forEach(element => {
			if(element.authRoleCode=='N1'){
				this.isHide=false;
				this.isManager='Y';
			}
		});

	}

	private attendanceDashboards(){
		this._service.getDashBordAttendance(this.isManager).subscribe(x=>{
			this.attDash=x.attendanceDashboard
			console.log(this.attDash);
		this.dayStatusData=[Number(this.attDash.checkIn), Number(this.attDash.checkOut), Number(this.attDash.yetToCheckIn)]
		this.attendanceStatusData= [Number(this.attDash.totalUsr), Number(this.attDash.present), Number(this.attDash.absent), Number(this.attDash.leave)];	
	});
	}

	starter() {
		this._service.getMetrics().subscribe(x => {
			this.metrics = x;
			this.metrics.forEach(y => {
				y.icon = helper.getIcons(y.featureCode);
			});
		}, error => {
			console.log(error)
		});
		if(this.isHide==false){
			this.attendanceDashboards();
		}
		if(this.isDir==true){
		this._service.getForecasts().subscribe(x => {
			x.sort(function (x, y) {
				let a = x.quarterName.toUpperCase(),
					b = y.quarterName.toUpperCase();
				return a == b ? 0 : a > b ? 1 : -1;
			});
			this.forecasts = x;
			this.formBarChartData();
		}, error => {
			console.log("Error fetching forecasts:", error)
		});

		this._service.getRevenuePerBu().subscribe(x => {
			this.revenuePerBu = x;
			this.formChartDataForRevenuePerBu();
		}, error => {
			console.log("Error fetching revenue per Bu:", error)
		});

		this._service.getRevenuePerCustomer().subscribe(x => {
			this.revenuePerCustomer = x;
			this.formChartDataForRevenuePerCustomer();
		}, error => {
			console.log("Error fetching revenue per customer:", error)
		});
	}
	}

	private formChartDataForRevenuePerBu() {
		if (this.revenuePerBu) {
			this.revenuePerBuChartLabel = [];
			this.revenuePerBuChartData = [];
			this.revenuePerBu.forEach(x => {
				this.revenuePerBuChartLabel.push(x.buName + " (lakhs)");
				this.revenuePerBuChartData.push(Number(x.totalRevenue));
			});
		}
	}

	private formChartDataForRevenuePerCustomer() {
		if (this.revenuePerCustomer) {
			this.revenuePerCustomerChartData = [];
			this.revenuePerCustomerChartLabel = [];
			this.revenuePerCustomer.forEach(x => {
				let clientName = x.clientName + " (lakhs)";
				this.revenuePerCustomerChartLabel.push(clientName);
				this.revenuePerCustomerChartData.push(Number(x.totalRevenue));
			});
		}
	}

	private formBarChartData() {
		let orderIntake = []; let actualRevenue = [];
		if (this.forecasts) {
			this.barChartLabels = [];
			this.barChartData = [];
			this.forecasts.forEach(x => {
				this.barChartLabels.push(x.quarterName);
				orderIntake.push(Number(x.orderIntake));
				actualRevenue.push(Number(x.actualRevenue))
			});
			let a1 = { data: orderIntake, label: 'orderIntake (lakhs)' };
			let a2 = { data: actualRevenue, label: 'actualRevenue (lakhs)' };
			this.barChartData.push(a1);
			this.barChartData.push(a2);
		}
	}
}
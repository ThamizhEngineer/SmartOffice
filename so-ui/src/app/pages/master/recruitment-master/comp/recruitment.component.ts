import { Component, OnInit } from '@angular/core';
import { RecruitmentService } from './../recruitment.service';
import { College,Degree,Course } from './../../../vo/RecruitmentMaster';
@Component({
    selector: 'recruitment-master',
    templateUrl: 'recruitment.component.html'
})

export class RecruitmentComponent implements OnInit {
    page :number = 1;
    pageSize :number = 8;
    cpage :number = 1;
    cpageSize :number = 8;
    cgpage :number = 1;
    cgpageSize :number = 8;
    coll:any=[];
    cour:any=[];
    deg:any=[];
    colleges: Array<College>;
    degrees: Array<Degree>;
    courses: Array<Course>;
    constructor(
        private service:RecruitmentService) {

     }

    ngOnInit() { 
        this.colleges = new Array<College>();
        this.degrees = new Array<Degree>();
        this.courses = new Array<Course>();
        this.onInit();
    }

    onInit(){
        this.service.getColleges().subscribe(x=>{
            this.colleges=x;
        })
        this.service.getDegrees().subscribe(x=>{
            this.degrees=x;
        })
        this.service.getCourses().subscribe(x=>{
            this.courses=x;
        })
    }

    addCollege(){
        let college = new College();
        this.colleges.push(college);
    }
    addDegree(){
        let degree = new Degree();
        this.degrees.push(degree);
    }
    addCourse(){
        let course = new Course();
        this.courses.push(course);
    }

    saveCollege() {
        console.log(this.colleges)
        this.coll=[];
        this.colleges.forEach(college=>{
            if(college.id==null){
               this.coll.push(college);
            }
        });
        this.service.postColleges(this.coll).subscribe(x=>{
            this.onInit();
        })
       
    }
    saveDegrees(){
        this.deg=[];
        this.degrees.forEach(degree=>{
            if(degree.id==null){
                this.deg.push(degree);
            }
        });
        this.service.postDegrees(this.deg).subscribe(x => {
            this.onInit();
        })
    }
    saveCourses(){
        this.cour=[];
        this.courses.forEach(course=>{
            if(course.id==null){
                this.cour.push(course);
            }
        });
        this.service.postCourses(this.cour).subscribe(x => {
            this.onInit();
        })
    }
    removeCollege(id,index){
        if(id!=null){
            var idString:string=(id).toString();
            this.colleges.splice(index, 1);
            this.service.deleteCollege(idString).subscribe(x => {
            })
        }else{
            this.colleges.splice(index, 1);
        }
    }
    removeDegree(id,index){
        if(id!=null){
            var idString:string=(id).toString();
            this.degrees.splice(index, 1);
            this.service.deleteDegree(idString).subscribe(x => {
            })
        }else{
            this.degrees.splice(index, 1);
        }
    }
    removeCourse(id,index){
        if(id!=null){
            var idString:string=(id).toString();
            this.courses.splice(index, 1);
            this.service.deleteCourse(idString).subscribe(x => {
            })
        }else{
            this.courses.splice(index, 1);
        }
    }
}
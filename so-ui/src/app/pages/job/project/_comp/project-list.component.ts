import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{ProjectService} from '../project.service';
@Component({
    selector: 'project-list',
    templateUrl: './project-list.component.html'
})

export class ProjectListComponent  implements OnInit{
    constructor(private router:Router,private projectService:ProjectService ){

    }
    rows:any;
    saveMsg:any;
    ngOnInit() {
    this.getProjects();
    }
    getProjects(){
        this.projectService.getProjects().subscribe(x=>{
            this.rows=x;
          
        });
            }
    // navigateToDetailPage(id:number){
    //     this.router.navigate(['job/project/project-detail/'] ,{queryParams:{flowType:"Edit Project",id:id}});
 
    // }
        editProject(_id:string){
        
              
        
                this.router.navigateByUrl("/job/project/project-detail/"+_id);   
               }
               deleteProject(id: any){
                this.projectService.deleteProject(id).subscribe(x=>{
                    this.saveMsg = {'type': 'success', 'text': "Sale Order Deleted Successfully"};
                    this.getProjects();
                },error=>{
                    this.saveMsg = {'type': 'danger', 'text': "Server Error"};
                });
            }
        }
        
        
   

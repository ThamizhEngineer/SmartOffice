import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { MaterialsService } from '../material.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Material,MaterialService } from '../../vo/meterial';
import { FormGroup,FormBuilder, Validators,FormControl } from '@angular/forms';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
    selector: '',
    templateUrl: 'material.component.html'
})

export class MaterialComponent implements OnInit {

    searchSkill = (text$: Observable<string>) =>
    text$.pipe(
        debounceTime(200),
        distinctUntilChanged(),
        map(term => (term === '' ? this.services
            : this.services.filter(v => v.serviceName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
    skillFormatter = (x: { serviceName: string }) => {x.serviceName};

    @ViewChild('goods') goods: TemplateRef<any>;
    @ViewChild('product') product: TemplateRef<any>;
    @ViewChild('consumables') consumables: TemplateRef<any>;
    @ViewChild('asset') asset: TemplateRef<any>;
    
    
    materials:any[];
    modalReference:any = null;
    material:Material;

    goodsForm:FormGroup;
    productForm:FormGroup;
    consumablesForm:FormGroup;
    assetForm:FormGroup;


    goodsName:FormControl;
    goodsUnitPrice:FormControl;
    goodsDesc:FormControl;
    goodsHsnCode:FormControl;

    productName:FormControl;
    productUnitPrice:FormControl;
    productDesc:FormControl;
    productHsnCode:FormControl;
    productManufacterId:FormControl;
    productFamilyId:FormControl;  
    productServiceName:FormControl;
    
    conName:FormControl;
    conUnitPrice:FormControl;
    conDesc:FormControl;
    conHsnCode:FormControl;
    conUnit:FormControl;
    conStockValue:FormControl;
    conFeeder:FormControl;
    conMotor:FormControl;
    conGenerator:FormControl;
    conLineEstimate:FormControl;
    conDiffLineEstimate:FormControl;
    conTrafo:FormControl;

    assetName:FormControl;
    assetUnitPrice:FormControl;
    assetDesc:FormControl;
    assetHsnCode:FormControl;
    assetUnit:FormControl;
    assetStockValue:FormControl;
    assetMake:FormControl;
    assetModel:FormControl;
    assetOwner:FormControl;

    services: any = [];


    constructor(
        private service:MaterialsService,
        private formBuilder: FormBuilder,
        private modalService: NgbModal,
        private commonService: CommonService) { }

    ngOnInit() {

        this.createFormControls();
        this.createForm();

        this.material= new Material();
        this.material.materialServices = [new MaterialService];
        this.service.getMaterials().subscribe(x=>{
            this.materials=x.content;
        })
        this.service.getServices().subscribe(x=>{
            this.services=x
        });
        
     }


    get getGoods(){
        if(this.materials){
            return this.materials.filter(x=>x.materialCategory=='goods');
        }else{
            return [];
        }                
     }

     get getProducts(){
        if(this.materials){
            return this.materials.filter(x=>x.materialCategory=='product');
        }else{            
            return [];
        }   
     }

     get getConsumables(){
        if(this.materials){
            return this.materials.filter(x=>x.materialCategory=='consumables');
        }else{
            return [];
        }  
     }
     
     get getAsset(){
        if(this.materials){
            return this.materials.filter(x=>x.materialCategory=='asset');
        }else{
            return [];
        }  
     }

     addService(){
         let service = new MaterialService();
         service.abilityName=''
         this.material.materialServices.push(service);
     }
     deleteService(i){
         this.material.materialServices.splice(i,1);
     }

     openByCategory(category,material){         
         this.material =new Material();
         this.material.materialCategory=category;
         if(category=='product'){
            this.material.materialServices = [new MaterialService];
         }         
         if(material){
            this.material=material;
         }         
        switch(category){
            case 'goods':
                this.modalReference = this.modalService.open(this.goods, {size: 'lg'});
                if(this.material){
                   this.goodsForm.patchValue({
                       goodsName:this.material.materialName,
                       goodsUnitPrice:this.material.unitPrice,
                       goodsDesc:this.material.materialDesc,
                       goodsHsnCode:this.material.hsnCode

                   })
                }
            break;

            case 'product':
                this.modalReference = this.modalService.open(this.product, {size: 'lg'});
                if(this.material){
                    this.productForm.patchValue({
                        productName:this.material.materialName,
                        productUnitPrice:this.material.unitPrice,
                        productDesc:this.material.materialDesc,
                        productHsnCode:this.material.hsnCode,
                        productManufacterId:this.productManufacterId,
                        productFamilyId:this.productFamilyId,                                          
                    })
                 }
                break;

            case 'consumables':
                this.modalReference = this.modalService.open(this.consumables, {size: 'lg'});
            break;

            case 'asset':
                this.modalReference = this.modalService.open(this.asset, {size: 'lg'});
            break;
        }
     }


     createFormControls(){
        this.goodsName = new FormControl('', [Validators.required]);
        this.goodsUnitPrice = new FormControl('', [Validators.required]);
        this.goodsDesc = new FormControl('', [Validators.required]);
        this.goodsHsnCode = new FormControl('', [Validators.required]);

        this.productName = new FormControl('', [Validators.required]);
        this.productUnitPrice = new FormControl('', [Validators.required]);
        this.productDesc = new FormControl('', [Validators.required]);
        this.productHsnCode = new FormControl('', [Validators.required]);
        this.productManufacterId = new FormControl('', [Validators.required]);
        this.productFamilyId = new FormControl('', [Validators.required]);        
        
  
        this.conName= new FormControl('', [Validators.required]);
        this.conUnitPrice= new FormControl('', [Validators.required]);
        this.conDesc= new FormControl('', [Validators.required]);
        this.conHsnCode= new FormControl('', [Validators.required]);
        this.conUnit= new FormControl('', [Validators.required]);
        this.conStockValue= new FormControl('', [Validators.required]);
        this.conFeeder= new FormControl('', [Validators.required]);
        this.conMotor= new FormControl('', [Validators.required]);
        this.conGenerator= new FormControl('', [Validators.required]);
        this.conLineEstimate= new FormControl('', [Validators.required]);
        this.conDiffLineEstimate= new FormControl('', [Validators.required]);
        this.conTrafo= new FormControl('', [Validators.required]);

        this.assetName= new FormControl('', [Validators.required]);
        this.assetUnitPrice= new FormControl('', [Validators.required]);
        this.assetDesc= new FormControl('', [Validators.required]);
        this.assetHsnCode= new FormControl('', [Validators.required]);
        this.assetUnit= new FormControl('', [Validators.required]);
        this.assetStockValue= new FormControl('', [Validators.required]);
        this.assetMake= new FormControl('', [Validators.required]);
        this.assetModel= new FormControl('', [Validators.required]);
        this.assetOwner= new FormControl('', [Validators.required]);
    }

     createForm(){
        this.goodsForm = new FormGroup({
            goodsName:this.goodsName,
            goodsUnitPrice:this.goodsUnitPrice,
            goodsDesc:this.goodsDesc,
            goodsHsnCode:this.goodsHsnCode
        })

        this.productForm = new FormGroup({
            productName:this.productName,
            productUnitPrice:this.productUnitPrice,
            productDesc:this.productDesc,
            productHsnCode:this.productHsnCode,
            productManufacterId:this.productManufacterId,
            productFamilyId:this.productFamilyId,            
        })

        // this.productForm = this.formBuilder.group({
        //     productServiceName:[null, Validators.required]
        // })

        this.consumablesForm = new FormGroup({
            conName:this.conName,
            conUnitPrice:this.conUnitPrice,
            conDesc:this.conDesc,
            conHsnCode:this.conHsnCode,
            conUnit:this.conUnit,
            conStockValue:this.conStockValue,
            conFeeder:this.conFeeder,
            conMotor:this.conMotor,
            conGenerator:this.conGenerator,
            conLineEstimate:this.conLineEstimate,
            conDiffLineEstimate:this.conDiffLineEstimate,
            conTrafo:this.conTrafo,
        })

        this.assetForm = new FormGroup({

            assetName:this.assetName,
            assetUnitPrice:this.assetUnitPrice,
            assetDesc:this.assetDesc,
            assetHsnCode:this.assetHsnCode,
            assetUnit:this.assetUnit,
            assetStockValue:this.assetStockValue,
            assetMake:this.assetMake,
            assetModel:this.assetModel,
            assetOwner:this.assetOwner

        })
     }


     save(){
         this.service.createMaterials(this.material).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
         },error=>{
             console.error(error);
         })
     }

     update(){
        this.service.updateMaterials(this.material.id,this.material).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
         },error=>{
             console.error(error);
         })   
     }
     delete(id){
        this.service.deleteMaterials(id).subscribe(x=>{
            this.ngOnInit(); 
        },error=>{
            console.error(error);
        })
     }

     onServiceSelect($event, i){
        console.log($event)
        this.material.materialServices[i].abilityId = $event.item.id
        this.material.materialServices[i].abilityName = $event.item.serviceName
        this.material.materialServices[i].abilityCode = $event.item.serviceCode
    }


}
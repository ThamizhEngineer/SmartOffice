package com.ss.smartoffice.soservice.master.productservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.material.Material;
import com.ss.smartoffice.soservice.master.material.MaterialRepo;

@RestController
@RequestMapping("master/products")
public class ProductsService {
@Autowired
MaterialRepo materialRepo;
@GetMapping
public Iterable<Material>getAllMaterials(@RequestParam (value="materialCategory")String materialCategory)throws SmartOfficeException{
	if(materialCategory.equals("product")) {
	return materialRepo.findByMaterialCategory(materialCategory);
	}
	return null;
	}

@GetMapping("/getAll")
public Iterable<Material> getMaterials() throws SmartOfficeException {
	
//	System.out.println("In getEmployee");
	return materialRepo.findAll();

}
}

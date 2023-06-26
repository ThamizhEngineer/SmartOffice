package com.ss.smartoffice.soservice.master.manufacturer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("master/product-families")
@Scope("prototype")
public class ProductFamilyService {
	
	@Autowired
	ProductFamilyRepo productFamilyRepo;
	
	@Autowired
	ProductFamilyService productFamilyService;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ProductFamily> getProductFamily() throws SmartOfficeException {
		return productFamilyRepo.findAll();

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<ProductFamily> getProductFamilyById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return productFamilyRepo.findById(id);

	}

	//@CrossOrigin(origins = "*")
	@PostMapping
	public ProductFamily addProductFamily(@RequestBody ProductFamily ProductFamily) throws SmartOfficeException {
		return productFamilyRepo.save(ProductFamily); 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public ProductFamily updateProductFamily(@RequestBody ProductFamily ProductFamily) throws SmartOfficeException {
		return productFamilyRepo.save(ProductFamily);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteProductFamily(@PathVariable(value = "id") int id) throws SmartOfficeException {
		productFamilyRepo.deleteById(id);

	}



}

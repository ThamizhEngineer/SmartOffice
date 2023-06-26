package com.ss.smartoffice.soservice.master.material;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;


@RestController
@RequestMapping("master/materials")
@Scope("prototype")
public class MaterialsService {
	
	@Autowired
	MaterialRepo materialRepo;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@Autowired
	CommonUtils commonUtils;
	
	//@CrossOrigin(origins = "*")
//	@GetMapping
//	public Iterable<Material> getAllMaterials()throws SmartOfficeException{
//		return materialRepo.findAll();
//		
//	}
	//@CrossOrigin(origins = "*")
	@GetMapping()
	public Iterable<Material> getMaterial(Model model, Pageable pageable,
			@RequestParam(value = "materialName",required = false) String materialName,
			@RequestParam(value = "materialCode", required = false) String materialCode,	
			@RequestParam(value = "materialCategory",required = false) String materialCategory)
			throws Exception {
		
		Page<Material> pages = materials(pageable,materialName,materialCode,materialCategory);
	       model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("materials", pages.getContent());
	        return pages;
	}
	

	public Page<Material> materials(Pageable pageable,String materialName,String materialCode,String materialCategory){
		boolean searchByMaterialName = false,searchByMaterialCategory = false, searchBymMaterialCode = false,searchByMaterialNameAndMaterialCode=false;
		
		if (materialName != null)
			searchByMaterialName = true;
		if (materialCode != null && !materialCode.isEmpty() )
				
			searchBymMaterialCode = true;
	
		if(materialName!=null&&!materialName.isEmpty()&&materialCode!=null&&!materialCode.isEmpty()) {
			searchByMaterialNameAndMaterialCode=true;	
		}
		if(materialCategory!=null) {
			searchByMaterialCategory=true;
		}
		if (searchBymMaterialCode && searchByMaterialName && searchByMaterialCategory) {
			return materialRepo.findByMaterialNameAndMaterialCodeAndMaterialCategory(pageable, materialCode, materialName,materialCategory);
		}  if (searchByMaterialName) {
			return materialRepo.findByMaterialName(pageable, materialName);
		} if (searchBymMaterialCode) {
			return materialRepo.findByMaterialCode(pageable, materialCode);
		}
		if(searchByMaterialCategory) {
			return materialRepo.findByMaterialCategory(pageable, materialCategory);
		}
        return materialRepo.findAll(pageable);
    }
	
	@GetMapping("/{id}")
	public Optional<Material> getMaterialById(@PathVariable(value = "id") int id) throws SmartOfficeException {
        return materialRepo.findById(id);

    }
	
	//@CrossOrigin(origins = "*")
	@PostMapping
	public Material addMaterial(@RequestBody Material Material)throws SmartOfficeException{
		HashMap<String, String> buisnessKeys = new HashMap<>();
		if(Material.getMaterialCategory().equals("goods")) {
			Material.setMaterialCode(sequenceGenerationService.nextSeq("GOODS",buisnessKeys));
		}
		if(Material.getMaterialCategory().equals("product")) {
			Material.setMaterialCode(sequenceGenerationService.nextSeq("PRODUCT",buisnessKeys));
		}
		if(Material.getMaterialCategory().equals("consumables")) {
			Material.setMaterialCode(sequenceGenerationService.nextSeq("CONSUMABLE",buisnessKeys));
		}
		if(Material.getMaterialCategory().equals("asset")) {
			Material.setMaterialCode(sequenceGenerationService.nextSeq("ASSET",buisnessKeys));
		}
		
		Material.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		Material.setCreatedDt(LocalDateTime.now());
		
		return materialRepo.save(Material);
		
	}
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public Material updateMaterial( @PathVariable(value = "id") Integer id,@RequestBody Material Material)throws SmartOfficeException{
		return materialRepo.save(Material);
		
	}
	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteMaterial(@PathVariable(value = "id") int id) throws SmartOfficeException {
		materialRepo.deleteById(id);

	}
}

package com.ss.smartoffice.soservice.seed.CtcConfigService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("seed/ctc-configs")
@Scope("prototype")
public class CtcConfigService {
	
	@Autowired
	CtcConfigRepository ctcConfigRepository;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<CtcConfig> getCtcConfig(@RequestParam(value="fiscalYearId",required=false)String fiscalYearId,
			@RequestParam(value="headerConfigCode",required=false)String headerConfigCode)throws SmartOfficeException{
		boolean searchByFiscalYearId=false; boolean searchByFiscalYearIdAndHeaderConfigCode=false;
		
		
		if(fiscalYearId!=null) {
			searchByFiscalYearId=true;
		}
		if(fiscalYearId!=null&& headerConfigCode!=null) {
			searchByFiscalYearIdAndHeaderConfigCode=true;
		}
		if(searchByFiscalYearId) {
			return ctcConfigRepository.findByFiscalYearId(fiscalYearId);
		}
		else if (searchByFiscalYearIdAndHeaderConfigCode) {
			return ctcConfigRepository.findByFiscalYearIdAndHeaderConfigCode(fiscalYearId, headerConfigCode);
			
		}
		return ctcConfigRepository.findAll();
		
	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<CtcConfig> getCtcConfigById(@PathVariable(value="id")int id)throws SmartOfficeException{
		return ctcConfigRepository.findById(id);
		
	}
	//@CrossOrigin(origins = "*")
	@PostMapping
	public CtcConfig addCtcConfigById(@RequestBody CtcConfig ctcConfig)throws SmartOfficeException{
		return ctcConfigRepository.save(ctcConfig);
		
	}
	
	@CrossOrigin(origins="*")
	@PatchMapping("/bulk-update")
	public Iterable<CtcConfig>  bulkaddAndUpdate(@RequestBody List<CtcConfig> ctcConfigs)throws SmartOfficeException{
		List<CtcConfig> ctcConfigList= new ArrayList<CtcConfig>();
		System.out.println(ctcConfigList);
		for(CtcConfig ctcConfig:ctcConfigs) {
			if(ctcConfig.getId()>0) {
				CtcConfig ctcConfigFromDB= ctcConfigRepository.findById(ctcConfig.getId()).orElse(new CtcConfig());
				ctcConfig=this.matchAndUpdateFields(ctcConfigFromDB,ctcConfig);
			}else {
				ctcConfig=addingNewRecord(ctcConfig);
			}
			ctcConfig.setIsEnabled("Y");
			ctcConfigList.add(ctcConfig);
		}
		System.out.println(ctcConfigList);
		return (Iterable<CtcConfig>)ctcConfigRepository.saveAll(ctcConfigList) ;
	}
		
		
	private CtcConfig addingNewRecord(CtcConfig ctcConfig) {
		// this Service will add New Record if there is no existing Record
		CtcConfig newCtcConfig= new CtcConfig();
		newCtcConfig.setFiscalYearId(ctcConfig.getFiscalYearId());
		newCtcConfig.setFiscalYearCode(ctcConfig.getFiscalYearCode());
		newCtcConfig.setHeaderConfigCode(ctcConfig.getHeaderConfigCode());
		newCtcConfig.setConfigCode(ctcConfig.getConfigCode());
		newCtcConfig.setConfigValue(ctcConfig.getConfigValue());
		
		return newCtcConfig;
	}
	private CtcConfig matchAndUpdateFields(CtcConfig ctcConfigFromDB, CtcConfig ctcConfig) {
		////only update relevant fields to the db-record
		ctcConfigFromDB.setId(ctcConfig.getId());
		ctcConfigFromDB.setFiscalYearId(ctcConfig.getFiscalYearId());
		ctcConfigFromDB.setFiscalYearCode(ctcConfig.getFiscalYearCode());
		ctcConfigFromDB.setHeaderConfigCode(ctcConfig.getHeaderConfigCode());
		ctcConfigFromDB.setConfigCode(ctcConfig.getConfigCode());
		ctcConfigFromDB.setConfigValue(ctcConfig.getConfigValue());
		return ctcConfigFromDB;
	}
	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteCtcConfigById(@PathVariable(value="id")int id)throws SmartOfficeException{
		ctcConfigRepository.deleteById(id);
	}
		
	
}

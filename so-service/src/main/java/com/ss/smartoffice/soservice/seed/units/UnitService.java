package com.ss.smartoffice.soservice.seed.units;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("seed/units")
public class UnitService {

	@Autowired
	UnitRepo unitRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<Units> getUnits() throws SmartOfficeException {
	
		return unitRepo.findAll();
	}
	
	@GetMapping("/{unitCode}")
	public Optional<Units> getunitsByName(@PathVariable(value = "unitCode") String unitCode) throws SmartOfficeException {
		return unitRepo.findById(unitCode);
	}
	
	@PostMapping
	public Units createUnits(@RequestBody Units unitsMaster) throws SmartOfficeException{		
		Units unitFromDb = unitRepo.findByUnitCode(unitsMaster.getUnitCode().toLowerCase());
		if(unitFromDb==null) {
			unitsMaster.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			unitsMaster.setCreatedDt(LocalDateTime.now());
			return unitRepo.save(unitsMaster);
		}else {
			throw new SmartOfficeException("Name Already present");
		}
		
	}
	
	@PatchMapping("/{unitCode}")
	public Units updateUnits(@PathVariable(value = "unitCode") String unitCode,@RequestBody Units unitsMaster) throws SmartOfficeException{
		Units unitFromDb = unitRepo.findById(unitCode).get();
		unitFromDb.setUnitCode(unitsMaster.getUnitCode());
		unitFromDb.setUnitName(unitsMaster.getUnitName());
		unitFromDb.setDispSymbol(unitsMaster.getDispSymbol());
		unitFromDb.setDescp(unitsMaster.getDescp());
		unitFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		unitFromDb.setModifiedDt(LocalDateTime.now());
		return unitRepo.save(unitFromDb);
	}
	
	@DeleteMapping("/{unitCode}")
	public void deleteUnits(@PathVariable(value = "unitCode") String unitCode) throws SmartOfficeException{		
		unitRepo.deleteById(unitCode);
	}
	
	
}

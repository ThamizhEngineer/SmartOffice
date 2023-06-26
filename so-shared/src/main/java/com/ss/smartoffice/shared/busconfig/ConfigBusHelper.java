package com.ss.smartoffice.shared.busconfig;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@Component
@Scope("prototype")
public class ConfigBusHelper {
	@Autowired
	ConfigRepository configRepository;
	
	
	public Iterable<Config> getConfig(String configDtlCode,String configHeaderCode)throws SmartOfficeException{
		boolean searchByConfigDtlCode=false,searchByconfigHeaderCode=false;
		
		
		
		
		if(configDtlCode!=null && !configDtlCode.isEmpty())
			searchByConfigDtlCode=true;
		if(configHeaderCode!=null && !configHeaderCode.isEmpty())
			searchByconfigHeaderCode=true;
		if(searchByConfigDtlCode&&searchByconfigHeaderCode) {
			return configRepository.findByConfigDtlCodeAndConfigHeaderCode(configDtlCode, configHeaderCode);
		}else if (searchByConfigDtlCode) {
			return configRepository.findByConfigDtlCode(configDtlCode);
		}else if (searchByconfigHeaderCode) {
		  return configRepository.findByConfigHeaderCode(configHeaderCode);	
		}
		return configRepository.findAll();
		
	}
	
	public Iterable<Config> bulkUpdate(List<Config> configs)throws SmartOfficeException{
		List<Config> configList = new ArrayList<Config>();
		System.out.println(configs);
		for(Config config:configs) {
			if(config.getId()>0) {
				Config configFromDB= configRepository.findById(config.getId()).orElse(new Config());
				System.out.println(configFromDB);
				config=this.matchAndRelavantFields(configFromDB,config);
			}else {
				config=identifyRelavantFields(config);
			}
			config.setConfigHeaderIsEnabled("Y");
			config.setIsEnabled("Y");
			configList.add(config);
				
		}
		System.out.println(configList);
		return (Iterable<Config>) configRepository.saveAll(configList);
		
	}

	private Config identifyRelavantFields(Config config) {
		// this service is meant to update specific fields only. hence this function will ignore irrelevant fields
		Config newConfig = new Config();
		newConfig.setConfigDtlCode(config.getConfigDtlCode());
		newConfig.setConfigHeaderCode(config.getConfigHeaderCode());
		newConfig.setConfigDtlName(config.getConfigDtlName());
		newConfig.setConfigDtlValue(config.getConfigDtlValue());
		newConfig.setConfigHeaderName(config.getConfigHeaderName());
		newConfig.setConfigHeaderRemarks(config.getConfigHeaderRemarks());
		return newConfig;
	}

	private Config matchAndRelavantFields(Config configFromDB, Config config) {
		// only update relevant fields to the db-record
		configFromDB.setId(config.getId());
		configFromDB.setConfigDtlCode(config.getConfigDtlCode());
        configFromDB.setConfigHeaderCode(config.getConfigHeaderCode());
        configFromDB.setConfigDtlName(config.getConfigDtlName());
        configFromDB.setConfigDtlValue(config.getConfigDtlValue());
        configFromDB.setConfigHeaderName(config.getConfigHeaderName());
        configFromDB.setConfigHeaderRemarks(config.getConfigHeaderRemarks());
		return configFromDB;
	}
	

	public Iterable<Config> getConfigInternal(String configDtlCode,String configHeaderCode)throws SmartOfficeException{
		boolean searchByConfigDtlCode=false,searchByconfigHeaderCode=false;
		if(configDtlCode!=null)
			searchByConfigDtlCode=true;
		if(configHeaderCode!=null)
			searchByconfigHeaderCode=true;
		if(searchByConfigDtlCode&&searchByconfigHeaderCode) {
			return configRepository.findByConfigDtlCodeAndConfigHeaderCode(configDtlCode, configHeaderCode);
		}else if (searchByConfigDtlCode) {
			return configRepository.findByConfigDtlCode(configDtlCode);
		}else if (searchByconfigHeaderCode) {
		  return configRepository.findByConfigHeaderCode(configHeaderCode);	
		}
		return configRepository.findAll();
				
	}
}

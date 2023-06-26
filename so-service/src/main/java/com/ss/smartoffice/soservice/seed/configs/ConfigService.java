package com.ss.smartoffice.soservice.seed.configs;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.busconfig.ConfigBusHelper;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("seed/configs")
@Scope("prototype")
public class ConfigService {
	@Autowired
	ConfigBusHelper configBusHelper;
	
	@GetMapping
	public Iterable<Config> getConfig(@RequestParam(value="configDtlCode",required=false)String configDtlCode,@RequestParam(value="configHeaderCode",required=false)String configHeaderCode)throws SmartOfficeException{
		return configBusHelper.getConfig(configDtlCode, configHeaderCode);
		
	}
	
	@PatchMapping("/bulk-update")
	public Iterable<Config> bulkUpdate(@RequestBody List<Config> configs)throws SmartOfficeException{
		return configBusHelper.bulkUpdate(configs);
	}

}

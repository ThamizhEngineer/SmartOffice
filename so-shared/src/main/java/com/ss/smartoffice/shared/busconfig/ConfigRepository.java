package com.ss.smartoffice.shared.busconfig;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.Config;
@Scope("prototype")
public interface ConfigRepository extends CrudRepository<Config, Integer> {
	List<Config>findByConfigDtlCode(String configDtlCode);
	List<Config> findByConfigHeaderCode(String configHeaderCode);
	List<Config> findByConfigDtlCodeAndConfigHeaderCode(String configDtlCode,String configHeaderCode);

}

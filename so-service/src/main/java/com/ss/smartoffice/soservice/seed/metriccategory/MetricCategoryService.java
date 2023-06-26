package com.ss.smartoffice.soservice.seed.metriccategory;

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

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("/seed/metric-categrories")
public class MetricCategoryService {
@Autowired
MetricCategoryRepo metricCategoryRepo;

@GetMapping
public Iterable<MetricCategory>getMetricCategories()throws SmartOfficeException{
	return metricCategoryRepo.findAll();
}
@GetMapping("/{id}")
public Optional<MetricCategory>getMetricCategoryById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return metricCategoryRepo.findById(id);
}
@PostMapping
public MetricCategory addMetricCategory(@RequestBody MetricCategory metricCategory)throws SmartOfficeException{
	return metricCategoryRepo.save(metricCategory);
}
@PatchMapping("/{id}/update")
public MetricCategory updateMetricCategory(@PathVariable(value="id")Integer id,@RequestBody MetricCategory metricCategory)throws SmartOfficeException{
	return metricCategoryRepo.save(metricCategory);
}
@DeleteMapping("/{id}/delete")
public void deleteMetricCategory(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	metricCategoryRepo.deleteById(id);
}




}

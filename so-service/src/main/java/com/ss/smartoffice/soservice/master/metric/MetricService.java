package com.ss.smartoffice.soservice.master.metric;

import java.util.HashMap;
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
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping("master/metrics")
public class MetricService {
	@Autowired
	MetricRepo metricRepo;
	@Autowired
	SequenceGenerationService sequenceGenerationService;

	@GetMapping
	public Iterable<Metric> getAllMetric() throws SmartOfficeException {
		return metricRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Metric> getMetricById(@PathVariable(value = "id") String id) throws SmartOfficeException {
		return metricRepo.findById(id);
	}

	@PostMapping
	public Metric addMetric(@RequestBody Metric metric) throws SmartOfficeException {
		HashMap<String, String> buisnessKeys = new HashMap<>();
		metric.setId(sequenceGenerationService.nextSeq("METRIC-ID",buisnessKeys ));
		return metricRepo.save(metric);
	}

	@PatchMapping("/products/{id}")
	public Metric saveMetric(@RequestBody Metric metric, @PathVariable Integer id) {
		return metricRepo.save(metric);
//		 "successfully updated";
	}

	@DeleteMapping("/products/{id}")
	public String deleteMetricById(@PathVariable (value = "id")String id) {
		metricRepo.deleteById(id);
		return "Successfully deleted";
	}
}

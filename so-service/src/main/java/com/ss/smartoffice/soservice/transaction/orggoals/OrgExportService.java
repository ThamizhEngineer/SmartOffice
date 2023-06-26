package com.ss.smartoffice.soservice.transaction.orggoals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.ExportOrg;
import com.ss.smartoffice.shared.model.OrgHeader;

@Controller
@RequestMapping("transaction/appraisal/export")
public class OrgExportService {
	@Autowired
	OrgHeaderRepository orgHeaderRepository;
	@GetMapping("/export-result/{fyYearId}")
	public List<ExportOrg> exportCsv(Model model,@PathVariable(value="fyYearId")String fyYearId)throws SmartOfficeException{
		List<ExportOrg>orgList=orgHeaderRepository.findByFyYearIdList(fyYearId);		
		Map<String, String> printAttributes = new LinkedHashMap<String,String>();
		printAttributes.put("Fiscal Year ", "fyYearCode");
		printAttributes.put("Employee Name", "empName");
		printAttributes.put("Metric Name", "metricName");
		printAttributes.put("Operator", "operator");
		printAttributes.put("Metric Value", "metricValue");
		printAttributes.put("Metric Category Name", "metricCategoryName");
		printAttributes.put("Metric Level Code", "metricLevelCode");
		printAttributes.put("Description", "descp");
		printAttributes.put("Metric Summary", "metricSummary");
		
		
		
	
		model.addAttribute("dataKeyName", "exportOrgList");
		
		model.addAttribute("dataType", "ExportOrg");
		
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "org-goal-list");
		return orgList;
		
	}
}

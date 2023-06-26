package com.ss.smartoffice.sodocumentservice.printer;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sodocumentservice.model.SkillMatrixResult;

@RestController
@RequestMapping(path="documents/skill-matrix/")
public class SkillMatrixService {
	
	@Autowired
	SkillMatrixDocGenerator docGenerator;

	@PostMapping("print/{type}")
	public Boolean generateDoc(@RequestBody List<SkillMatrixResult> obj, @PathVariable(value = "type") String type) throws SmartOfficeException, DocumentException, IOException {
		try {
			docGenerator.generateDoc(obj, type);
			return true;
		} catch (SmartOfficeException e) {
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}

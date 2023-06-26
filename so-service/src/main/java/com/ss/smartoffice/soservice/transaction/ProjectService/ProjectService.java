package com.ss.smartoffice.soservice.transaction.ProjectService;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping("transaction/projects")
@Scope("prototype")
public class ProjectService {
@Autowired
ProjectRepository projectRepository;
@Autowired
SequenceGenerationService sequenceGenerationService;
@Autowired
CommonUtils commonUtils;

private static final String key = "AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg";
//	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<Project> getProjects()throws SmartOfficeException{				
		return projectRepository.findAll();
		
	}
//	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<Project> getProjectById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return projectRepository.findById(id);
		
	}
//	@CrossOrigin(origins="*")
	@PostMapping
	public Project addProject(@RequestBody Project project)throws Exception{
		HashMap<String, String> buisnessKeys = new HashMap<>();
		project.setProjCode(sequenceGenerationService.nextSeq("PROJECT", buisnessKeys));
		project.setCreatedBy(commonUtils.getLoggedinUserId());
		project.setIsEnabled("Y");
		return projectRepository.save(project);
		
	}
//	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public Project updateProjectById(@RequestBody Project project)throws SmartOfficeException{
		return projectRepository.save(project);
		
	}
//	@CrossOrigin(origins="*")
	@PatchMapping("/bulk-update")
	public Iterable<Project> updateProjects(@RequestBody List<Project>projects)throws SmartOfficeException{
		List<Project> projectList= new ArrayList<Project>();
		for(Project project:projects) {
			if(project.getId()!=null&&project.getId()>0) {
				Project projectFromDB= projectRepository.findById(project.getId()).orElse(new Project());
				project=this.matchAndUpdateFields(projectFromDB,project);
			}else {
				project=addingNewRecord(project);
			}
			project.setIsEnabled("Y");
			projectList.add(project);
		}
		System.out.println(projectList);
		return (Iterable<Project>)projectRepository.saveAll(projectList);
		
	}
	
	private Project addingNewRecord(Project project) {
		Project newProject= new Project();
		newProject.setProjName(project.getProjName());
		newProject.setProjCode(project.getProjCode());
		
		return newProject;
	}
	private Project matchAndUpdateFields(Project projectFromDB, Project project) {
		projectFromDB.setId(project.getId());
		projectFromDB.setProjName(project.getProjName());
		projectFromDB.setProjCode(project.getProjCode());
		
		return projectFromDB;
	}
//	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteProjectById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		projectRepository.deleteById(id);
	}
	
//	public Map<String, String> convertCoordsToAddress(String latitude, String longitude, String key) throws Exception {
//		String city = "";
//		String state, country = "";
//		Map<String, String> locationMap = new HashMap<String, String>();
//		int responseCode = 0;
//		String api = "https://maps.googleapis.com/maps/api/geocode/xml?latlng=" + latitude + "," + longitude + "&key="
//				+ key + "&sensor=true/false";
//	
//		URL url = new URL(api);
//		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//		httpConnection.connect();
//		responseCode = httpConnection.getResponseCode();
//	
//		if (responseCode == 200) {
//			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//	
//			Document document = builder.parse(httpConnection.getInputStream());
//	
//			document.getDocumentElement().normalize();
//			NodeList nList = document.getElementsByTagName("address_component");
//	
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//				Node nNode = nList.item(temp);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					if (eElement.getElementsByTagName("type").item(0).getTextContent().equals("locality")) {
//						city = eElement.getElementsByTagName("long_name").item(0).getTextContent();
//						locationMap.put("city", city);
//					}
//					if (eElement.getElementsByTagName("type").item(0).getTextContent()
//							.equals("administrative_area_level_1")) {
//						state = eElement.getElementsByTagName("long_name").item(0).getTextContent();
//						locationMap.put("state", state);
//					}
//					if (eElement.getElementsByTagName("type").item(0).getTextContent().equals("country")) {
//						country = eElement.getElementsByTagName("long_name").item(0).getTextContent();
//						locationMap.put("country", country);
//					}
//				}
//			}
//		}
//	
//		return locationMap;
//	}
	
	}

package com.ss.smartoffice.healthcheck.service;


//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.*;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.healthcheck.model.HealthCheck;

@RestController
@RequestMapping("health-check/startup")
public class SoHealthCheckService {
	
	@Value("${db.drive}")
	private String JDBC_DRIVER;
	
	@Value("${db.url}")
	private String DB_URL;	
	@Value("${db.username}")
	private String USER;
	@Value("${db.password}")
	private String PASS;
	
	@Value("${service.url}")
	private String SO_SERVICE_URL;
	
	@Value("${auth-service.url}")
	private String SO_AUTH_URL;
	
	@Value("${document-service.url}")
	private String SO_DOCUMENT_URL;
	
	@Value("${notification-service.url}")
	private String SO_NOTIFICATION_URL;
	
	@Value("${report-service.url}")
	private String SO_REPORT_URL;
	
	@Value("${hc.location}")
	private String HC_LOCATION;
	
	@Value("${kf.location}")
	private String KF_Location;
	
	@Value("${kf.port}")
	private String KF_PORT;
	
	static final String CSV_SEPARATOR = ",";
	
	@GetMapping
	public List<HealthCheck> startUpService() throws JsonGenerationException, JsonMappingException, IOException {
		
		System.out.println("TEST-1");
		List<HealthCheck> healthCheckList = new ArrayList<HealthCheck>();
		HealthCheck dbCheck = dbCheck();
		HealthCheck authCheck = checkAuthService();
		HealthCheck docCheck = checkDocumentService();
		HealthCheck notifCheck = checkNotifService();
		HealthCheck businessCheck = checkSoService();
		HealthCheck reportCheck = checkReportService();
		HealthCheck kafkaCheck = checkKafka();
		
		healthCheckList.add(dbCheck);
		healthCheckList.add(authCheck);
		healthCheckList.add(docCheck);
		healthCheckList.add(notifCheck);
		healthCheckList.add(businessCheck);
		healthCheckList.add(reportCheck);
		healthCheckList.add(kafkaCheck);
		
        ObjectMapper mapper = new ObjectMapper();
        String preetyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(healthCheckList);
		
		try(FileWriter file = new FileWriter(HC_LOCATION)) {
			file.write(preetyJson);
			file.flush();
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		

		return healthCheckList;
	
	}
		
//		-----------End---------------------------------------------

	public HealthCheck dbCheck() {
		Connection conn = null;
		Statement stmt = null;
		HealthCheck healthCheck = new HealthCheck();

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT COUNT(*) FROM s_ctc_config";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String count = rs.getString("COUNT(*)");
				if (!count.isEmpty() && count != null) {
					healthCheck.setType("database");
					healthCheck.setCode("SO-DB");
					healthCheck.setDescription("Database");
					healthCheck.setStatus("UP");
					healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			healthCheck.setType("database");
			healthCheck.setCode("SO-DB");
			healthCheck.setDescription("Database");
			healthCheck.setStatus("DOWN");
			healthCheck.setMessage(ex.getMessage());
			healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException ex1) {

			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return healthCheck;
	}
	
	public HealthCheck checkAuthService() {
		
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HealthCheck healthCheck = new HealthCheck();
		try {
        	HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = rest.exchange(SO_AUTH_URL,HttpMethod.GET, entity, String.class);       
            String result = response.getBody();
            if(!result.isEmpty() || result!=null) {
            	healthCheck.setType("service");
            	healthCheck.setCode("so-auth-serv");
            	healthCheck.setDescription("Authorisation Service");
            	healthCheck.setStatus("UP");
            	healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
            }
            
        }catch(Exception e) {
        	e.printStackTrace();
        	healthCheck.setType("service");
        	healthCheck.setCode("so-auth-serv");
        	healthCheck.setDescription("Authorisation Service");
        	healthCheck.setStatus("DOWN");
        	healthCheck.setMessage(e.getMessage());
        	healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
        }
		return healthCheck;
	}
	
	public HealthCheck checkSoService() {
		HealthCheck healthCheck = new HealthCheck();
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "token");
        
        try {
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> response = rest.exchange(SO_SERVICE_URL,HttpMethod.GET, entity, String.class);;
            String result = response.getBody();
            System.out.println("so-bus-serv-->"+result);
            if(!result.isEmpty() || result!=null) {
            	healthCheck.setType("service");
        		healthCheck.setCode("so-bus-serv");
        		healthCheck.setDescription("Business Service");
        		healthCheck.setStatus("UP");
        		healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
            }
            } catch(Exception e) {
            	e.printStackTrace();            	           
            		healthCheck.setType("service");
            		healthCheck.setCode("so-bus-serv");
            		healthCheck.setDescription("Business Service");
            		healthCheck.setStatus("DOWN");
            		healthCheck.setMessage(e.getMessage());
            		healthCheck.setLastCheckedDate(LocalDateTime.now().toString());            	
            }
        
		return healthCheck;		
	}
	public HealthCheck checkDocumentService() {
		HealthCheck healthCheck = new HealthCheck();
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "token");
        
        try {
        	HttpEntity<String> entity = new HttpEntity<String>(headers);
        	ResponseEntity<String> response = rest.exchange(SO_DOCUMENT_URL,HttpMethod.GET, entity,String.class);
        	String result = response.getBody();
        	System.out.println("SO-DOCUMENT-RESULT-->  "+result);
        	 if(!result.isEmpty() || result!=null) {
        		 healthCheck.setType("service");
        		 healthCheck.setCode("so-doc-serv");
        		 healthCheck.setDescription("Document Service");
        		 healthCheck.setStatus("UP");
        		 healthCheck.setLastCheckedDate(LocalDateTime.now().toString()); 
        	 }
        } catch(Exception e) {
        	e.printStackTrace();        	        	
        		 healthCheck.setType("service");
        		 healthCheck.setCode("so-doc-serv");
        		 healthCheck.setDescription("Document Service");
        		 healthCheck.setStatus("DOWN");
        		 healthCheck.setMessage(e.getMessage());
        		 healthCheck.setLastCheckedDate(LocalDateTime.now().toString());        	
        }
		return healthCheck;
	}
	public HealthCheck checkNotifService() {
		HealthCheck healthCheck = new HealthCheck();
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "token");
        
        try {
        	HttpEntity<String> entity = new HttpEntity<String>(headers);
        	ResponseEntity<String> response = rest.exchange(SO_NOTIFICATION_URL,HttpMethod.GET, entity,String.class);
        	String result = response.getBody();
        	System.out.println("SO-NOTIFICATION-RESULT-->  "+result);
        	if(!result.isEmpty() || result!=null) {
        		healthCheck.setType("service");
        		healthCheck.setCode("so-not-serv");
        		healthCheck.setDescription("Notofication Service");
        		healthCheck.setStatus("UP");
        		healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
        	}
        }catch(Exception e) {
        	e.printStackTrace();        	        	
        		healthCheck.setType("service");
        		healthCheck.setCode("so-not-serv");
        		healthCheck.setDescription("Notofication Service");
        		healthCheck.setStatus("DOWN");
        		healthCheck.setMessage(e.getMessage());
        		healthCheck.setLastCheckedDate(LocalDateTime.now().toString());        	
        }
		return healthCheck;
	}
	
	public HealthCheck checkReportService() {
		HealthCheck healthCheck = new HealthCheck();
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "token");
        
        try {
        	HttpEntity<String> entity = new HttpEntity<String>(headers);
        	ResponseEntity<String> response = rest.exchange(SO_REPORT_URL,HttpMethod.GET, entity,String.class);
        	String result = response.getBody();
        	System.out.println("SO-NOTIFICATION-RESULT-->  "+result);
        	if(!result.isEmpty() || result!=null) {
        		healthCheck.setType("service");
        		healthCheck.setCode("so-report-serv");
        		healthCheck.setDescription("Report Service");
        		healthCheck.setStatus("UP");
        		healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
        	}
        }catch(Exception e) {
        	e.printStackTrace();        	        	
        		healthCheck.setType("service");
        		healthCheck.setCode("so-report-serv");
        		healthCheck.setDescription("Report Service");
        		healthCheck.setStatus("DOWN");
        		healthCheck.setMessage(e.getMessage());
        		healthCheck.setLastCheckedDate(LocalDateTime.now().toString());        	
        }
		return healthCheck;
	}
	
	public HealthCheck checkKafka() {
		HealthCheck healthCheck = new HealthCheck();

		Properties properties = new Properties();
		properties.put("bootstrap.servers", KF_Location+":"+KF_PORT);
		properties.put("connections.max.idle.ms", 10000);
		properties.put("request.timeout.ms", 5000);
		try (AdminClient client = KafkaAdminClient.create(properties)) {
			ListTopicsResult topics = client.listTopics();
			Set<String> names = topics.names().get();

			if (!names.isEmpty()) {
				System.out.println("KAFKA-RUNNING");
				healthCheck.setType("message-broker");
				healthCheck.setCode("Kafka");
				healthCheck.setDescription("Kafka Message Broker");
				healthCheck.setStatus("UP");
				healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
			}
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("catch-->" + e.getMessage());
			System.out.println("catch1-->" + e.getLocalizedMessage());
			if (e.getLocalizedMessage().equals(
					"org.apache.kafka.common.errors.TimeoutException: Timed out waiting for a node assignment.")) {
				System.out.println("KAFKA-DOWN");
				healthCheck.setType("message-broker");
				healthCheck.setCode("Kafka");
				healthCheck.setDescription("Kafka Message Broker");
				healthCheck.setStatus("DOWN");
				healthCheck.setMessage(e.getMessage());
				healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
			} else {
				System.out.println("KAFKA-DOWN");
				healthCheck.setType("message-broker");
				healthCheck.setCode("Kafka");
				healthCheck.setDescription("Kafka Message Broker");
				healthCheck.setStatus("DOWN");
				healthCheck.setMessage(e.getMessage());
				healthCheck.setLastCheckedDate(LocalDateTime.now().toString());
			}
		}
		return healthCheck;
	}
}



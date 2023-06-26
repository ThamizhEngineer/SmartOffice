//package com.ss.smartoffice.sonotificationservice.order;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.ss.smartoffice.shared.common.CommonUtils;
//import com.ss.smartoffice.shared.model.SmartOfficeException;
//import com.ss.smartoffice.shared.model.Event;
//import com.ss.smartoffice.sonotificationservice.common.SmsHelper;
//
//import com.ss.smartoffice.sonotificationservice.model.PayrollHeader;
//import com.ss.smartoffice.sonotificationservice.model.Employee;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//
//@Service
//public class OrderCreationEventProcessor {
//	
//	@Autowired
//	SmsHelper smsHelper;
//	@Autowired
//	CommonUtils commonUtils;
//	
//	@Value("${payroll.url}")
//	private String payrollUrl;
//	
//	@Value("${employee.url}")
//	private String employeeUrl;
//	
//	public void process(Event event) {
//		String employeeName,message,employeephoneNumber,month="";
//		int year=0;
//		
//		if(event.getKeyValues()!=null) {
//			Map<String,String> keyValues= event.getKeyValues();
//			String payrollId=keyValues.get("payroll-id");
//			RestTemplate payrollRestTemplate = getTemplate();
//			PayrollHeader payrollHeader =payrollRestTemplate.getForObject(payrollUrl+payrollId, PayrollHeader.class);
//			if(payrollHeader!=null) {
//				if(payrollHeader.getEmpId()>0) {
//					RestTemplate employeeRestTemplate = getTemplate();
//					Employee employee =employeeRestTemplate.getForObject(employeeUrl+payrollHeader.getEmpId(), Employee.class);
//					if(employee!=null) {
//						employeeName=employee.getEmpName();
//						employeephoneNumber=employee.getPermContactNo();
//						month=CommonUtils.monthConversion(payrollHeader.getMonth()+"");
//						year=payrollHeader.getYear();
//						message = "Hi "+employeeName+" payroll for the month "+month+" "+year+" is generated. Please check smart office for more details.";						
//						smsHelper.sendSms(employeephoneNumber, message);
//						
//					}else {
//						throw new SmartOfficeException("employee is empty");
//					}
//					
//					
//				}else {
//					throw new SmartOfficeException("employee id is empty in payroll");
//				}
//			}else {
//				throw new SmartOfficeException("payroll is empty ");
//			}
//			
//		}else {
//			throw new SmartOfficeException("event keyvalues is empty ");
//		}
//		
//		
//		
//	}
//
//	
//	public RestTemplate getTemplate(){
//		RestTemplate restTemplate = new RestTemplate();
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//
//		//Add the Jackson Message converter
//		messageConverters.add(new MappingJackson2HttpMessageConverter());
//
//		//Add the message converters to the restTemplate
//		restTemplate.setMessageConverters(messageConverters); 
//
//		return restTemplate;
//	}
//}

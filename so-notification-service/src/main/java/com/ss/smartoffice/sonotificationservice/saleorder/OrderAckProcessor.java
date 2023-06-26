package com.ss.smartoffice.sonotificationservice.saleorder;

import java.util.Map;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.sonotificationservice.model.Customer;
import com.ss.smartoffice.sonotificationservice.model.SaleOrder;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;


@Service
public class OrderAckProcessor {
	@Value("${customer.url}")
	private String customerUrl;
	
	@Value("${saleOrder.url}")
	private String saleOrderUrl;

	@Autowired
	OrderAckEmailService orderAckEmailService;

	@Autowired
	CommonUtils commonUtils;
	
	

	public void process(Event event) {
		try {
			
			if (event.getNotificationKeys() != null) {
			
//				String customerId = event.getNotificationKeys().get(0).getCustomerId();
//				String purchaseOrderId = event.getNotificationKeys().get(0).getPoId();
//				String saleOrderId = event.getNotificationKeys().get(0).getSaleOrderId();
//				String saleOrderCode=event.getNotificationKeys().get(0).getSoCode();
//				String appToken=event.getAppToken();
//				System.out.println(appToken);
				Customer customer1 =new Customer();
				
//				Customer customerGet=  commonUtils.getRestTemplate().getForObject(customerUrl+customerId,
//						Customer.class);
//			
//				SaleOrder saleOrderGet=commonUtils.getRestTemplate().getForObject(saleOrderUrl+saleOrderId,
//						SaleOrder.class);
//				
//				System.out.println("saleOrderGet"+saleOrderGet);
//				System.out.println("saleOrderGet"+saleOrderGet.getPartnerName());
//				
//				try {
//					orderAckEmailService.sendMailUser(saleOrderGet,purchaseOrderId,saleOrderCode);
				
			} else {
				throw new SmartOfficeException("event keyvalues is empty ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

	}

}

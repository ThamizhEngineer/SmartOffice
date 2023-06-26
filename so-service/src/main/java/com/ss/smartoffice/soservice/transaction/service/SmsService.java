package com.ss.smartoffice.soservice.transaction.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SmsService {

	@Value("${sms.url}")
	private String smsUrl;

	private final String  apikey="2bBuACJnFCU-Sp3OkBDUGcFM8vxUXqbrVxHRPqjbnZ";
	
	public String sendSms(String soNumber,Long netAmount) {
		try {
			// Construct data
			String apiKey = "apikey=" +apikey ;
			String message = "&message=" + "Sale Order("+soNumber+") of value - Rs."+netAmount+" received. Please login to SmartOffice for more info.";
			String sender = "&sender=" + "TXTLCL";
			String numbers ="&numbers=9003544108";
			
			if(netAmount>=500000) {
				numbers += ",8754495488";	
			}
//			System.out.println("netAmount-"+netAmount);
//			System.out.println("netAmount-"+(netAmount>=500000));
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL(smsUrl).openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}

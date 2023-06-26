package com.ss.smartoffice.soservice.kafkaConsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class SmsHelper {
	
	@Value("${sms.url}")
	private String smsUrl;
	
	@Value("${sms-api-key}")
	private String apikey;
	
	@Value("${sms-sender-name}")
	private String smsSenderName;
	

	
	public String sendSms(String phoneNumber ,String message ) {
		try {
			// Construct data
			String apiKey = "apikey=" +apikey ;
			message = "&message=" +message;
			String sender = "&sender=" + smsSenderName;
			String numbers ="&numbers="+phoneNumber;
			
		

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
//		return smsId;
	}

}

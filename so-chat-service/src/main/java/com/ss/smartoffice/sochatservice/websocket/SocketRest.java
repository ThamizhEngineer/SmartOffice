package com.ss.smartoffice.sochatservice.websocket;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.ss.smartoffice.sochatservice.model.GroupChatLog;
import com.ss.smartoffice.sochatservice.model.IndividualChatLog;
import com.ss.smartoffice.sochatservice.repo.GroupChatLogRepo;
import com.ss.smartoffice.sochatservice.repo.IndividualChatLogRepo;

@RestController
@CrossOrigin("*")
public class SocketRest {

	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	IndividualChatLogRepo individualChatLogRepo;
	
	@Autowired
	GroupChatLogRepo groupChatLogRepo;

	@MessageMapping("/messagemap")
	@SendTo("/topic/messages")
	public Greeting send(HelloMessage message) throws Exception {
		System.out.println(message);
		String timestamp = LocalDateTime.now().toString();
		Thread.sleep(1000); // simulated delay
		return new Greeting(HtmlUtils.htmlEscape(message.getFromName()), message.getToName(),
				message.getMessageContent(), timestamp);
	}

	@MessageMapping("/ind-chat-logs")
	public void sendIndividual(IndividualChatLog message) throws Exception {
		System.out.println(message);
		message = formIndividualMessage(message);
		Thread.sleep(1000); // simulated delay
		template.convertAndSend("/topic/ind-chat-logs", message);
	}
	
	@MessageMapping("/group-chat-logs")
	public void sendGroup(GroupChatLog message) throws Exception {
		System.out.println(message);
		message = fromGroupMessage(message);
		Thread.sleep(1000); // simulated delay
		template.convertAndSend("/topic/group-chat-logs", message);
	}

	private IndividualChatLog formIndividualMessage(IndividualChatLog message) {
		message.setCreatedDt(LocalDateTime.now());
		message.setSentDt(LocalDateTime.now());
		message.setCreatedBy("Ind");
		individualChatLogRepo.save(message);
		return message;
	}
	
	private GroupChatLog fromGroupMessage(GroupChatLog message) {
		message.setCreatedDt(LocalDateTime.now());
		message.setSentDt(LocalDateTime.now());
		message.setCreatedBy("Grp");
		groupChatLogRepo.save(message);
		return message;
	}


}

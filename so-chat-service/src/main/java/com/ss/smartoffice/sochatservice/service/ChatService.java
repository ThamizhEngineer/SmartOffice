package com.ss.smartoffice.sochatservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.sochatservice.model.ChatGroup;
import com.ss.smartoffice.sochatservice.model.ChatGroupUser;
import com.ss.smartoffice.sochatservice.model.ChatUser;
import com.ss.smartoffice.sochatservice.model.GroupChatLog;
import com.ss.smartoffice.sochatservice.model.IndividualChatLog;
import com.ss.smartoffice.sochatservice.repo.ChatGroupRepo;
import com.ss.smartoffice.sochatservice.repo.ChatGroupUserRepo;
import com.ss.smartoffice.sochatservice.repo.ChatUserRepo;
import com.ss.smartoffice.sochatservice.repo.GroupChatLogRepo;
import com.ss.smartoffice.sochatservice.repo.IndividualChatLogRepo;

@Service
@RestController
@RequestMapping(path = "/chat")
public class ChatService {
	
	@Autowired
	ChatGroupRepo chatGroupRepo;
	@Autowired
	ChatUserRepo chatUserRepo;
	@Autowired
	IndividualChatLogRepo indChatLogRepo;
	@Autowired
	GroupChatLogRepo grpChatLogRepo;
	@Autowired
	ChatGroupUserRepo chatGrpUserRepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/chatgroups")
	public Iterable<ChatGroup> fetchAllChatGroup() {
		return chatGroupRepo.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/profile")
	public Iterable<ChatUser> fetchChatProfile(){
		return chatUserRepo.fetchOnlyEmp();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/groups/{authUserId}")
	public Iterable<ChatGroupUser> fetchChatGroups(@PathVariable(value = "authUserId")Integer authUserId){
		return chatGrpUserRepo.findByAuthUserId(authUserId);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/ind-chat-logs/{toAuthUserId}")
	public List<IndividualChatLog> fetchIndChatLogs(@PathVariable(value = "toAuthUserId")Integer toAuthUserId){
		return indChatLogRepo.findByToAuthUserId(toAuthUserId);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/ind-chat-logs/{fromAuthUserId}/{toAuthUserId}")
	public List<IndividualChatLog> fetchIndChatLogs(@PathVariable(value = "fromAuthUserId")Integer fromAuthUserId,@PathVariable(value = "toAuthUserId")Integer toAuthUserId){
		return indChatLogRepo.fetchCurrentChatLog(fromAuthUserId, toAuthUserId);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/group-chat-logs/{groupChatId}")
	public List<GroupChatLog> fetchGroupChatLogs(@PathVariable(value = "groupChatId")Integer groupChatId){
		return grpChatLogRepo.findByGroupChatId(groupChatId);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/ind-chat-logs")
	public IndividualChatLog postIndChatLog(IndividualChatLog indChatLog) {
		return indChatLogRepo.save(indChatLog);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/group-chat-logs")
	public GroupChatLog postGrpChatLog(GroupChatLog grpChatLog) {
		return grpChatLogRepo.save(grpChatLog);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/create-group-chat")
	public ChatGroup createGrpChat(@RequestBody ChatGroup chatGroup) {
		ChatGroup chatGroupFromDb = chatGroupRepo.findByGroupId(chatGroup.getGroupId());
		System.out.println(chatGroup);
		if(chatGroupFromDb==null) {
			return chatGroupRepo.save(chatGroup);
		}else {
			chatGroupFromDb.setGroupName(chatGroup.getGroupName());
			for(ChatGroupUser grpUser:chatGroup.getChatGroupUsers()) {
				if(chatGrpUserRepo.checkChatGroupUsers(chatGroup.getGroupId().toString(), grpUser.getAuthUserId())==0) {
					chatGroupFromDb.getChatGroupUsers().add(grpUser);
				}
			}
			return chatGroupRepo.save(chatGroupFromDb);
		}
		
		
	}
	
}

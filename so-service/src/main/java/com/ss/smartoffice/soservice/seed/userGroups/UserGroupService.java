package com.ss.smartoffice.soservice.seed.userGroups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.UserGroup;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;

@RestController
@RequestMapping("seed/user-groups")
@Scope("prototype")
public class UserGroupService {
	
	@Autowired
	UserGroupRepo userGroupRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	
	@GetMapping
	public Iterable<UserGroup> getUserGroup(
			@RequestParam(value = "isHrL1",required = false) String isHrL1,
			@RequestParam(value = "isHrL2",required = false) String isHrL2,
			@RequestParam(value = "isAcctL1",required = false) String isAcctL1,
			@RequestParam(value = "isAcctL2",required = false) String isAcctL2,
			@RequestParam(value = "userGroupName",required = false) String userGroupName) throws SmartOfficeException{
		return userGroupRepo.findBySummaries(isHrL1, isHrL2, isAcctL1, isAcctL2, userGroupName);
	}
	
	@GetMapping("/{id}")
	public Optional<UserGroup> getUserGroupById(@PathVariable(value="id")int id) throws SmartOfficeException{
		return userGroupRepo.findById(id);
	}
	
	@PostMapping
	public UserGroup addUserGroupById(@RequestBody UserGroup userGroup) throws SmartOfficeException{
		HashMap<String, String> buisnessKeys = new HashMap<>();
		userGroup.setUserGroupCode(sequenceGenerationService.nextSeq("USER-GROUP-CODE",buisnessKeys));
		userGroup.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
		return userGroupRepo.save(userGroup);
	}
	
	@PatchMapping("/{id}")
	public UserGroup updateUserGroupById(@RequestBody UserGroup userGroup)throws SmartOfficeException{
		userGroup.setModifiedBy(commonUtils.getAuthenticatedUser().getName());
		return userGroupRepo.save(userGroup);
	}
	
	@PatchMapping("/bulk-update")
	public Iterable<UserGroup> bulkAddandUpdate(@RequestBody List<UserGroup> userGroup) throws SmartOfficeException{
		List<UserGroup> groupList = new ArrayList<UserGroup>();
		for(UserGroup list: userGroup) {
			if(list.getId()>0) {
				UserGroup userGroupDB = userGroupRepo.findById(list.getId()).orElse(new UserGroup());
				userGroupDB = this.matchAndUpdateFields(userGroupDB,list);
			}else {
				list=addingNewRecord(list);
			}
			list.setModifiedBy(commonUtils.getAuthenticatedUser().getName());
			groupList.add(list);
			
		}
		return (Iterable<UserGroup>) userGroupRepo.saveAll(groupList);
	}
	
	private UserGroup addingNewRecord(UserGroup userGroup) {
		
		UserGroup newGroup = new UserGroup();
		newGroup.setId(userGroup.getId());
		newGroup.setIsHrL1(userGroup.getIsHrL1());
		newGroup.setIsHrL2(userGroup.getIsHrL2());
		newGroup.setIsAcctL1(userGroup.getIsAcctL1());
		newGroup.setIsAcctL2(userGroup.getIsAcctL2());
		newGroup.setUserGroupName(userGroup.getUserGroupName());
		newGroup.setDescription(userGroup.getDescription());
		return newGroup;
	}
	
	private UserGroup matchAndUpdateFields(UserGroup userGroupDB,UserGroup userGroup) {
		userGroupDB.setId(userGroup.getId());
		userGroupDB.setIsHrL1(userGroup.getIsHrL1());
		userGroupDB.setIsHrL2(userGroup.getIsHrL2());
		userGroupDB.setIsAcctL1(userGroup.getIsAcctL1());
		userGroupDB.setIsAcctL2(userGroup.getIsAcctL2());
		userGroupDB.setUserGroupName(userGroup.getUserGroupName());
		userGroupDB.setDescription(userGroup.getDescription());
		return userGroupDB;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserGroupById(@PathVariable(value="id")int id) throws SmartOfficeException{
		userGroupRepo.deleteById(id);
	}
	
	

}

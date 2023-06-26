package com.ss.smartoffice.shared.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path = "/transaction/comments")
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	private List<Comment> getComment(
			@RequestParam(value = "commentDesc",required = false) String commentDesc,
			@RequestParam(value = "userId",required = false) String userId,
			@RequestParam(value = "jobId",required = false) String jobId,
			@RequestParam(value = "employeeId",required = false) String employeeId,
			@RequestParam(value = "partnerId",required = false) String partnerId,
			@RequestParam(value = "endClientId",required = false) String endClientId,
			@RequestParam(value = "jobMilestoneId",required = false) String jobMilestoneId,
			@RequestParam(value = "jobTasklistId",required = false) String jobTasklistId,
			@RequestParam(value = "taskId",required = false) String taskId
			)throws SmartOfficeException{
		return commentRepository.findByComment(commentDesc, userId, jobId,
				employeeId, partnerId, endClientId, jobMilestoneId, 
				jobTasklistId, taskId);
	}
	@PostMapping
	private Comment addCommentLog(@RequestBody Comment comment)throws SmartOfficeException{
		return commentRepository.save(comment);
	}
}

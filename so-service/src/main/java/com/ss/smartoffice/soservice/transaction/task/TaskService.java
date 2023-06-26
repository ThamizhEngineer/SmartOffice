package com.ss.smartoffice.soservice.transaction.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.Comment.CommentRepository;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path = "/transaction/tasks")
@Scope("prototype")
public class TaskService {
	
	@Autowired
	TaskRepo taskRepo;
	@Autowired
	SubTaskRepo subTaskRepo;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	TaskHistoryRepo taskHistoryRepo;
	
	// All Summaries
	@GetMapping
	public Iterable<Task> getAllTasks(Pageable pageable,Model model,
			@RequestParam(value="taskType",required=false)String taskType,
			@RequestParam(value="taskStatus",required=false)String taskStatus,
			@RequestParam(value="taskListId",required=false)Integer taskListId,
			@RequestParam(value="assignedToUserId",required=false)String assignedToUserId,
			@RequestParam(value="isDelayed",required=false)String isDelayed,
			@RequestParam(value="isBlocked",required=false)String isBlocked,
			@RequestParam(value="jobId",required=false)Integer jobId,
			@RequestParam(value="endClientId",required=false)Integer endClientId,
			@RequestParam(value="milestoneId",required=false)Integer milestoneId,
			@RequestParam(value="taskTypeId",required=false)Integer taskTypeId)throws SmartOfficeException{
		try {
			return taskRepo.findBySummaries(pageable,taskType, taskStatus, taskListId, assignedToUserId, isDelayed, isBlocked, jobId, endClientId, milestoneId, taskTypeId);
	
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - getAllTasks");
		}
		
	}
	
	
	// Task byId
	@GetMapping("/{taskId}")
	public Optional<Task> getTaskById( @PathVariable(value = "taskId")Integer taskId) throws SmartOfficeException{
		Task task =new Task();
		try {
			task=taskRepo.findById(Integer.valueOf(taskId)).get();
			task.setComments(commentRepository.findByTaskId(Integer.toString(taskId)));
			return taskRepo.findById(taskId);
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - getTaskById");
		}
	}
	
	//Create Task Function
	@PostMapping("/create-task")
	public List<Task> createTasks(@RequestBody List<Task> tasks) throws SmartOfficeException{
		List<Task> returnTasks = new ArrayList<Task>();
		try {
		for(Task task:tasks) {
			task.setTaskStatus("CREATED");
			task.setIsEnabled("Y");
			task.setCreatedBy(commonUtils.getLoggedinUserId());
			returnTasks.add(taskRepo.save(task)); 
			logTaskHistory("CREATED",task,null);
		}
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - createTasks");
			
		}
		
		return returnTasks;
	
	}
	



	//Update delay Status
	@PatchMapping("/{taskId}/update-delay-status")
	public Task updateDelayStatus(@RequestBody Task taskFromReq, @PathVariable(value = "taskId")Integer taskId) throws SmartOfficeException{
		try {
			Task taskFromDb = taskRepo.findById(Integer.valueOf(taskId)).get();
			taskFromDb.setIsDelayed(taskFromReq.getIsDelayed());
			if(taskFromReq.getIsDelayed().equals("N")) {
				taskFromDb.setDelayedDt(null);
			} 
			else {
				taskFromDb.setDelayedDt(LocalDateTime.now());
			}
			taskFromDb.setModifiedBy(commonUtils.getLoggedinUserId());
			taskRepo.save(taskFromDb);
			logTaskHistory("UPDATE-DELAY",taskFromReq,null);
		
		
			return taskFromDb;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - updateDelayStatus");
		}
		
	}
	
	//Update Block Status
	@PatchMapping("/{taskId}/update-block-status")
	public Task updateBlockStatus(@RequestBody Task taskFromReq, @PathVariable(value="taskId")Integer taskId)  throws SmartOfficeException{
		try {
			Task taskFromDb = taskRepo.findById(Integer.valueOf(taskId)).get();
			taskFromDb.setIsBlocked(taskFromReq.getIsBlocked());
			if(taskFromReq.getIsBlocked().equals("N")) {
				taskFromDb.setBlockedDt(null);
			}else {
				taskFromDb.setBlockedDt(LocalDateTime.now());
			}
			taskFromDb.setModifiedBy(commonUtils.getLoggedinUserId());
			taskRepo.save(taskFromDb);
			logTaskHistory("UPDATED-BLOCKED",taskFromReq,null);	
			return taskFromDb;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - updateBlockStatus");
		}
	}
	
	//Complete task
	@PatchMapping("/{taskId}/completed")
	public Task getCompleteTask(@PathVariable(value = "taskId")Integer taskId) throws SmartOfficeException{
		try {
		
			Task completeTask= taskRepo.findById(Integer.valueOf(taskId)).get();
			completeTask.setTaskStatus("COMPLETED");
			completeTask.setCompletedDt(LocalDateTime.now());
			completeTask.setModifiedBy(commonUtils.getLoggedinUserId());
			taskRepo.save(completeTask);
			logTaskHistory("COMPLETED",completeTask,null);
			return completeTask;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - getCompleteTask");
		}
	}
	
	//Update Task Data
	@PatchMapping("/{taskId}/update-task-data")
	public Task updateTaskData(@RequestBody Task task, @PathVariable (value = "taskId")Integer taskId) throws SmartOfficeException{
     try {
		Task task1 = taskRepo.findById(Integer.valueOf(taskId)).get();
		
		task1.setTaskName(task.getTaskName());
		task1.setTaskDesc(task.getTaskDesc());
		task1.setModifiedBy(commonUtils.getLoggedinUserId());
		taskRepo.save(task1);
		logTaskHistory("UPDATE-TASK",task1,null);
		return task1;
     }catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - updateTaskData");
	}
     }
	
	//Assign User
	@PatchMapping("/{taskId}/assign/{assignedUserId}")
	public Task assignUser(@PathVariable(value = "taskId")Integer taskId,@PathVariable(value = "assignedUserId")String assignedUserId) throws SmartOfficeException{
		try {
		Task taskassign = taskRepo.findById(Integer.valueOf(taskId)).get();
		taskassign.setAssignedToUserId(assignedUserId);
		taskassign.setAssignedOnDt(LocalDateTime.now());
		taskassign.setModifiedBy(commonUtils.getLoggedinUserId());
		taskRepo.save(taskassign);
		logTaskHistory("ASSIGNED",taskassign,null);
		return taskassign;
		}catch (Exception e) {
			e.printStackTrace();
				throw new SmartOfficeException("Exception Occured : TaskService - addAssignUser");
		}
	}
	
	//UnAssign User
		@PatchMapping("/{taskId}/un-assign")
		public Task unassignUser(@PathVariable(value = "taskId")Integer taskId) throws SmartOfficeException{
			try {
			Task taskassign = taskRepo.findById(Integer.valueOf(taskId)).get();
			taskassign.setAssignedToUserId(null);
			taskassign.setAssignedOnDt(null);
			taskassign.setModifiedBy(commonUtils.getLoggedinUserId());
			taskRepo.save(taskassign);
			logTaskHistory("UN-ASSIGNED",taskassign,null);
			return taskassign;
			}catch (Exception e) {
				e.printStackTrace();
					throw new SmartOfficeException("Exception Occured : TaskService - addAssignUser");
			}
		}
    
	//Delete Tasks
	@DeleteMapping("/delete/{taskId}")
	public Task deleteTasks(@PathVariable(value = "taskId") Integer taskId) throws SmartOfficeException{
		Task task=taskRepo.findById(taskId).get();
		try {
			   taskRepo.delete(taskId);
			   logTaskHistory("DELETED",task,null);
		   
		return task;
	   }catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - deleteTasks");   
	}
	}
	
	//Without Exposing - update Task
    public Task updateTask(@RequestBody Task task, @PathVariable(value = "taskId")Integer taskId) throws SmartOfficeException{
		try {

			task.setModifiedBy(commonUtils.getLoggedinUserId());
			taskRepo.save(task);
			logTaskHistory("UPDATED",task,null);
			return task;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - updateTask");   
		}

}
	//Create Sub task Function
	@PostMapping("/{taskId}/sub-tasks")
	public List<SubTask> addUpdateSubTasks(@RequestBody Task task, @PathVariable (value = "taskId")Integer taskId)throws SmartOfficeException{
		try {
		List<SubTask> resultSubTasks  = new ArrayList<>();
		SubTask newSubTask,subTaskFromDB = null;
		if(task.getSubTasks() !=null) {
			for(SubTask subTask:task.getSubTasks()) {
				if(subTask.getId() != null && subTask.getId() >0)
				{
					subTaskFromDB = subTaskRepo.findById(subTask.getId()).get();
					subTaskFromDB.setSubtaskName(subTask.getSubtaskName());
					subTaskFromDB.setSubTaskDesc(subTask.getSubTaskDesc());
					subTaskFromDB.setModifiedBy(commonUtils.getLoggedinUserId());
					resultSubTasks.add(subTaskRepo.save(subTaskFromDB));
				}
				else {
					newSubTask = new SubTask();
					newSubTask.setSubtaskName(subTask.getSubtaskName());
					newSubTask.setSubTaskDesc(subTask.getSubTaskDesc());
					if(subTask.getAssignedToUserId()!=null) {
						newSubTask.setAssignedToUserId(subTask.getAssignedToUserId());
						newSubTask.setAssignedOnDt(LocalDateTime.now());
					}
					newSubTask.setTaskId(taskId);
					newSubTask.setTaskStatus("CREATED");
					newSubTask.setIsEnabled("Y");
					newSubTask.setCreatedBy(commonUtils.getLoggedinUserId());
					resultSubTasks.add(subTaskRepo.save(newSubTask));
				}
			}	
		}
		logTaskHistory("SUBTASK-CREATED",task,null);
		return resultSubTasks;
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - addUpdateSubTasks");   
		}
	}
	
	
	// Complete Sub Task Function
	@PatchMapping("/{taskId}/sub-tasks/{subTaskId}/completed")
	public SubTask completeSubTask(@PathVariable(value = "subTaskId")Integer subTaskId)throws SmartOfficeException{
		try {
			SubTask subTaskFromDB = subTaskRepo.findById(subTaskId).get();
			subTaskFromDB.setTaskStatus("COMPLETED");
			subTaskFromDB.setCompletedDt(LocalDateTime.now());
			subTaskFromDB.setModifiedBy(commonUtils.getLoggedinUserId());
			subTaskRepo.save(subTaskFromDB);
			logTaskHistory1("SUBTASK-COMPLETED", subTaskFromDB, null);
			return subTaskFromDB;	
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - addSubTaskFunction");
		}
	}
	
	
	//Delete Sub Tasks
	@DeleteMapping("/delete/sub-task/{subTaskId}")
	public SubTask deleteSubtaskTasks(@PathVariable(value = "subTaskId") Integer subTaskId) throws SmartOfficeException{
		SubTask subTask=subTaskRepo.findById(subTaskId).get();
		try {
			subTaskRepo.delete(subTaskId);
			logTaskHistory1("DELETED",subTask,null);
		   
		return subTask;
	   }catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : TaskService - deleteTasks");   
	}
	}
    //Log Task History - Without Exposing 
	public TaskHistory logTaskHistory(String activity , Task task, Integer subTaskId) throws SmartOfficeException{
		
		TaskHistory taskHistory = new TaskHistory();
		task.setId(task.getId());
		taskHistory.setLogDt(LocalDateTime.now());
		String userId=commonUtils.getLoggedinUserId();
		
		if(activity.equals("CREATED")) {
			taskHistory.setTaskName(taskHistory.getTaskName());
			taskHistory.setTaskDesc(taskHistory.getTaskDesc());
			taskHistory.setAssignedToUserId(taskHistory.getAssignedToUserId());
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("ASSIGNED")){
			taskHistory.setAssignedToUserId(taskHistory.getAssignedToUserId());
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("UNASSIGNED")){
			taskHistory.setAssignedToUserId(null);
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("UPDATE-DELAY")) {
			taskHistory.setTaskName(taskHistory.getTaskName());
			taskHistory.setTaskDesc(taskHistory.getTaskDesc());
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("UPDATE-BLOCK")) {
			taskHistory.setTaskName(taskHistory.getTaskName());
			taskHistory.setTaskDesc(taskHistory.getTaskDesc());
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("DELETED")) {
			taskHistory.setTaskName(taskHistory.getTaskName());
		    taskHistory.setTaskId(taskHistory.getTaskId());
		}
		
		return taskHistory;
		
	}
	
     //
     public TaskHistory logTaskHistory1(String activity , SubTask subTask, Integer subTaskId) throws SmartOfficeException{
		
		TaskHistory taskHistory = new TaskHistory();
		subTask.setId(subTask.getId());
		taskHistory.setLogDt(LocalDateTime.now());
		taskHistory.setActivity(activity);
		String userId=commonUtils.getLoggedinUserId();
		
		if(activity.equals("SUBTASK-COMPLETED")) {
			subTask.setId(subTask.getId());
			taskHistoryRepo.save(taskHistory);
	    }
		
		if(activity.equals("SUBTASK-SAVED")) {
			subTask.setId(subTask.getId());
			taskHistoryRepo.save(taskHistory);
	    }
		
		if(activity.equals("UPDATE-DELAYED")) {
			taskHistory.setTaskName(taskHistory.getTaskName());
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("UPDATE-BLOCKED")) {
			taskHistory.setTaskName(taskHistory.getTaskName());
			taskHistoryRepo.save(taskHistory);
		}
		
		if(activity.equals("SUBTASK-CREATED")) {
			subTask.setId(subTask.getId());
			taskHistoryRepo.save(taskHistory);
	    }
		

		return taskHistory;
}

}





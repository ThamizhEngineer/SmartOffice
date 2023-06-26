package com.ss.smartoffice.soservice.transaction.VideoUploadService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("/transaction/video-upload-service")
public class VideoUploadService {
	
	@Autowired
	LearningTrackRepository learningTrackRepository;
	@Autowired
	LearningTrackDetailRepository learningTrackDetailRepository;
	
	
	@GetMapping
	public Iterable<LearningTrack> getAllLearningTrack()throws SmartOfficeException{
		return learningTrackRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<LearningTrack> getLearningTrackById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return learningTrackRepository.findById(id);
	}
	
	@PostMapping
	public LearningTrack addLearningTrack(@RequestBody LearningTrack learningTrack)throws SmartOfficeException{
		return learningTrackRepository.save(learningTrack);
	}
	
	@PatchMapping("/{id}")
	public LearningTrack updateLearningTrack(@RequestBody LearningTrack learningTrack)throws SmartOfficeException{
		return learningTrackRepository.save(learningTrack);
	}
	
	@PatchMapping("/{id}/update-lines")
	public LearningTrack addLearningTrackLines(@RequestBody LearningTrack learningTrack)throws SmartOfficeException{
	if(learningTrack.getLearningTrackDetails()!=null &&!learningTrack.getLearningTrackDetails().isEmpty()) {
		for(LearningTrackDetails learningTrackDetails:learningTrack.getLearningTrackDetails()) {
			learningTrackDetailRepository.save(learningTrackDetails);
		}
	}
	return learningTrack;
	}
	
	@DeleteMapping("/{id}/delete")
	public void deleteLearningTrack(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		learningTrackRepository.deleteById(id);
	}
	
	@DeleteMapping("/{id}/delete/lines")
	public void deleteLearningTrackLines(@PathVariable(value="id")Integer id) throws SmartOfficeException{
		learningTrackDetailRepository.deleteById(id);
	}
}

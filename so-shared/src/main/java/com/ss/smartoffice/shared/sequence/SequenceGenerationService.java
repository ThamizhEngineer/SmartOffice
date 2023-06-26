package com.ss.smartoffice.shared.sequence;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path ="/sequence")
public class SequenceGenerationService {
	@Autowired
	SequenceRepository sequenceRepository;
	
	@Autowired
	SequenceGenerationHelper sequenceGenerationHelper;
	
	
	String[] ALPHABETS= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	ArrayList<String> incrementalTokens = new ArrayList<String>(Arrays.asList( "RUN_YY", "RUN_YYYY", "RUN_MM", "RUN_MMM", "RUN_MMM","RUN_A1", "RUN_A2", "RUN_SEQ", "YY", "YYYY", "MM", "MMM", "A1", "A2", "SEQ"));
	ArrayList<String> trackedTokens = new ArrayList<String>(Arrays.asList( "RUN_A1", "RUN_A2", "RUN_SEQ", "A1", "A2","SEQ"));

	public synchronized String nextSeq(@PathVariable(value="sequenceCode")String sequenceCode, HashMap<String, String> buisnessKeys) throws SmartOfficeException {
		return sequenceGenerationHelper.nextSeq(sequenceCode, buisnessKeys);
	}
	
	@PostMapping
	public String generateTokenValue(String token,  Sequence sequenceObj) throws SmartOfficeException {
		return sequenceGenerationHelper.generateTokenValue("RUN_SEQ", sequenceObj);
	}
	
	public Sequence updateTokenValue(String token, String newTokenValue, Sequence sequenceObj)
			throws SmartOfficeException {
		return sequenceGenerationHelper.updateTokenValue(token, newTokenValue, sequenceObj);
	}
	
	public String resetTokenValue(String token, Sequence sequenceObj) throws SmartOfficeException {
		return sequenceGenerationHelper.resetTokenValue(token, sequenceObj);
	}

	public String getTokenValue(String token, Sequence sequenceObj) throws SmartOfficeException {
		return sequenceGenerationHelper.getTokenValue(token, sequenceObj);
	}
	
	
	@GetMapping
	public Sequence getCurrentValue(@RequestParam(value="sequenceCode")String sequenceCode)throws SmartOfficeException{
		try {
			return sequenceRepository.findBySequenceCode(sequenceCode).get(0);
		} catch (Exception e) {
			throw new SmartOfficeException(e.getMessage());
		}
	}

	public Sequence nextSequence(String sequenceCode)throws SmartOfficeException{
		try {
			Sequence seq = sequenceRepository.findBySequenceCode(sequenceCode).get(0);
			Integer incCurrentValue = seq.getStartValue();
			if( seq.getStartValue() > seq.getCurrentValue() )
				incCurrentValue=seq.getStartValue();
			else
				incCurrentValue=seq.getCurrentValue()+1;
			seq.setCurrentValue(incCurrentValue);
			sequenceRepository.save(seq);
			return seq;
		} catch (Exception e) {
			throw new SmartOfficeException(e.getMessage());
		}
	}
	

	public Sequence resetSequence(String sequenceCode)throws SmartOfficeException{
		try {
			Sequence seq = sequenceRepository.findBySequenceCode(sequenceCode).get(0);
			seq.setCurrentValue(seq.getStartValue());
			sequenceRepository.save(seq);
			return seq;
		} catch (Exception e) {
			throw new SmartOfficeException(e.getMessage());
		}
	}
	
	
	public Integer getNextValue(String sequenceCode)throws SmartOfficeException{
		if(sequenceCode.equalsIgnoreCase("EMPLOYEE-CODE")) {
			
		}
		return nextSequence(sequenceCode).getCurrentValue();
	}
	
	public String generateEmployeeCode() {
		String generatedSequence = "";
		try {
			Sequence seq = sequenceRepository.findBySequenceCode("EMPLOYEE-CODE").get(0);
			// curr_value is equal to end_value
			if(seq.getCurrentValue().compareTo(seq.getEndValue())==0) {
				//check if alpha2 is last
				if(isLastAlpha(seq.getAlpha2Value())) {
					//	then increment alpha1
					seq.setAlpha1Value(incrementAlpha(seq.getAlpha1Value()));
				}
				//increment alpha2
				seq.setAlpha2Value(incrementAlpha(seq.getAlpha2Value()));
				
				//set curr_value = start_value
				seq.setCurrentValue(seq.getStartValue());
				
			}
			else {
				// increment by  curr_value by 1
				seq.setCurrentValue(seq.getCurrentValue()+1);
				
			//save sequence
			sequenceRepository.save(seq);
			generatedSequence = seq.getAlpha1Value()+seq.getAlpha2Value()+"-"+seq.getCurrentValue();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedSequence;
	}
	

	private boolean isLastAlpha(String alphabet) {
		return alphabet.equalsIgnoreCase("Z");
	}
	
	private String incrementAlpha(String currAlpha) {
		int pos=0;
		if(currAlpha.equalsIgnoreCase("Z")) {
			pos = ALPHABETS.length-1;
		}
		else {
			for (String alpha : ALPHABETS) {
				pos = pos+1;
				if(alpha.equalsIgnoreCase(currAlpha) ) {
					break;
				}
			}
		}
		return ALPHABETS[pos];
	}
	
	

}

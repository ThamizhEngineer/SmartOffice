package com.ss.smartoffice.shared.sequence;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@Service
public class SequenceGenerationHelper {

	private static Logger log = LoggerFactory.getLogger(SequenceGenerationHelper.class);

	@Autowired
	SequenceRepository sequenceRepository;
	ArrayList<String> ALPHABETS = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
	ArrayList<String> incrementalTokens = new ArrayList<String>(Arrays.asList("RUN_YY", "RUN_YYYY", "RUN_MM", "RUN_MMM",
			"RUN_MMM", "RUN_A1", "RUN_A2", "RUN_SEQ", "YY", "YYYY", "MM", "MMM", "A1", "A2", "SEQ"));
	ArrayList<String> trackedTokens = new ArrayList<String>(
			Arrays.asList("RUN_A1", "RUN_A2", "RUN_SEQ", "A1", "A2", "SEQ"));

	public String nextSeq(String sequenceCode, HashMap<String, String> businessKeys) throws SmartOfficeException {

		boolean resetNextToken = false;
		String sequence = "";
		String currTokenValue = "";
		String newTokenValue = "";
		String seq = null;

		List<String> tokenList = new ArrayList<String>();
		System.out.println(sequenceCode);
		Sequence sequenceObj = sequenceRepository.findBySequenceCode(sequenceCode).get(0);
		String seqPattern = sequenceObj.getSequencePattern();

		ArrayList<String> tokenArray = new ArrayList<>(Arrays.asList(seqPattern.split(",")));
		System.out.println(tokenArray);
		List<String> tempTokenList = new ArrayList<String>();

		try {
			for (String token : tokenArray) {

				if (businessKeys != null) {
					businessKeys.put(token, null);
					tokenList.add(token);
				} else {
					System.out.println("token" + token);
					tokenList.add(token);
				}

			}
			
			System.out.println("tokenList" + tokenList);

			if (tokenList != null && !tokenList.isEmpty()) {
				String sequenceVal = null;
				for (String tempToken : tokenList) {
					System.out.println("tempToken" + tempToken);
					if (incrementalTokens.contains(tempToken)) {
						System.out.println("hi present in incremental token");
						if (resetNextToken == false) {
							System.out.println("hi present in incremental token");
							if (trackedTokens.contains(tempToken)) {
								sequenceVal = resetTokenValue(tempToken, sequenceObj);
							}
							if (tempToken.startsWith("RUN_")) {
								resetNextToken = true;
							} else {
								resetNextToken = false;
							}
						} else {
							currTokenValue = getTokenValue(tempToken, sequenceObj);
							newTokenValue = generateTokenValue(tempToken, sequenceObj);

							sequenceObj = updateTokenValue(tempToken, newTokenValue, sequenceObj);
							if (tempToken.startsWith("RUN") && currTokenValue != newTokenValue) {
								resetNextToken = true;
							} else {
								resetNextToken = false;
							}
							tempTokenList.add(tempToken);
						}
					} else {
						tempTokenList.add(tempToken);
					}
					tokenList = tempTokenList;
				}
				for (String tokenSeq : tokenList) {
					System.out.println(tokenSeq);
					sequence = sequence + tokenSeq;
					seq = sequence + sequenceVal;
				}
			}

			System.out.println("sequence" + sequence);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		if (seq==null) {
			seq = "Code Not Generated";
		}

		return seq;

	}

	public String generateTokenValue(String token, Sequence sequenceObj) throws SmartOfficeException {
		List<String> incrementalTokens = new ArrayList<String>();
		incrementalTokens.add(token);
		String tokenValue = null;

		if (incrementalTokens.contains(token)) {
			if (token.startsWith("RUN_")) {

				switch (token) {
				case "RUN_YYYY":
					tokenValue = String.valueOf(LocalDateTime.now().getYear());
					break;
				case "RUN_YY":
					int lastTwoDigits = Calendar.getInstance().get(Calendar.YEAR) % 100;

					tokenValue = String.valueOf(lastTwoDigits);

					break;
				case "RUN_MM":
					SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
					Date now = new Date();
					System.out.println(simpleDateformat.format(now));

					break;
				case "RUN_MMM":
					simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
					Date currDate = new Date();
					System.out.println(simpleDateformat.format(currDate));

					break;

				case "RUN_SEQ":
					int seqValue = 0;
					if (sequenceObj.getCurrSeq().equals(sequenceObj.getEndSeq())) {
						seqValue = Integer.valueOf(sequenceObj.getStartSeq());
					} else {
						seqValue = Integer.valueOf(sequenceObj.getCurrSeq());
						seqValue = seqValue + 1;
					}

					tokenValue = String.valueOf(seqValue);
					System.out.println(seqValue);
					break;
				case "RUN_A1":
					if (sequenceObj.getCurrA1().equals(sequenceObj.getEndA1())) {
						tokenValue = sequenceObj.getStartA1();
					} else {
						int currPos = ALPHABETS.indexOf(sequenceObj.getCurrA1());
						tokenValue = ALPHABETS.get(currPos + 1);
					}
					break;
				case "RUN_A2":
					if (sequenceObj.getCurrA2().equals(sequenceObj.getEndA2())) {
						tokenValue = sequenceObj.getStartA2();
					} else {
						int currPos = ALPHABETS.indexOf(sequenceObj.getCurrA2());
						tokenValue = ALPHABETS.get(currPos + 1);
					}
					break;
				default:
					break;
				}
			} else {
				return tokenValue;
			}

		}
		return tokenValue;
	}

	public Sequence updateTokenValue(String token, String newTokenValue, Sequence sequenceObj)
			throws SmartOfficeException {
		if (trackedTokens.contains(token)) {
			if (token.startsWith("RUN_")) {

				switch (token) {
				case "RUN_SEQ":
					sequenceObj.setCurrSeq(newTokenValue);
					break;
				case "RUN_A1":
					sequenceObj.setCurrA1(newTokenValue);
					break;
				case "RUN_A2":
					sequenceObj.setCurrA2(newTokenValue);
					break;
				default:
					break;
				}
			} else {
				return sequenceObj;
			}
		}
		return sequenceObj;
	}

	public String resetTokenValue(String token, Sequence sequenceObj) throws SmartOfficeException {
		System.out.println("hi in reset token value");
		System.out.println(token);
		String sequence = null;
		if (trackedTokens.contains(token)) {
			if (token != null) {

				switch (token) {
				case "RUN_SEQ":
					System.out.println("hi in run_seq");
					Integer seqVal = Integer.parseInt(sequenceObj.getCurrSeq()) + 1;
					sequenceObj.setCurrSeq(seqVal.toString());
					sequenceRepository.save(sequenceObj);
					sequence = sequenceObj.getCurrSeq();
					break;
				case "RUN_A1":
					Integer seqA1 = Integer.parseInt(sequenceObj.getCurrA1()) + 1;
					sequenceObj.setCurrA1(seqA1.toString());
					sequenceRepository.save(sequenceObj);
					sequence = sequenceObj.getCurrA1();
					break;
				case "RUN_A2":
					Integer seqA2 = Integer.parseInt(sequenceObj.getCurrA2()) + 1;
					sequenceObj.setCurrA2(seqA2.toString());
					sequenceRepository.save(sequenceObj);
					sequence = sequenceObj.getCurrA2();
					break;

				default:
					break;
				}
			}
		}
		System.out.println(sequence);
		return sequence;

	}

	public String getTokenValue(String token, Sequence sequenceObj) throws SmartOfficeException {
		String tokenValue = null;
		if (trackedTokens.contains(token)) {
			if (token.startsWith("RUN_")) {

				switch (token) {
				case "RUN_SEQ":
					tokenValue = sequenceObj.getStartSeq();
					break;
				case "RUN_A1":
					tokenValue = sequenceObj.getStartA1();
					break;
				case "RUN_A2":
					tokenValue = sequenceObj.getStartA2();
					break;
				case "RUN_YYYY":
					tokenValue = String.valueOf(LocalDateTime.now().getYear());
					break;
				case "RUN_YY":
					int lastTwoDigits = Calendar.getInstance().get(Calendar.YEAR) % 100;
					tokenValue = String.valueOf(lastTwoDigits);
					break;
				case "RUN_MM":
					SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
					Date now = new Date();
					System.out.println(simpleDateformat.format(now));
					tokenValue = simpleDateformat.format(now);

					break;
				case "RUN_MMM":
					simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
					Date currDate = new Date();
					System.out.println(simpleDateformat.format(currDate));
					tokenValue = simpleDateformat.format(currDate);
					break;
				default:
					break;
				}
			} else {
				tokenValue = token;
			}
		}
		return tokenValue;

	}

}

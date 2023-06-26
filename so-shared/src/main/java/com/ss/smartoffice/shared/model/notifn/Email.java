package com.ss.smartoffice.shared.model.notifn;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {

	private String emailBody;
	private String emailSubject;
	private String fromEmailId;
	private String toEmailId;
}

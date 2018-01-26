package com.cgi.bootcamp.survey.ui.models;

import javax.validation.constraints.Size;

import com.cgi.bootcamp.survey.ui.models.validators.EmailsConstraint;
import com.cgi.bootcamp.survey.ui.models.validators.EmailsConstraintValidator;

public class SurveyBaseAttributes {

	@Size(min=3, message="Der Titel muss aus mindestens 3 Zeichen bestehen")
	private String title;
	private String headerText;
	private String invitationText;
	
	// TODO specify validator (Integer, probably one or two digit, not more )
	private String numberOfQuestionsStr;

	public String getNumberOfQuestionsStr() {
		return numberOfQuestionsStr;
	}

	public void setNumberOfQuestionsStr(String numberOfQuestionsStr) {
		this.numberOfQuestionsStr = numberOfQuestionsStr;
	}

	@javax.validation.constraints.Pattern(regexp = EmailsConstraintValidator.VALID_EMAIL_ADDRESS_REGEX, message="Bitte gebe eine gültige E-Mail-Adresse ein.")
	private String creatorEmail;

	@EmailsConstraint(message="Bitte gebe nur gültige E-Mail-Adressen an.")
	private String emails;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	@Override
	public String toString() {
		return "SurveyBaseAttributes [title=" + title + ", headerText=" + headerText + ", invitationText="
				+ invitationText + ", creatorEmail=" + creatorEmail + ", emails=" + emails + ", numberOfQuestionsStr=" + numberOfQuestionsStr + "]";
	}

	public String getInvitationText() {
		return invitationText;
	}

	public void setInvitationText(String invitationText) {
		this.invitationText = invitationText;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}
	
}

package com.cgi.bootcamp.survey.ui.models;

import java.util.List;

import com.cgi.bootcamp.survey.ui.rest.model.PageElement;

public class SurveyFieldsAttributes {

	private String title;
	private String surveyId;
	private List<PageElement> pageElements;
	

	// the new question
	private String question;
	private String type;
	private String example;

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public List<PageElement> getPageElements() {
		return pageElements;
	}

	public void setPageElements(List<PageElement> pageElements) {
		this.pageElements = pageElements;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "SurveyFieldsAttributes [title=" + title + "]";
	}

	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}


}

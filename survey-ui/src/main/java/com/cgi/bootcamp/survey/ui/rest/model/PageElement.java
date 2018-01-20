package com.cgi.bootcamp.survey.ui.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class PageElement {
	
	enum PageElementType {
		TEXTBLOCK, QUESTION_FREETEXT, MULTIPLE_CHOICE
	}
	
	private String text;
	private PageElementType type;
	private List<String> answers;

}

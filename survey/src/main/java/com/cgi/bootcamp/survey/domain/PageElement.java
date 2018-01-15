package com.cgi.bootcamp.survey.domain;

import java.util.List;

import lombok.Data;

@Data
public class PageElement {
	
	enum PageElementType {
		TEXTBLOCK, QUESTION_FREETEXT, MULTIPLE_CHOICE
	}
	
	private String text;
	private PageElementType type;
	private List<String> answers;

}

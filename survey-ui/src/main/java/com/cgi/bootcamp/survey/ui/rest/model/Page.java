package com.cgi.bootcamp.survey.ui.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Page {
	private List<PageElement> elements;

}

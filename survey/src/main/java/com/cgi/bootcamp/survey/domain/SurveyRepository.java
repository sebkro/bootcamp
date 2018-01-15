package com.cgi.bootcamp.survey.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, String> {

	public Survey findOneById(String id);

}

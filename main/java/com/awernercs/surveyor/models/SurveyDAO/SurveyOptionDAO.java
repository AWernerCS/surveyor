package com.awernercs.surveyor.models.SurveyDAO;

import com.awernercs.surveyor.models.SurveyOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Amanda on 4/30/2017.
 */
@Repository
@Transactional
public interface SurveyOptionDAO extends CrudRepository<SurveyOption, Integer> {
}
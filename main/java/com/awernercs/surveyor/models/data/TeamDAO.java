package com.awernercs.surveyor.models.data;

import com.awernercs.surveyor.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TeamDAO extends CrudRepository<Team, Integer> {
}
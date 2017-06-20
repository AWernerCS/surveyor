package com.awernercs.surveyor.models.data;

import com.awernercs.surveyor.models.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TeamMemberDAO extends CrudRepository<TeamMember, Integer> {
}
package com.jabasoft.mms.projectmanagement.adapter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SpringJpaProjectRepository extends CrudRepository<JpaProject, Long>{

	public List<JpaProject> findAllByCustomerId(Long customerId);
}

package com.jabasoft.mms.todomanagement.todo.adapter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaTodoRepository extends CrudRepository<JpaTodo, Long> {

	public List<JpaTodo> deleteJpaTodoByCustomerId(Long customerId);
}

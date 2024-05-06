package com.jabasoft.mms.todomanagement.todo.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaTodoRepository extends CrudRepository<JpaTodo, Long> {

}

package com.jabasoft.mms.todomanagement.category.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaTodoCategoryRepository extends CrudRepository<JpaTodoCategory, String> {

}

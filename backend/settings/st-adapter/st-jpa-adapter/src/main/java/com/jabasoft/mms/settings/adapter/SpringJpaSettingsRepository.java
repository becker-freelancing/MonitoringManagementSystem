package com.jabasoft.mms.settings.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaSettingsRepository extends CrudRepository<JpaSettings, String> {

	public JpaSettings findBySelector(String selector);
}

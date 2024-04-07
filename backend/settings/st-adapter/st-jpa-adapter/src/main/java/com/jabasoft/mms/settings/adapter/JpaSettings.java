package com.jabasoft.mms.settings.adapter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MMS_SETTINGS")
public class JpaSettings {

	@Id
	@Column(name = "SEL")
	private String selector;

	@Column(name = "VAL")
	private String value;

	public String getSelector() {

		return selector;
	}

	public void setSelector(String selector) {

		this.selector = selector;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

}

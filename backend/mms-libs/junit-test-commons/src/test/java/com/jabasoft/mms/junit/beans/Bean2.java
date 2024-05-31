package com.jabasoft.mms.junit.beans;

import java.util.Objects;

public class Bean2 {

	private String s;

	public Bean2(String s) {

		this.s = s;
	}

	public String getS() {

		return s;
	}

	public void setS(String s) {

		this.s = s;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Bean2 bean2 = (Bean2) o;
		return Objects.equals(s, bean2.s);
	}

	@Override
	public int hashCode() {

		return Objects.hash(s);
	}

}

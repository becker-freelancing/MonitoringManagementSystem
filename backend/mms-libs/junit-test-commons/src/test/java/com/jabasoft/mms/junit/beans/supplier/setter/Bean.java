package com.jabasoft.mms.junit.beans.supplier.setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Bean {


	private int i;
	private Integer in;
	private double d;
	private Double dou;
	private long l;
	private Long lo;
	private float f;
	private Float fl;
	private String st;
	private LocalDate ld;
	private LocalTime lt;
	private LocalDateTime ldt;
	private byte[] b;
	private BigInteger bi;
	private BigDecimal bd;
	private boolean bo;
	private Boolean bol;
	private Enum anEnum;
	private List<Double> doubles;

	public void setDoubles(List<Double> doubles) {

		this.doubles = doubles;
	}

	private InnerBean innerBean;

	public Bean() {

	}

	public void setI(int i) {

		this.i = i;
	}

	public void setIn(Integer in) {

		this.in = in;
	}

	public void setD(double d) {

		this.d = d;
	}

	public void setDou(Double dou) {

		this.dou = dou;
	}

	public void setL(long l) {

		this.l = l;
	}

	public void setLo(Long lo) {

		this.lo = lo;
	}

	public void setF(float f) {

		this.f = f;
	}

	public void setFl(Float fl) {

		this.fl = fl;
	}

	public void setSt(String st) {

		this.st = st;
	}

	public void setLd(LocalDate ld) {

		this.ld = ld;
	}

	public void setLt(LocalTime lt) {

		this.lt = lt;
	}

	public void setLdt(LocalDateTime ldt) {

		this.ldt = ldt;
	}

	public void setB(byte[] b) {

		this.b = b;
	}

	public void setBi(BigInteger bi) {

		this.bi = bi;
	}

	public void setBd(BigDecimal bd) {

		this.bd = bd;
	}

	public void setBo(boolean bo) {

		this.bo = bo;
	}

	public void setBol(Boolean bol) {

		this.bol = bol;
	}

	public void setAnEnum(Enum anEnum) {

		this.anEnum = anEnum;
	}

	public void setInnerBean(InnerBean innerBean) {

		this.innerBean = innerBean;
	}

}

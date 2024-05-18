package com.jabasoft.mms.junit.beans.supplier.constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.jabasoft.mms.junit.beans.supplier.constructor.RandomBeanCreator;

class RandomBeanCreatorTest {

	private enum Enum {
		YES,
		NO,
		MAYBE
	}

	private class InnerInnerBean {

		private long l;

		public InnerInnerBean(long l) {

			this.l = l;
		}

	}

	private class InnerBean {

		private int i;
		private InnerInnerBean innerBean;

		public InnerBean(int i, InnerInnerBean innerBean) {

			this.i = i;
			this.innerBean = innerBean;
		}

	}

	private class Bean {

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

		private InnerBean innerBean;

		public Bean(
			int i,
			Integer in,
			double d,
			Double dou,
			long l,
			Long lo,
			float f,
			Float fl,
			String st,
			LocalDate ld,
			LocalTime lt,
			LocalDateTime ldt,
			byte[] b,
			BigInteger bi,
			BigDecimal bd,
			boolean bo,
			Boolean bol,
			Enum anEnum,
			InnerBean innerBean) {

			this.i = i;
			this.in = in;
			this.d = d;
			this.dou = dou;
			this.l = l;
			this.lo = lo;
			this.f = f;
			this.fl = fl;
			this.st = st;
			this.ld = ld;
			this.lt = lt;
			this.ldt = ldt;
			this.b = b;
			this.bi = bi;
			this.bd = bd;
			this.bo = bo;
			this.bol = bol;
			this.anEnum = anEnum;
			this.innerBean = innerBean;
		}

	}

	@Test
	void testCreateBeans() {

		Stream<Bean> beans = new RandomBeanCreator<Bean>() {

			@Override
			protected Class<? extends Bean> getBeanClass() {

				return Bean.class;
			}
		}.createBeans();

		assertEquals(72, beans.count());
	}

}
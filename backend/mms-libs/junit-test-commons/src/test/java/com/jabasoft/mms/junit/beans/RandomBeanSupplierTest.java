package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RandomBeanSupplierTest {

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

		@Override
		public boolean equals(Object o) {

			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			InnerInnerBean that = (InnerInnerBean) o;
			return l == that.l;
		}

		@Override
		public int hashCode() {

			return Objects.hash(l);
		}

	}

	private class InnerBean {

		private int i;
		private InnerInnerBean innerBean;

		public InnerBean(int i, InnerInnerBean innerBean) {

			this.i = i;
			this.innerBean = innerBean;
		}

		@Override
		public boolean equals(Object o) {

			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			InnerBean innerBean1 = (InnerBean) o;
			return i == innerBean1.i && Objects.equals(innerBean, innerBean1.innerBean);
		}

		@Override
		public int hashCode() {

			return Objects.hash(i, innerBean);
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

		@Override
		public boolean equals(Object o) {

			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Bean bean = (Bean) o;
			return i == bean.i
				&& Double.compare(d, bean.d) == 0
				&& l == bean.l
				&& Float.compare(f, bean.f) == 0
				&& bo == bean.bo
				&& Objects.equals(in, bean.in)
				&& Objects.equals(dou, bean.dou)
				&& Objects.equals(lo, bean.lo)
				&& Objects.equals(fl, bean.fl)
				&& Objects.equals(st, bean.st)
				&& Objects.equals(ld, bean.ld)
				&& Objects.equals(lt, bean.lt)
				&& Objects.equals(ldt, bean.ldt)
				&& Arrays.equals(b, bean.b)
				&& Objects.equals(bi, bean.bi)
				&& Objects.equals(bd, bean.bd)
				&& Objects.equals(bol, bean.bol)
				&& anEnum == bean.anEnum
				&& Objects.equals(innerBean, bean.innerBean);
		}

		@Override
		public int hashCode() {

			int result = Objects.hash(i, in, d, dou, l, lo, f, fl, st, ld, lt, ldt, bi, bd, bo, bol, anEnum, innerBean);
			result = 31 * result + Arrays.hashCode(b);
			return result;
		}

	}

	@Test
	void testCreateBeans() {

		int expectedBeanCount = 50;

		RandomBeanSupplier<Bean> randomBeanSupplier = RandomBeanSupplierRegistry.getRandomBeanSupplier(Bean.class);
		Set<Bean> beans = new HashSet<>(randomBeanSupplier.createBeans(expectedBeanCount));

		assertEquals(expectedBeanCount, beans.size());
	}

}
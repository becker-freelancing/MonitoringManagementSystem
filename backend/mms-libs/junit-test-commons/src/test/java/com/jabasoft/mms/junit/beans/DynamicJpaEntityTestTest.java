package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

class DynamicJpaEntityTestTest extends DynamicBeanTestTest {

	@Test
	void testBeanClassesStreamContainsEntity() {

		Class<CorrectAnnotatedEntity> entityClass = CorrectAnnotatedEntity.class;

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return entityClass;
			}
		};

		assertEquals(1, dummyDynamicJpaEntityTest.beanClasses().count(), "beanClasses.count()");
		assertTrue(
			dummyDynamicJpaEntityTest.beanClasses().toList().contains(entityClass),
			"BeanClasses contains " + entityClass.getSimpleName());
	}

	@Test
	void testEntityAnnotatedWithEntityPasses() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return CorrectAnnotatedEntity.class;
			}
		};

		dummyDynamicJpaEntityTest.testClassIsAnnotatedWithEntity();
	}

	@Test
	void testEntityAnnotatedWithTablePasses() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return CorrectAnnotatedEntity.class;
			}
		};

		dummyDynamicJpaEntityTest.testClassIsAnnotatedWithTable();
	}

	@Test
	void testEntityAttributesAnnotatedWithColumnPasses() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return CorrectAnnotatedEntity.class;
			}
		};

		dummyDynamicJpaEntityTest.testAttributesAreCorrectAnnotated();
	}

	@Test
	void testEntityAttributesNotAnnotatedWithColumnPasses() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return NotCorrectAnnotatedEntity.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testAttributesAreCorrectAnnotated);
	}

	@Test
	void testEntityNotAnnotatedWithEntityFails() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return NotCorrectAnnotatedEntity.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testClassIsAnnotatedWithEntity);
	}

	@Test
	void testEntityNotAnnotatedWithTableFails() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return NotCorrectAnnotatedEntity.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testClassIsAnnotatedWithTable);
	}

	@Test
	void testWithNotCorrectOneToOneAttributeInEntityFails() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return NotCorrectOneToOneAnnotatedEntity.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testAttributesAreCorrectAnnotated);
	}

	@Test
	void testWithNotCorrectOneToManyAttributeInEntityFails() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return NotCorrectOneToManyAnnotatedEntity.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testAttributesAreCorrectAnnotated);
	}



	private static class DummyDynamicJpaEntityTest extends DynamicJpaEntityTest {

		@Override
		protected Class<?> createEntity() {

			return null;
		}

	}

	@Entity
	protected static class InnerEntityObject{

	}

	@Table
	@Entity
	protected static class CorrectAnnotatedEntity {

		@Column
		private int col1;

		@Column
		private int col2;

		@OneToOne
		@JoinColumn
		private InnerEntityObject col3;

		@OneToMany
		@JoinTable
		private List<InnerEntityObject> col4;
	}

	protected static class NotCorrectAnnotatedEntity {

		@Column
		private int col1;

		private int col2;

	}

	@Entity
	@Table
	protected static class NotCorrectOneToOneAnnotatedEntity{

		@OneToOne
		private InnerEntityObject col1;
	}



	@Entity
	@Table
	protected static class NotCorrectOneToManyAnnotatedEntity{

		@OneToMany
		private List<InnerEntityObject> col1;
	}

}

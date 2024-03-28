package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

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

			@Override
			protected Class<? extends Annotation> entityAnnotation() {

				return EntityAnnotationMock.class;
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

			@Override
			protected Class<? extends Annotation> tableAnnotation() {

				return TableAnnotationMock.class;
			}
		};

		dummyDynamicJpaEntityTest.testClassIsAnnotatedWithTable();
	}

	@Test
	void testEntityAttributesAnnotatedWithColumnPasses() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<? extends Annotation> columnAnnotation() {

				return ColumnAnnotationMock.class;
			}

			@Override
			protected Class<?> createEntity() {

				return CorrectAnnotatedEntity.class;
			}
		};

		dummyDynamicJpaEntityTest.testAttributesAreAnnotatedWithColumn();
	}

	@Test
	void testEntityAttributesNotAnnotatedWithColumnPasses() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<? extends Annotation> columnAnnotation() {

				return ColumnAnnotationMock.class;
			}

			@Override
			protected Class<?> createEntity() {

				return NotCorrectAnnotatedEntity.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testAttributesAreAnnotatedWithColumn);
	}

	@Test
	void testEntityNotAnnotatedWithEntityFails() {

		DummyDynamicJpaEntityTest dummyDynamicJpaEntityTest = new DummyDynamicJpaEntityTest() {

			@Override
			protected Class<?> createEntity() {

				return NotCorrectAnnotatedEntity.class;
			}

			@Override
			protected Class<? extends Annotation> entityAnnotation() {

				return EntityAnnotationMock.class;
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

			@Override
			protected Class<? extends Annotation> tableAnnotation() {

				return TableAnnotationMock.class;
			}
		};

		Assertions.assertThrows(AssertionFailedError.class, dummyDynamicJpaEntityTest::testClassIsAnnotatedWithTable);
	}

	@Retention(RetentionPolicy.RUNTIME)
	private @interface TableAnnotationMock {

	}

	@Retention(RetentionPolicy.RUNTIME)
	private @interface EntityAnnotationMock {

	}

	@Retention(RetentionPolicy.RUNTIME)
	private @interface ColumnAnnotationMock {

	}

	private static class DummyDynamicJpaEntityTest extends DynamicJpaEntityTest {

		@Override
		protected Class<? extends Annotation> columnAnnotation() {

			return null;
		}

		@Override
		protected Class<?> createEntity() {

			return null;
		}

		@Override
		protected Class<? extends Annotation> entityAnnotation() {

			return null;
		}

		@Override
		protected Class<? extends Annotation> tableAnnotation() {

			return null;
		}

	}

	@TableAnnotationMock
	@EntityAnnotationMock
	protected static class CorrectAnnotatedEntity {

		@ColumnAnnotationMock
		private int col1;

		@ColumnAnnotationMock
		private int col2;

	}

	protected static class NotCorrectAnnotatedEntity {

		@ColumnAnnotationMock
		private int col1;

		private int col2;

	}

}

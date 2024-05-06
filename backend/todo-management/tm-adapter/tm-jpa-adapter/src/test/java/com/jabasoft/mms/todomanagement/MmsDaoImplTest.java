package com.jabasoft.mms.todomanagement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jabasoft.mms.todomanagement.config.JpaTodoConfig;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = JpaTodoConfig.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MmsDaoImplTest {

}

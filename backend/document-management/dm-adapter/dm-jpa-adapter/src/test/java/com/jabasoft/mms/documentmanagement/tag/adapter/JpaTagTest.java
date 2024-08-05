package com.jabasoft.mms.documentmanagement.tag.adapter;

import com.jabasoft.mms.documentmanagement.entity.JpaTag;
import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaTagTest extends DynamicJpaEntityTest {

    @Override
    protected Class<?> createEntity() {
        return JpaTag.class;
    }
}
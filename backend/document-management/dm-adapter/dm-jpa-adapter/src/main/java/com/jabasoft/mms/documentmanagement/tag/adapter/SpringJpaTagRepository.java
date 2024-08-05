package com.jabasoft.mms.documentmanagement.tag.adapter;

import com.jabasoft.mms.documentmanagement.entity.JpaTag;
import org.springframework.data.repository.CrudRepository;

public interface SpringJpaTagRepository extends CrudRepository<JpaTag, String> {
}

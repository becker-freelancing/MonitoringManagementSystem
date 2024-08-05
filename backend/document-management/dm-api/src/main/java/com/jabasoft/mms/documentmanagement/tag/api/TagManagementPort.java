package com.jabasoft.mms.documentmanagement.tag.api;

import com.jabasoft.mms.documentmanagement.dto.TagDto;

import java.util.Set;

public interface TagManagementPort {

    public Set<TagDto> getAllTags();
}

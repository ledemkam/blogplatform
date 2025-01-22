package com.kte.blog.mappers;

import com.kte.blog.domain.dtos.CreatePostRequestDto;
import com.kte.blog.domain.dtos.PostDto;
import com.kte.blog.domain.dtos.UpdatePostRequestDto;
import com.kte.blog.domain.entities.Post;
import com.kte.blog.domain.enumeration.CreatePostRequest;
import com.kte.blog.domain.enumeration.UpdatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "status", source = "status")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}

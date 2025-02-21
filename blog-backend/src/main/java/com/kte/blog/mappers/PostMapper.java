package com.kte.blog.mappers;


import com.kte.blog.domain.dtos.CreatePostRequestDto;
import com.kte.blog.domain.dtos.PostDto;
import com.kte.blog.domain.dtos.UpdatePostRequestDto;
import com.kte.blog.domain.entities.Post;
import com.kte.blog.domain.request.CreatePostRequest;
import com.kte.blog.domain.request.UpdatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target= "author", source = "author")
    @Mapping(target= "category", source = "category")
    @Mapping(target= "tags", source = "tags")
    PostDto toDto(Post post);
    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}

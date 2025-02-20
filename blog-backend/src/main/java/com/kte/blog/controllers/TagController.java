package com.kte.blog.controllers;


import com.kte.blog.domain.dtos.CreateTagsRequest;
import com.kte.blog.domain.dtos.TagDto;
import com.kte.blog.domain.entities.Tag;
import com.kte.blog.mappers.TagMapper;
import com.kte.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tagMapper.toTagResponseList(tags));
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedtags = tagService.createTags(createTagsRequest.getNames());
        List<TagDto> createdTagRespons = savedtags.stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return new ResponseEntity<>(
                createdTagRespons,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return  ResponseEntity.noContent().build();
    }

}

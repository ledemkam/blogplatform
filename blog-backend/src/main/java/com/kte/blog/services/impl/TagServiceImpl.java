package com.kte.blog.services.impl;

import com.kte.blog.domain.entities.Tag;
import com.kte.blog.repositories.TagRepository;
import com.kte.blog.services.TagService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostCount();
    }


    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        Set<Tag> existingTagSet = tagRepository.findByNameIn(tagNames);
        List<Tag> existingTags = new ArrayList<>(existingTagSet);

        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();

        List<Tag> savedTags = new ArrayList<>();
        if(!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty()) {
                throw new IllegalStateException("Cannot delete tag with associated posts");
            }
            tagRepository.deleteById(id);
        });

    }

    @Override
    public Tag getTagById(UUID id){
      return   tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id " + id));

    }

}

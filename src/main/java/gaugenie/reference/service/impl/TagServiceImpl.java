package gaugenie.reference.service.impl;

import gaugenie.reference.entity.Tag;
import gaugenie.reference.repository.TagRepository;
import gaugenie.reference.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Optional<Tag> getTagById(Integer id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        Tag existingTag = tagRepository.findById(tag.getId()).orElseThrow(() -> new RuntimeException("Tag not found"));
        existingTag.setName(tag.getName());
        return tagRepository.save(existingTag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public boolean isNameUnique(Integer id, String name) {
        Tag existingTag = tagRepository.findByName(name);
        return existingTag == null || existingTag.getId().equals(id);

    }
}

package gaugenie.reference.service;

import gaugenie.reference.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<Tag> getTagById(Integer id);
    List<Tag> getAllTags();
    Tag getTagByName(String name);
    Tag saveTag(Tag tag);
    Tag updateTag(Tag tag);
    void deleteTag(Tag tag);

    boolean isNameUnique(Integer id, String name);

    // Aadd tag to reference

}

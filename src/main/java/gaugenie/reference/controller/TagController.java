package gaugenie.reference.controller;

import gaugenie.reference.entity.Tag;
import gaugenie.reference.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags/v1")
public class TagController {

    private final TagService tagService;

    // Rest API method to check tag name uniqueness
    @GetMapping("/check-unique")
    public String checkUnique(@Param("id") Integer id, @Param("name") String name) {
        return tagService.isNameUnique(id, name)? "OK" : "Duplicated";
    }
    // Rest API method to get all tags
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
    // Rest API method to get tag by name
    @GetMapping("/by-name")
    public Tag getByName(@RequestParam("name") String name) {
        return tagService.getTagByName(name);
    }

    // Rest API method to get tag by id
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Integer id) {
        return tagService.getTagById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Rest API method to create tag
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }
    // Rest API method to update tag
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Integer id, @RequestBody Tag tag) {
        tag.setId(id);
        Tag updatedTag = tagService.updateTag(tag);
        return ResponseEntity.ok(updatedTag);
    }
    // Rest API method to delete tag
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        tagService.getTagById(id).ifPresent(tagService::deleteTag);
        return ResponseEntity.noContent().build();
    }
}

package gaugenie.reference.controller;

import gaugenie.reference.entity.Reference;
import gaugenie.reference.entity.Tag;
import gaugenie.reference.service.impl.ReferenceServiceImpl;
import gaugenie.reference.service.impl.TagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reference/v1")
public class ReferenceRestController {
    private final ReferenceServiceImpl referenceServiceImpl;
    private final TagServiceImpl tagServiceImpl;

   // rest api to get article by id
    @GetMapping("/{id}")
    public Reference getReferenceById(@PathVariable("id") Integer id) {
        return referenceServiceImpl.getReferenceById(id);
    }

    @GetMapping("/all")
    public List<Reference> getAllReferences() {
        return referenceServiceImpl.getReferenceList();
    }

    // rest api to create reference
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reference createReference(@RequestBody Reference reference) {
        return referenceServiceImpl.saveReference(reference);
    }

    // rest api to update reference
    @PutMapping("/{id}")
    public Reference updateReference(@PathVariable("id") Integer id, @RequestBody Reference reference) {
        return referenceServiceImpl.updateReference(id, reference);
    }
    // rest api to delete article
    @DeleteMapping("/delref/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReference(@PathVariable("id") Integer id) {
        referenceServiceImpl.deleteReference(id);
    }

    @PostMapping("/{id}/tags/{tagId}")
    public ResponseEntity<Reference> addTagToReference(@PathVariable Integer id, @PathVariable Integer tagId) {
        Reference reference = referenceServiceImpl.getReferenceById(id);
        Optional<Tag> tag = tagServiceImpl.getTagById(tagId);

        if (reference!= null && tag.isPresent()) {
            reference.getTags().add(tag.get());
            referenceServiceImpl.saveReference(reference);
            return ResponseEntity.ok(reference);
        }
        return ResponseEntity.notFound().build();
    }
}

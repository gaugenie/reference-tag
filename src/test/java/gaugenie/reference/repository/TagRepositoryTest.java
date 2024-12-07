package gaugenie.reference.repository;

import gaugenie.reference.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class TagRepositoryTest {
    @Autowired private TagRepository tagRepository;

    @Test
    void testCreateTag() {
        Tag tag = new Tag();
        tag.setName("Java Script");

        Tag savedTag = tagRepository.save(tag);
        assertNotNull(savedTag);
        assertNotNull(savedTag.getId());
    }

    @Test
    void testReadTagByName() {
        String tagName = "Spring"; // remplacez par le nom d'un tag existant
        Tag tag = tagRepository.findByName(tagName);

        assertNotNull(tag);
        assertEquals("Spring", tag.getName());
    }

    @Test
    void testUpdateTag(Tag tag) {
        // find id to existing tag
        Integer tagId = 1;
        Tag existingTag = tagRepository.findById(tagId).orElse(null);
        if (existingTag!= null) {
            existingTag.setName("Angular");
        }
        Tag updatedTag = tagRepository.save(existingTag);
        assertEquals("Angular", updatedTag.getName());

    }

    @Test
    public void testDeleteTag() {
        String tagName = "Spring Framework"; // remplacez par le nom d'un tag existant
        Tag tag = tagRepository.findByName(tagName);

        assertNotNull(tag);
        tagRepository.delete(tag);

        Tag deletedTag = tagRepository.findByName(tagName);
        assertNull(deletedTag);
    }


}


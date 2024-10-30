package gaugenie.reference;

import gaugenie.reference.entity.Tag;
import gaugenie.reference.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
 class TagRepositoryTests {
    @Autowired
    private TagRepository tagRepository;

    @Test
     void testCreateTag() {
        Tag tag = new Tag();
        tag.setName("Spring");

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
     void testUpdateTag() {
        String tagName = "Spring"; // remplacez par le nom d'un tag existant
        Tag tag = tagRepository.findByName(tagName);

        assertNotNull(tag);
        tag.setName("Spring Framework");

        Tag updatedTag = tagRepository.save(tag);
        assertEquals("Spring Framework", updatedTag.getName());
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

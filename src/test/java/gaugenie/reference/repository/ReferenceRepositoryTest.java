package gaugenie.reference.repository;

import gaugenie.reference.entity.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class ReferenceRepositoryTest {
  @Autowired private ReferenceRepository referenceRepository;

  @Autowired
  private TagRepository tagRepository;

  @Test
  void testCreateReference() {
    Reference reference = new Reference();
    reference.setTitle("Introduction to Spring Boot");
    reference.setAuthors("John Doe");
    reference.setJournal("Tech Journal");
    reference.setYear(2023);
    reference.setHyperlink("http://example.com");
    reference.setDescription("An introductory reference on Spring Boot.");

    Reference savedReference = referenceRepository.save(reference);
    assertNotNull(savedReference);
    assertNotNull(savedReference.getId());
  }

  @Test
  void testReadReferenceById() {
    Integer ReferenceId = 1; // remplacez par l'ID d'un Reference existant
    Optional<Reference> Reference = referenceRepository.findById(ReferenceId);

    assertTrue(Reference.isPresent());
    System.out.println(Reference.get().getTitle());
  }

  @Test
  void testUpdateReference() {
    Integer referenceId = 2; // remplacez par l'ID d'un reference existant
    Optional<Reference> referenceOpt = referenceRepository.findById(referenceId);

    assertTrue(referenceOpt.isPresent());
    Reference reference = referenceOpt.get();
    reference.setTitle("Advanced Spring Boot");

    Reference updatedReference = referenceRepository.save(reference);
    assertEquals("Advanced Spring Boot", updatedReference.getTitle());
  }

  @Test
  void testDeleteReference() {
    Integer referenceId = 1; // remplacez par l'ID d'un Reference existant
    referenceRepository.deleteById(referenceId);

    Optional<Reference> deletedReference = referenceRepository.findById(referenceId);
    assertFalse(deletedReference.isPresent());
  }

  @Test
  void testAddTagToReference() {
    Integer referenceId = 1; // remplacez par l'ID d'un Reference existant
    Integer tagId = 1; // remplacez par l'ID d'un tag existant
    Reference reference = referenceRepository.findById(referenceId).orElse(null);
    reference.getTags().add(tagRepository.findById(tagId).orElse(null));
    referenceRepository.save(reference);

    assertEquals(1, reference.getTags().size());
    assertTrue(reference.getTags().stream().anyMatch(tag -> tag.getId().equals(tagId)));
  }
}

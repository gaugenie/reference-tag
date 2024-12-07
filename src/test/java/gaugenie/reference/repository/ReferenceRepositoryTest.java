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
  void testCreateMultipleReferences(){

      Reference reference1 = new Reference();
      reference1.setTitle("Introduction to Java Core");
      reference1.setAuthors("Olivier Doe");
      reference1.setJournal("Tech Journal");
      reference1.setYear(2023);
      reference1.setHyperlink("http://example.com");
      reference1.setDescription("An introductory reference on Java Core.");

      Reference reference2 = new Reference();
      reference2.setTitle("Advanced Spring Boot");
      reference2.setAuthors("Jane Doe");
      reference2.setJournal("Tech Magazine");
      reference2.setYear(2022);
      reference2.setHyperlink("http://example.com/advanced");
      reference2.setDescription("An advanced reference on Spring Boot.");

      Reference reference3 = new Reference();
      reference3.setTitle("Mastering Spring Boot");
      reference3.setAuthors("Alice Doe");
      reference3.setJournal("Tech Review");
      reference3.setYear(2021);
      reference3.setHyperlink("http://example.com/mastering");
      reference3.setDescription("A mastering reference on Spring Boot.");

      Reference reference4 = new Reference();
      reference4.setTitle("Introduction to Java Script");
      reference4.setAuthors("jean francis Doe");
      reference4.setJournal("Tech new Java Script");
      reference4.setYear(2023);
      reference4.setHyperlink("http://example.com");
      reference4.setDescription("An introductory reference on Java Script.");


      Reference savedReference1 = referenceRepository.save(reference1);
      Reference savedReference2 = referenceRepository.save(reference2);
     Reference savedReference3 = referenceRepository.save(reference3);
    Reference savedReference4 =  referenceRepository.save(reference4);


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
    Integer referenceId = 3; // remplacez par l'ID d'un Reference existant
    Integer tagId = 2; // remplacez par l'ID d'un tag existant
    Reference reference = referenceRepository.findById(referenceId).orElse(null);
    reference.getTags().add(tagRepository.findById(tagId).orElse(null));
    referenceRepository.save(reference);

    assertEquals(1, reference.getTags().size());
    assertTrue(reference.getTags().stream().anyMatch(tag -> tag.getId().equals(tagId)));
  }

  @Test
  void testRemoveTagFromReference() {
    Integer referenceId = 6; // remplacez par l'ID d'un Reference existant
    Integer tagId = 4; // remplacez par l'ID d'un tag existant
    Reference reference = referenceRepository.findById(referenceId).orElse(null);
    reference.getTags().removeIf(tag -> tag.getId().equals(tagId));
    referenceRepository.save(reference);

    assertEquals(0, reference.getTags().size());
    assertFalse(reference.getTags().stream().anyMatch(tag -> tag.getId().equals(tagId)));
  }

  @Test
  void testSearchReferenceByTitle() {
    String searchTerm = "Spring Boot";
    Iterable<Reference> references = referenceRepository.findByTitleContaining(searchTerm);

    assertTrue(references.iterator().hasNext());
    references.forEach(reference -> System.out.println(reference.getTitle()));
  }
}

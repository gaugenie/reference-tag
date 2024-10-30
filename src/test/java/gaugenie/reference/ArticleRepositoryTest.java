package gaugenie.reference;

import gaugenie.reference.entity.Article;
import gaugenie.reference.repository.ArticleRepository;
import gaugenie.reference.repository.TagRepository;
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
class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
     void testCreateArticle() {
        Article article = new Article();
        article.setTitle("Introduction to Spring Boot");
        article.setAuthors("John Doe");
        article.setJournal("Tech Journal");
        article.setYear(2023);
        article.setHyperlink("http://example.com");
        article.setDescription("An introductory article on Spring Boot.");

        Article savedArticle = articleRepository.save(article);
        assertNotNull(savedArticle);
        assertNotNull(savedArticle.getId());
    }

    @Test
     void testReadArticleById() {
        Integer articleId = 1; // remplacez par l'ID d'un article existant
        Optional<Article> article = articleRepository.findById(articleId);

        assertTrue(article.isPresent());
        System.out.println(article.get().getTitle());
    }

    @Test
    void testUpdateArticle() {
        Integer articleId = 2; // remplacez par l'ID d'un article existant
        Optional<Article> articleOpt = articleRepository.findById(articleId);

        assertTrue(articleOpt.isPresent());
        Article article = articleOpt.get();
        article.setTitle("Advanced Spring Boot");

        Article updatedArticle = articleRepository.save(article);
        assertEquals("Advanced Spring Boot", updatedArticle.getTitle());
    }

    @Test
    void testDeleteArticle() {
        Integer articleId = 1; // remplacez par l'ID d'un article existant
        articleRepository.deleteById(articleId);

        Optional<Article> deletedArticle = articleRepository.findById(articleId);
        assertFalse(deletedArticle.isPresent());
    }


}

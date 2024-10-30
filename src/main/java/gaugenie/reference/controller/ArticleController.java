package gaugenie.reference.controller;

import gaugenie.reference.entity.Article;
import gaugenie.reference.entity.Tag;
import gaugenie.reference.service.ArticleService;
import gaugenie.reference.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles/v1")
public class ArticleController {
    private final ArticleService articleService;
    private final TagService tagService;

   // rest api to get article by id
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Integer id) {
        return articleService.getArticleById(id);
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getArticleList();
    }

    // rest api to create article
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    // rest api to update article
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable("id") Integer id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }
    // rest api to delete article
    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
    }

    @PostMapping("/{id}/tags/{tagId}")
    public ResponseEntity<Article> addTagToArticle(@PathVariable Integer id, @PathVariable Integer tagId) {
        Article article = articleService.getArticleById(id);
        Optional<Tag> tag = tagService.getTagById(tagId);

        if (article != null && tag.isPresent()) {
            article.getTags().add(tag.get());
            articleService.saveArticle(article);
            return ResponseEntity.ok(article);
        }
        return ResponseEntity.notFound().build();
    }
}

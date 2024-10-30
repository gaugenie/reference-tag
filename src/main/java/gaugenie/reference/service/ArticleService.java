package gaugenie.reference.service;

import gaugenie.reference.entity.Article;
import gaugenie.reference.repository.ArticleRepository;
import gaugenie.reference.service.impl.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements ArticleServiceImpl {

    private final ArticleRepository articleRepository;
    @Override
    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);

    }

    @Override
    public Article updateArticle(Integer id, Article article) {
        Article existingArticle = articleRepository.findById(id).orElse(null);

        if (existingArticle!= null) {
            existingArticle.setTitle(article.getTitle());
            existingArticle.setAuthors(article.getAuthors());
            existingArticle.setJournal(article.getJournal());
            existingArticle.setYear(article.getYear());
            existingArticle.setHyperlink(article.getHyperlink());
            existingArticle.setDescription(article.getDescription());
            return articleRepository.save(existingArticle);
        }
        return null;

    }

    @Override
    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }
}

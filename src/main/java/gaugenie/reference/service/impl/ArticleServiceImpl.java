package gaugenie.reference.service.impl;

import gaugenie.reference.entity.Article;

import java.util.List;

public interface ArticleServiceImpl {
    List<Article> getArticleList();
    Article getArticleById(Integer id);
    Article saveArticle(Article article);
    Article updateArticle(Integer id, Article article);
    void deleteArticle(Integer id);
}

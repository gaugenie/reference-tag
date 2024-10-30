package gaugenie.reference.repository;

import gaugenie.reference.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article ,Integer> {

    List<Article> findByTitleContaining(String title);
}

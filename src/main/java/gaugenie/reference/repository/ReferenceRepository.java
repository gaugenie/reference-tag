package gaugenie.reference.repository;

import gaugenie.reference.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference,Integer> {

    List<Reference> findByTitleContaining(String title);
}

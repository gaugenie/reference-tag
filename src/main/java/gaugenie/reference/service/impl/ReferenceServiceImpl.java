package gaugenie.reference.service.impl;

import gaugenie.reference.entity.Reference;
import gaugenie.reference.repository.ReferenceRepository;
import gaugenie.reference.repository.TagRepository;
import gaugenie.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;
    private final TagRepository tagRepository;
    @Override
    public List<Reference> getReferenceList() {
        return referenceRepository.findAll();
    }

    @Override
    public Reference getReferenceById(Integer id) {
        return referenceRepository.findById(id).orElse(null);
    }

    @Override
    public Reference saveReference(Reference reference) {
        return referenceRepository.save(reference);

    }

    @Override
    public Reference updateReference(Integer id, Reference reference) {
        Reference existingReference = referenceRepository.findById(id).orElse(null);

        if (existingReference != null) {
            existingReference.setTitle(reference.getTitle());
            existingReference.setAuthors(reference.getAuthors());
            existingReference.setJournal(reference.getJournal());
            existingReference.setYear(reference.getYear());
            existingReference.setHyperlink(reference.getHyperlink());
            existingReference.setDescription(reference.getDescription());
            return referenceRepository.save(existingReference);
        }
        return null;

    }

    @Override
    public void deleteReference(Integer id) {
        referenceRepository.deleteById(id);
    }

    @Override
    public void addTagToReference(Integer referenceId, Integer tagId) {
        Reference reference = referenceRepository.findById(referenceId).orElse(null);
        if (reference != null) {
            reference.getTags().add(tagRepository.findById(tagId).orElse(null));
            referenceRepository.save(reference);
        }
        else {
            throw new RuntimeException("Reference not found");
        }

    }

    @Override
    public List<Reference> getReferencesByTag(Integer tagId) {
        List<Reference> references = new ArrayList<Reference>();
        for (Reference reference : referenceRepository.findAll()) {
            if (reference.getTags().contains(tagRepository.findById(tagId).orElse(null))) {
                references.add(reference);
            }
        }
        return references;
    }
}

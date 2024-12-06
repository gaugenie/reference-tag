package gaugenie.reference.service;

import gaugenie.reference.entity.Reference;

import java.util.List;

public interface ReferenceService {
    List<Reference> getReferenceList();
    Reference getReferenceById(Integer id);
    Reference saveReference(Reference reference);
    Reference updateReference(Integer id, Reference reference);
    void deleteReference(Integer id);
    void addTagToReference(Integer referenceId, Integer tagId);

    List<Reference> getReferencesByTag(Integer tagId);
}

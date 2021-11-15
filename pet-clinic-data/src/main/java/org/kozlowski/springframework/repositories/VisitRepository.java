package org.kozlowski.springframework.repositories;

import org.kozlowski.springframework.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}

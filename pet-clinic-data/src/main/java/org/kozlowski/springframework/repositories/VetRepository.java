package org.kozlowski.springframework.repositories;

import org.kozlowski.springframework.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}

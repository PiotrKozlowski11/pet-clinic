package org.kozlowski.springframework.repositories;

import org.kozlowski.springframework.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}

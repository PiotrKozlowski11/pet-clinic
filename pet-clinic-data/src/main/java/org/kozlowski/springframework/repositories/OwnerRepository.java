package org.kozlowski.springframework.repositories;

import org.kozlowski.springframework.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}

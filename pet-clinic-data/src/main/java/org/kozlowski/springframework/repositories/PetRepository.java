package org.kozlowski.springframework.repositories;

import org.kozlowski.springframework.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {


}

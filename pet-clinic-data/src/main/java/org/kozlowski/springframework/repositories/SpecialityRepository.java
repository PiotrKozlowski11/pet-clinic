package org.kozlowski.springframework.repositories;

import org.kozlowski.springframework.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}

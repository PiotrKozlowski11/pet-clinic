package org.kozlowski.springframework.services;

import org.kozlowski.springframework.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}

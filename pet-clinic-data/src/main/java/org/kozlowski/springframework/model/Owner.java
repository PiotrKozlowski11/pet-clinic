package org.kozlowski.springframework.model;

import java.util.Set;

public class Owner extends Person {
    private Set<Pet> petSet;

    public Set<Pet> getPetSet() {
        return petSet;
    }

    public void setPetSet(Set<Pet> petSet) {
        this.petSet = petSet;
    }
}

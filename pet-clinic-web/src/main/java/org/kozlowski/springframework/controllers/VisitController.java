package org.kozlowski.springframework.controllers;

import org.kozlowski.springframework.model.Pet;
import org.kozlowski.springframework.model.Visit;
import org.kozlowski.springframework.services.PetService;
import org.kozlowski.springframework.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class VisitController {

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId,
                                   Model model) {

        Pet pet = petService.findById(petId);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);

        model.addAttribute("pet", pet);
        model.addAttribute("visit", visit);

        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processCreationForm(@Valid Visit visit, BindingResult result, @PathVariable Long ownerId,
                                      @PathVariable Long petId, Model model) {

        Pet pet = petService.findById(petId);
        pet.getVisits().add(visit);
        visit.setPet(pet);

        //model.addAttribute("pet",pet);
        model.addAttribute("visit", visit);

        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }

    }

}

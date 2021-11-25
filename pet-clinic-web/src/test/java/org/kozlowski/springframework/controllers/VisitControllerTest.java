package org.kozlowski.springframework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.springframework.model.Owner;
import org.kozlowski.springframework.model.Pet;
import org.kozlowski.springframework.model.PetType;
import org.kozlowski.springframework.services.PetService;
import org.kozlowski.springframework.services.VisitService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest {

    VisitController controller;

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new VisitController(petService, visitService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    @Test
    void initNewVisitForm() throws Exception {
        Long petId = 1L;
        Long ownerId = 1L;

        Pet pet = Pet.builder()
                .id(petId)
                .birthDate(LocalDate.of(2018, 11, 13))
                .name("Cutie")
                .visits(new HashSet<>())
                .owner(Owner.builder()
                        .id(ownerId)
                        .lastName("Doe")
                        .firstName("Joe")
                        .build())
                .petType(PetType.builder()
                        .name("Dog").build())
                .build();

        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/2/pets/2/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        Long petId = 1L;
        Long ownerId = 1L;

        Pet pet = Pet.builder()
                .id(petId)
                .birthDate(LocalDate.of(2018, 11, 13))
                .name("Cutie")
                .visits(new HashSet<>())
                .owner(Owner.builder()
                        .id(ownerId)
                        .lastName("Doe")
                        .firstName("Joe")
                        .build())
                .petType(PetType.builder()
                        .name("Dog").build())
                .build();

        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post("/owners/2/pets/3/visits/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date", "2018-11-11")
                        .param("description", "yet another visit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        verify(visitService).save(any());
    }
}
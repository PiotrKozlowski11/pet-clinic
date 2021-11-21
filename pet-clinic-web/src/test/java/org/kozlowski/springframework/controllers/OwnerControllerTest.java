package org.kozlowski.springframework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.springframework.model.Owner;
import org.kozlowski.springframework.services.OwnerService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    OwnerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new OwnerController(ownerService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListOwners() throws Exception {
        Owner owner1 = Owner.builder().id(2L).build();
        Owner owner2 = Owner.builder().id(3L).build();

        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(owner1);
        ownerSet.add(owner2);

        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/owners", "/owners/", "/owners/index", "/owners/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attributeExists("owners"));

        verify(ownerService, times(1)).findAll();

    }


    @Test
    void testFindOwners() {
    }

    @Test
    void showOwner() throws Exception {

        Owner owner1 = Owner.builder().id(2L).build();

        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owners/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(2L))));

        verify(ownerService, times(1)).findById(anyLong());
    }
}
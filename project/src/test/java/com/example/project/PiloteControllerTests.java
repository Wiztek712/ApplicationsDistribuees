package com.example.project;

import com.example.project.pilote.Pilote;
import com.example.project.pilote.PiloteController;
import com.example.project.pilote.PiloteRepository;
import com.example.project.team.Team;
import com.example.project.team.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PiloteController.class)
public class PiloteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PiloteRepository piloteRepository;

    @MockBean
    private TeamRepository teamRepository;

    private Pilote testPilote;
    private Team testTeam;

    @BeforeEach
    void setUp() {
        testTeam = new Team();
        testTeam.setId(1L);
        testTeam.setName("Ferrari");

        testPilote = new Pilote();
        testPilote.setId(1L);
        testPilote.setName("Leclerc");
        testPilote.setNumber(16);
        testPilote.setTeam(testTeam);
    }

    @Test
    void testGetAllPilotes() throws Exception {
        when(piloteRepository.findAll()).thenReturn(List.of(testPilote));

        mockMvc.perform(get("/pilotes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Leclerc")))
                .andExpect(jsonPath("$[0].number", is(16)));
    }

    @Test
    void testGetPiloteById() throws Exception {
        when(piloteRepository.findById(1L)).thenReturn(Optional.of(testPilote));

        mockMvc.perform(get("/pilotes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Leclerc")))
                .andExpect(jsonPath("$.number", is(16)));
    }

    @Test
    void testDeletePilote() throws Exception {
        doNothing().when(piloteRepository).deleteById(1L);

        mockMvc.perform(delete("/pilotes/1"))
                .andExpect(status().isOk());

        verify(piloteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testReplacePilote() throws Exception {
        when(piloteRepository.findById(1L)).thenReturn(Optional.of(testPilote));
        when(piloteRepository.existsByNumber(44)).thenReturn(false);
        when(piloteRepository.save(ArgumentMatchers.<Pilote>any())).thenReturn(testPilote);

        mockMvc.perform(put("/pilotes/1")
                        .param("name", "Hamilton")
                        .param("number", "44")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Hamilton")))
                .andExpect(jsonPath("$.number", is(44)));
    }
}

package com.example.project;

import com.example.project.team.Team;
import com.example.project.team.TeamController;
import com.example.project.team.TeamRepository;
import com.example.project.pilote.Pilote;
import com.example.project.pilote.PiloteRepository;

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

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private PiloteRepository piloteRepository;

    private Team testTeam;
    private Pilote testPilote;

    @BeforeEach
    void setUp() {
        testTeam = new Team();
        testTeam.setId(1L);
        testTeam.setName("Ferrari");
        testTeam.setHeadQuarters("Maranello");

        testPilote = new Pilote();
        testPilote.setId(1L);
        testPilote.setName("Leclerc");
        testPilote.setNumber(16);
        testPilote.setTeam(testTeam);

        testTeam.setPilotes(List.of(testPilote));
    }

    @Test
    void testGetAllTeams() throws Exception {
        when(teamRepository.findAll()).thenReturn(List.of(testTeam));

        mockMvc.perform(get("/teams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Ferrari")))
                .andExpect(jsonPath("$[0].headQuarters", is("Maranello")))
                .andExpect(jsonPath("$[0].pilotes[0].name", is("Leclerc")))
                .andExpect(jsonPath("$[0].pilotes[0].number", is(16)));
    }

    @Test
    void testCreateTeam() throws Exception {
        when(teamRepository.save(ArgumentMatchers.any(Team.class))).thenReturn(testTeam);

        mockMvc.perform(post("/teams")
                        .param("name", "Ferrari")
                        .param("headQuarters", "Maranello")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ferrari")))
                .andExpect(jsonPath("$.headQuarters", is("Maranello")));
    }

    @Test
    void testGetTeamById() throws Exception {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(testTeam));

        mockMvc.perform(get("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ferrari")))
                .andExpect(jsonPath("$.headQuarters", is("Maranello")))
                .andExpect(jsonPath("$.pilotes[0].name", is("Leclerc")));
    }

    @Test
    void testReplaceTeam() throws Exception {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(testTeam));
        when(teamRepository.existsByName("Ferrari")).thenReturn(false);
        when(teamRepository.save(ArgumentMatchers.any(Team.class))).thenReturn(testTeam);

        mockMvc.perform(put("/teams/1")
                        .param("name", "Mercedes")
                        .param("headQuarters", "Brackley")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Mercedes")))
                .andExpect(jsonPath("$.headQuarters", is("Brackley")));
    }

    @Test
    void testDeleteTeam() throws Exception {
        doNothing().when(teamRepository).deleteById(1L);

        mockMvc.perform(delete("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(teamRepository, times(1)).deleteById(1L);
    }
}

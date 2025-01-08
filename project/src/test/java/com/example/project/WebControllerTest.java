package com.example.project;

import com.example.project.pilote.Pilote;
import com.example.project.pilote.PiloteRepository;
import com.example.project.team.Team;
import com.example.project.team.TeamRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(WebController.class)
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PiloteRepository piloteRepository;

    @MockBean
    private TeamRepository teamRepository;

    @Mock
    private Model model;

    private Pilote testPilote;
    private Team testTeam;

    @BeforeEach
    void setUp() {
        testPilote = new Pilote();
        testPilote.setId(1L);
        testPilote.setName("Leclerc");
        testPilote.setNumber(16);

        testTeam = new Team();
        testTeam.setId(1L);
        testTeam.setName("Ferrari");
        testTeam.setHeadQuarters("Maranello");
        testTeam.setPilotes(List.of(testPilote));

        testPilote.setTeam(testTeam);
    }

    @Test
    void testIndexPage() throws Exception {
        mockMvc.perform(get("/web/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testListPilotesPage() throws Exception {
        when(piloteRepository.findAll()).thenReturn(List.of(testPilote));

        mockMvc.perform(get("/web/pilotes"))
                .andExpect(status().isOk())
                .andExpect(view().name("pilotes"))
                .andExpect(model().attributeExists("pilotes"))
                .andExpect(model().attribute("pilotes", hasSize(1)))
                .andExpect(model().attribute("pilotes", hasItem(
                        allOf(
                                hasProperty("name", is("Leclerc")),
                                hasProperty("number", is(16))
                        )
                )));
    }

    @Test
    void testListTeamsPage() throws Exception {
        when(teamRepository.findAll()).thenReturn(List.of(testTeam));

        mockMvc.perform(get("/web/teams"))
                .andExpect(status().isOk())
                .andExpect(view().name("teams"))
                .andExpect(model().attributeExists("teams"))
                .andExpect(model().attribute("teams", hasSize(1)))
                .andExpect(model().attribute("teams", hasItem(
                        allOf(
                                hasProperty("name", is("Ferrari")),
                                hasProperty("headQuarters", is("Maranello"))
                        )
                )));
    }
}

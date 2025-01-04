package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.pilote.PiloteRepository;
import com.example.project.team.TeamRepository;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private PiloteRepository piloteRepository;
    
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/pilotes")
    public String listPilotes(Model model) {
        model.addAttribute("pilotes", piloteRepository.findAll());
        return "pilotes";
    }

    @GetMapping("/teams")
    public String listTeams(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }
}
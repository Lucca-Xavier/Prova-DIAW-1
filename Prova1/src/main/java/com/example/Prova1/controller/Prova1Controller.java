package com.example.Prova1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Prova1.model.Candidato;
import org.springframework.ui.Model;

import com.example.Prova1.service.Prova1Service;

@Controller
public class Prova1Controller {
    private final Prova1Service prova1Service;

    public Prova1Controller(Prova1Service prova1service) {
        this.prova1Service = prova1service;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String partido,
            @RequestParam(required = false) String texto,
            Model model) {

        List<Candidato> candidatos = prova1Service.filtrar(cargo, partido, texto);

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("cargos", prova1Service.listarCargos());
        model.addAttribute("partidos", prova1Service.listarPartidos());

        model.addAttribute("cargoSelecionado", cargo);
        model.addAttribute("partidoSelecionado", partido);
        model.addAttribute("textoBuscado", texto);

        return "index";
    }
}

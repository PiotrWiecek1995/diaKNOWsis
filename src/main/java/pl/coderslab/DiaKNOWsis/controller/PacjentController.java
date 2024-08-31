package pl.coderslab.DiaKNOWsis.controller;

import pl.coderslab.DiaKNOWsis.model.Pacjent;
import pl.coderslab.DiaKNOWsis.service.PacjentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacjenci")
public class PacjentController {

    @Autowired
    private PacjentService pacjentService;


    @GetMapping("/lista")
    public String listaPacjentow(Model model) {
        List<Pacjent> pacjenci = pacjentService.getAllPacjenci();
        model.addAttribute("pacjenci", pacjenci);
        return "lista";
    }


    @GetMapping("/dodaj")
    public String dodajPacjentaForm(Model model) {
        model.addAttribute("pacjent", new Pacjent());
        return "dodaj";
    }


    @PostMapping("/dodaj")
    public String dodajPacjenta(@ModelAttribute Pacjent pacjent) {
        pacjentService.savePacjent(pacjent);
        return "redirect:/pacjenci/lista";
    }


    @GetMapping("/edytuj/{id}")
    public String edytujPacjentaForm(@PathVariable Long id, Model model) {
        Pacjent pacjent = pacjentService.getPacjentById(id);
        model.addAttribute("pacjent", pacjent);
        return "edytuj"; // formularz edycji pacjenta
    }


    @PostMapping("/edytuj")
    public String edytujPacjenta(@ModelAttribute Pacjent pacjent) {
        pacjentService.savePacjent(pacjent);
        return "redirect:/pacjenci/lista";
    }


    @GetMapping("/usun/{id}")
    public String usunPacjenta(@PathVariable Long id) {
        pacjentService.deletePacjentById(id);
        return "redirect:/pacjenci/lista";
    }
    @GetMapping("")
    public String zarzadzaniePacjentami() {
        return "zarzadzanie";
    }

}

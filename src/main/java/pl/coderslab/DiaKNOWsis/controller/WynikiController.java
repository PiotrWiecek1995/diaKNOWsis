package pl.coderslab.DiaKNOWsis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.DiaKNOWsis.model.Pacjent;
import pl.coderslab.DiaKNOWsis.model.Wyniki;
import pl.coderslab.DiaKNOWsis.service.PacjentService;
import pl.coderslab.DiaKNOWsis.service.WynikiService;

import java.util.List;

@Controller
public class WynikiController {

    @Autowired
    private PacjentService pacjentService;

    @Autowired
    private WynikiService wynikiService;


    @GetMapping("/wyniki/dodaj/{pacjentId}")
    public String dodajWynikiForm(@PathVariable Long pacjentId, Model model) {
        Pacjent pacjent = pacjentService.getPacjentById(pacjentId);
        Wyniki wyniki = new Wyniki();
        model.addAttribute("pacjent", pacjent);
        model.addAttribute("wyniki", wyniki);
        return "dodaj_wyniki";
    }

    @PostMapping("/wyniki/dodaj/{pacjentId}")
    public String dodajWyniki(@PathVariable Long pacjentId, @ModelAttribute Wyniki wyniki) {
        Pacjent pacjent = pacjentService.getPacjentById(pacjentId);
        wyniki.setPacjent(pacjent);
        wynikiService.zapiszWyniki(wyniki);
        return "redirect:/pacjenci/lista";
    }


    @GetMapping("/wyniki/pokaz/{pacjentId}")
    public String pokazWyniki(@PathVariable Long pacjentId, Model model) {
        List<Wyniki> wynikiList = wynikiService.znajdzWynikiPoPacjencieId(pacjentId);
        Pacjent pacjent = pacjentService.getPacjentById(pacjentId);
        model.addAttribute("pacjent", pacjent);
        model.addAttribute("wynikiList", wynikiList);
        return "pokaz_wyniki";
    }
}

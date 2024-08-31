package pl.coderslab.DiaKNOWsis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.DiaKNOWsis.model.Choroba;
import pl.coderslab.DiaKNOWsis.model.Wyniki;
import pl.coderslab.DiaKNOWsis.service.ChorobaService;
import pl.coderslab.DiaKNOWsis.service.WynikiService;

import java.util.List;

@Controller
public class DiagnosisController {

    @Autowired
    private ChorobaService chorobaService;

    @Autowired
    private WynikiService wynikiService;

    @GetMapping("/diagnosis/{pacjentId}")
    public String wybierzWynikiDoDiagnozy(@PathVariable Long pacjentId, Model model) {
        List<Wyniki> wynikiList = wynikiService.znajdzWynikiPoPacjencieId(pacjentId);
        model.addAttribute("wynikiList", wynikiList);
        model.addAttribute("pacjentId", pacjentId);
        return "pokaz_wyniki";
    }

    @PostMapping("/diagnosis/wyniki")
    public String zdiagnozujWybraneWyniki(@RequestParam Long pacjentId, @RequestParam Long wynikId, Model model) {

        Wyniki wybraneWyniki = wynikiService.znajdzWynikPoId(wynikId);
        List<Choroba> pasujaceChoroby = chorobaService.diagnozuj(wybraneWyniki);
        model.addAttribute("pasujaceChoroby", pasujaceChoroby);
        return "wynik_diagnozy";
    }

    @GetMapping("/diagnosis/{pacjentId}/{wynikId}")
    public String diagnozujPacjenta(@PathVariable Long pacjentId, @PathVariable Long wynikId, Model model) {
        Wyniki wyniki = wynikiService.znajdzWynikPoId(wynikId);
        List<Choroba> pasujaceChoroby = chorobaService.diagnozuj(wyniki);
        model.addAttribute("pasujaceChoroby", pasujaceChoroby);
        return "wynik_diagnozy";
    }
}

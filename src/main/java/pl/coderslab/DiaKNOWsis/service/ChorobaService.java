
package pl.coderslab.DiaKNOWsis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.DiaKNOWsis.model.Choroba;
import pl.coderslab.DiaKNOWsis.model.Parametr;
import pl.coderslab.DiaKNOWsis.model.Wyniki;
import pl.coderslab.DiaKNOWsis.repository.ChorobaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChorobaService {

    @Autowired
    private ChorobaRepository chorobaRepository;

    public List<Choroba> znajdzWszystkieChoroby() {
        return chorobaRepository.findAll();
    }


    @Transactional(readOnly = true)
    public List<Choroba> diagnozuj(Wyniki wyniki) {
        List<Choroba> choroby = znajdzWszystkieChoroby();
        List<Choroba> pasujaceChoroby = new ArrayList<>();

        for (Choroba choroba : choroby) {
            boolean pasuje = true;
            for (Parametr parametr : choroba.getParametry()) {
                Double wartosc = getWartoscWyniku(wyniki, parametr.getNazwa());
                if (wartosc == null || wartosc < parametr.getMinWartosc() || wartosc > parametr.getMaxWartosc()) {
                    pasuje = false;
                    break;
                }
            }
            if (pasuje) {
                pasujaceChoroby.add(choroba);
            }
        }

        return pasujaceChoroby;
    }

    private Double getWartoscWyniku(Wyniki wyniki, String nazwaParametru) {
        switch (nazwaParametru) {
            case "Morfologia":
                return wyniki.getMorfologia();
            case "Leukocyty":
                return wyniki.getLeukocyty();
            case "Erytrocyty":
                return wyniki.getErytrocyty();
            case "Hemoglobina":
                return wyniki.getHemoglobina();
            case "Hematokryt":
                return wyniki.getHematokryt();
            default:
                return null;
        }
    }
}

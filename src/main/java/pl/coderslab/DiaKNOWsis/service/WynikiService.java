package pl.coderslab.DiaKNOWsis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.DiaKNOWsis.model.Pacjent;
import pl.coderslab.DiaKNOWsis.model.Wyniki;
import pl.coderslab.DiaKNOWsis.repository.WynikiRepository;
import pl.coderslab.DiaKNOWsis.repository.PacjentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WynikiService {

    @Autowired
    private WynikiRepository wynikiRepository;

    @Autowired
    private PacjentRepository pacjentRepository;

    // Zapisz wyniki
    public void zapiszWyniki(Wyniki wyniki) {
        wynikiRepository.save(wyniki);
    }

    // Znajdź wyniki po pacjentId
    public List<Wyniki> znajdzWynikiPoPacjencieId(Long pacjentId) {
        Optional<Pacjent> pacjentOpt = pacjentRepository.findById(pacjentId);
        if (pacjentOpt.isPresent()) {
            Pacjent pacjent = pacjentOpt.get();
            return wynikiRepository.findByPacjent(pacjent);
        } else {
            throw new IllegalArgumentException("Pacjent o podanym ID nie istnieje");
        }
    }

    // Znajdź wynik po jego ID
    public Wyniki znajdzWynikPoId(Long id) {
        Optional<Wyniki> wynikiOpt = wynikiRepository.findById(id);
        if (wynikiOpt.isPresent()) {
            return wynikiOpt.get();
        } else {
            throw new IllegalArgumentException("Nie znaleziono wyniku o ID: " + id);
        }
    }
}

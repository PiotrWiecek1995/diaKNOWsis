package pl.coderslab.DiaKNOWsis.service;

import pl.coderslab.DiaKNOWsis.model.Pacjent;
import pl.coderslab.DiaKNOWsis.repository.PacjentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacjentService {

    @Autowired
    private PacjentRepository pacjentRepository;

    public void savePacjent(Pacjent pacjent) {
        pacjentRepository.save(pacjent);
    }

    public List<Pacjent> getAllPacjenci() {
        return pacjentRepository.findAll();
    }

    public Pacjent getPacjentById(Long id) {
        return pacjentRepository.findById(id).orElse(null);
    }

    public void deletePacjentById(Long id) {
        pacjentRepository.deleteById(id);
    }
}

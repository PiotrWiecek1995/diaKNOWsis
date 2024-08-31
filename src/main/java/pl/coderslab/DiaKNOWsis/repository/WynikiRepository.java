package pl.coderslab.DiaKNOWsis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.DiaKNOWsis.model.Pacjent;
import pl.coderslab.DiaKNOWsis.model.Wyniki;

import java.util.List;

@Repository
public interface WynikiRepository extends JpaRepository<Wyniki, Long> {

    List<Wyniki> findByPacjent(Pacjent pacjent);
}

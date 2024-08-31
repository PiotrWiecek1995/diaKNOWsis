package pl.coderslab.DiaKNOWsis.repository;

import pl.coderslab.DiaKNOWsis.model.Pacjent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacjentRepository extends JpaRepository<Pacjent, Long> {
}

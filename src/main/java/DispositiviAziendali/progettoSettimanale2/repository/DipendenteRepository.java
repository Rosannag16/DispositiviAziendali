package DispositiviAziendali.progettoSettimanale2.repository;

import DispositiviAziendali.progettoSettimanale2.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}
package DispositiviAziendali.progettoSettimanale2.repository;

import DispositiviAziendali.progettoSettimanale2.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
}
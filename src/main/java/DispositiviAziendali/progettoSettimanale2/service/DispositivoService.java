package DispositiviAziendali.progettoSettimanale2.service;

import DispositiviAziendali.progettoSettimanale2.model.Dipendente;
import DispositiviAziendali.progettoSettimanale2.model.Dispositivo;
import DispositiviAziendali.progettoSettimanale2.repository.DipendenteRepository;
import DispositiviAziendali.progettoSettimanale2.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;
    public String assegnaDispositivo(Long dipendenteId, Long dispositivoId) {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new IllegalArgumentException("Dipendente non trovato con ID: " + dipendenteId));

        Dispositivo dispositivo = dispositivoRepository.findById(dispositivoId)
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo non trovato con ID: " + dispositivoId));

        dipendente.addDispositivo(dispositivo);

        dipendenteRepository.save(dipendente);

        return "Dispositivo assegnato con successo al dipendente";
    }


    public void eseguiOperazioniConDispositivi() {
        Dipendente dipendente = new Dipendente();
        dipendenteRepository.save(dipendente);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipo("Laptop");
        dispositivo.setStato("Nuovo");

        dipendente.addDispositivo(dispositivo);

        dispositivoRepository.save(dispositivo);

        Long idDaCercare = 1L;
        Dispositivo dispositivoTrovato = dispositivoRepository.findById(idDaCercare).orElse(null);
    }








}

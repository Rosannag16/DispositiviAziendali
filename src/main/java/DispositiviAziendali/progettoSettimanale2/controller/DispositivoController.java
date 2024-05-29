package DispositiviAziendali.progettoSettimanale2.controller;

import DispositiviAziendali.progettoSettimanale2.dto.DispositivoDto;
import DispositiviAziendali.progettoSettimanale2.model.Dispositivo;
import DispositiviAziendali.progettoSettimanale2.repository.DispositivoRepository;
import DispositiviAziendali.progettoSettimanale2.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DispositivoController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivi")
    public ResponseEntity<List<Dispositivo>> getDispositivi() {
        List<Dispositivo> dispositivi = dispositivoRepository.findAll();
        return new ResponseEntity<>(dispositivi, HttpStatus.OK);
    }

    @PostMapping("/dispositivi")
    public ResponseEntity<Dispositivo> createDispositivo(@RequestBody DispositivoDto dispositivoDTO) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipo(dispositivoDTO.getTipo());
        dispositivo.setStato(dispositivoDTO.getStato());
        Dispositivo savedDispositivo = dispositivoRepository.save(dispositivo);
        return new ResponseEntity<>(savedDispositivo, HttpStatus.CREATED);
    }

    @PutMapping("/dispositivi/{id}")
    public ResponseEntity<Dispositivo> updateDispositivo(@PathVariable Long id, @RequestBody DispositivoDto dispositivoDTO) {
        Optional<Dispositivo> optionalDispositivo = dispositivoRepository.findById(id);
        if (optionalDispositivo.isPresent()) {
            Dispositivo dispositivo = optionalDispositivo.get();
            dispositivo.setTipo(dispositivoDTO.getTipo());
            dispositivo.setStato(dispositivoDTO.getStato());
            Dispositivo updatedDispositivo = dispositivoRepository.save(dispositivo);
            return new ResponseEntity<>(updatedDispositivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/dispositivi/{id}")
    public ResponseEntity<String> deleteDispositivo(@PathVariable Long id) {
        Optional<Dispositivo> optionalDispositivo = dispositivoRepository.findById(id);
        if (optionalDispositivo.isPresent()) {
            dispositivoRepository.deleteById(id);
            return new ResponseEntity<>("Dispositivo eliminato con successo", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Dispositivo non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/assegna-dispositivo/{dipendenteId}/{dispositivoId}")
    public ResponseEntity<String> assegnaDispositivo(
            @PathVariable Long dipendenteId,
            @PathVariable Long dispositivoId) {
        String result = dispositivoService.assegnaDispositivo(dipendenteId, dispositivoId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

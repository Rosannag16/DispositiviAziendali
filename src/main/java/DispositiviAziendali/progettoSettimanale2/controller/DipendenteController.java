package DispositiviAziendali.progettoSettimanale2.controller;

import DispositiviAziendali.progettoSettimanale2.dto.DipendenteDto;
import DispositiviAziendali.progettoSettimanale2.exception.DipendenteNotFoundException;
import DispositiviAziendali.progettoSettimanale2.model.Dipendente;
import DispositiviAziendali.progettoSettimanale2.repository.DipendenteRepository;
import DispositiviAziendali.progettoSettimanale2.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DipendenteController {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @GetMapping("/dipendenti")
    public ResponseEntity<List<Dipendente>> getDipendenti() {
        List<Dipendente> dipendenti = dipendenteRepository.findAll();
        return new ResponseEntity<>(dipendenti, HttpStatus.OK);
    }

    @PostMapping("/dipendenti")
    public ResponseEntity<Dipendente> createDipendente(@RequestBody DipendenteDto dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());
        //dipendente.setUrlImmagineProfilo();
        Dipendente savedDipendente = dipendenteRepository.save(dipendente);
        return new ResponseEntity<>(savedDipendente, HttpStatus.CREATED);
    }

    @PutMapping("/dipendenti/{id}")
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id, @RequestBody DipendenteDto dipendenteDTO) {
        Optional<Dipendente> optionalDipendente = dipendenteRepository.findById(id);
        if (optionalDipendente.isPresent()) {
            Dipendente dipendente = optionalDipendente.get();
            dipendente.setUsername(dipendenteDTO.getUsername());
            dipendente.setNome(dipendenteDTO.getNome());
            dipendente.setCognome(dipendenteDTO.getCognome());
            dipendente.setEmail(dipendenteDTO.getEmail());
            Dipendente updatedDipendente = dipendenteRepository.save(dipendente);
            return new ResponseEntity<>(updatedDipendente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dipendenti/{id}")
    public ResponseEntity<String> deleteDipendente(@PathVariable Long id) {
        Optional<Dipendente> optionalDipendente = dipendenteRepository.findById(id);
        if (optionalDipendente.isPresent()) {
            dipendenteRepository.deleteById(id);
            return new ResponseEntity<>("Dipendente eliminato con successo", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Dipendente non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/foto")
    public ResponseEntity<String> patchFoto(@PathVariable int id, @RequestParam("foto") MultipartFile foto) {
        try {
            String risultato = DipendenteService.patchFoto(id, foto);
            return new ResponseEntity<>(risultato, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Errore nel caricamento della foto", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DipendenteNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

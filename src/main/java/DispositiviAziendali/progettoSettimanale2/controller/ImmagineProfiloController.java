package DispositiviAziendali.progettoSettimanale2.controller;

import DispositiviAziendali.progettoSettimanale2.exception.DispositivoNotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import DispositiviAziendali.progettoSettimanale2.model.Dipendente;
import DispositiviAziendali.progettoSettimanale2.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class ImmagineProfiloController {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary; // Iniezione di dipendenza per Cloudinary

    @PatchMapping("/dipendenti/{dipendenteId}")
    public ResponseEntity<String> uploadImmagineProfilo(
            @PathVariable Long dipendenteId, @RequestBody() MultipartFile file) {
        try {
            Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                    .orElseThrow(() -> new DispositivoNotFoundException("Dipendente non trovato con id: " + dipendenteId));

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            dipendente.setUrlImmagineProfilo(imageUrl);
            dipendenteRepository.save(dipendente);

            return new ResponseEntity<>("Immagine profilo caricata con successo", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Errore durante il caricamento dell'immagine profilo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

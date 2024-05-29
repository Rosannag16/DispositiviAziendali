package DispositiviAziendali.progettoSettimanale2.service;

import DispositiviAziendali.progettoSettimanale2.exception.DipendenteNotFoundException;
import DispositiviAziendali.progettoSettimanale2.model.Dipendente;
import DispositiviAziendali.progettoSettimanale2.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class DipendenteService {


    @Autowired
    private static DipendenteRepository dipendenteRepository;


    @Autowired
    private static Cloudinary cloudinary;

    public Optional<Dipendente> getDipendenteeById(long id) {
        return dipendenteRepository.findById(id);
    }
    public static String patchFoto(long id, MultipartFile foto) throws IOException {
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(id);

        if (dipendenteOptional.isPresent()) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Dipendente dipendente = dipendenteOptional.get();

            dipendente.setUrlImmagineProfilo(url);
            dipendenteRepository.save(dipendente);
            return "Dipendente con ID: " + id + " aggiornato correttamente";
        } else {
            throw new DipendenteNotFoundException("Dipendente con id:" + id + " non trovato/a");
        }
    }
}

package DispositiviAziendali.progettoSettimanale2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DipendenteDto {

    @NotNull(message = "L'id' non può essere null")
    private Long id;
    @NotNull (message = "L'username non può essere null")
    private String username;
    @NotNull (message = "Il nome non può essere null")
    private String nome;
    @NotNull (message = "Il cognome non può essere null")
    private String cognome;
    @NotNull (message = "L'email non può essere null")
    private String email;
    private String urlImmagineProfilo;


}

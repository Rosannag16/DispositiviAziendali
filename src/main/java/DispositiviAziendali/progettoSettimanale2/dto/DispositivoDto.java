package DispositiviAziendali.progettoSettimanale2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoDto {
    @NotNull(message = "l'id non può essere null")
    private Long Id;
    @NotNull(message = "Il tipo non può essere null")
    private String tipo;
    @NotNull (message = "Lo stato non può essere null")
    private String stato;



}

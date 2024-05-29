package DispositiviAziendali.progettoSettimanale2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String stato; // disponibile, assegnato, in manutenzione, dismesso

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    @JsonIgnore
    private Dipendente dipendente;


    public Long getDipendenteId() {
        return (dipendente != null) ? dipendente.getId() : null;
    }


}
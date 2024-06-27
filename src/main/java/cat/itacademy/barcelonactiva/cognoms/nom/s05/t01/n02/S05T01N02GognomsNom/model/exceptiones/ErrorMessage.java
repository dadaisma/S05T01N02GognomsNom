package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02GognomsNom.model.exceptiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
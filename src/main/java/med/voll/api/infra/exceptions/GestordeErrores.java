package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestordeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarCodigo404(){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarCodigo400(MethodArgumentNotValidException ex){

        var error=ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DatosErrorValidacion::new));
    }

    public record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }


}

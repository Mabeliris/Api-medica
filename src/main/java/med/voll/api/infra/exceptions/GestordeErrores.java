package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestordeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarCodigo404(){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity gestionarErrorDeValidacion(ValidacionException e){


        return ResponseEntity.badRequest().body(e.getMessage());
    }



    public record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }


}

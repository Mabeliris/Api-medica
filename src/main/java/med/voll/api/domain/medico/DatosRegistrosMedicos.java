package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistrosMedicos(
        @NotBlank String nombre,//notblank es para strings
        @NotBlank @Email String email,
        @NotBlank String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento,//creo se refiere al rut en argentina
        @NotNull Especialidad especialidad,
        @NotNull @Valid DatosDireccion direccion
       //valid controla los campos de datos direccion que entran

) {
}

package med.voll.api.paciente;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PacienteDtoModelAssembler implements RepresentationModelAssembler<PacienteDto, @NonNull EntityModel<PacienteDto>> {

    // El metodo toModel convierte una instancia dto en un EntityModel,
    // que es una representación envolvente que proporciona una estructura estable para el JSON y puede incluir links adicionales.
    @Override
    @NonNull
    public EntityModel<PacienteDto> toModel(@NonNull PacienteDto datosListpaciente) {
        return EntityModel.of(datosListpaciente);
    }
}




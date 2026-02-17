package med.voll.api.medico;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class MedicoDtoModelAssembler implements RepresentationModelAssembler<MedicoDto, @NonNull EntityModel<MedicoDto>> {

    // El metodo toModel convierte una instancia dto en un EntityModel,
    // que es una representación envolvente que proporciona una estructura estable para el JSON y puede incluir links adicionales.
    @Override
    @NonNull
    public EntityModel<MedicoDto> toModel(@NonNull MedicoDto datosListaMedico) {
        return EntityModel.of(datosListaMedico);
    }
}

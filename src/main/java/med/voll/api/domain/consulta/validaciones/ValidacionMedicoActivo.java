package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionMedicoActivo implements ValidadorDeConsultas {


    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datos) {
        if (datos.idMedico() == null) return;

        boolean medicoEstaActivo = medicoRepository.findActivoById(datos.idMedico());

        if (!medicoEstaActivo) {
            throw new ValidationException("Consulta no puede ser reservada con médico excluido");
        }
    }
}

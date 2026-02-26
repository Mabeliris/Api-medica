package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionPacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datos) {
        boolean pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());

        if (!pacienteEstaActivo) {
            throw new ValidationException("Consulta no puede ser reservada con pacientes excluidos");
        }
    }
}

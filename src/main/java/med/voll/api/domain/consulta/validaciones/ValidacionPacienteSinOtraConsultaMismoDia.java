package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ValidacionPacienteSinOtraConsultaMismoDia implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        LocalDateTime primerHorario = datos.fecha().withHour(7);
        LocalDateTime ultimoHorario = datos.fecha().withHour(18);

        boolean pacienteTieneOtraConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(
                datos.idPaciente(), primerHorario, ultimoHorario);

        if (pacienteTieneOtraConsulta) {
            throw new ValidationException("Paciente ya tiene una consulta reservada para ese día");
        }
    }
}

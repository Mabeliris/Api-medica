package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionMedicoConOtraConsultaMismoHorario implements ValidadorDeConsultas {


    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        boolean medicoTieneOtraConsulta = consultaRepository.existByMedicoIdAndFecha(
                datos.idMedico(), datos.fecha());

        if (medicoTieneOtraConsulta) {
            throw new ValidationException("Médico ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}

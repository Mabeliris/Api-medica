package med.voll.api.domain.consulta.validaciones;


import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionMedicoConOtraConsultaMismoHorario implements ValidadorDeConsultas {


    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        boolean medicoTieneOtraConsulta = consultaRepository.existsByMedicoIdAndFecha(
                datos.idMedico(), datos.fecha());

        if (medicoTieneOtraConsulta) {
            throw new ValidacionException("Médico ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}

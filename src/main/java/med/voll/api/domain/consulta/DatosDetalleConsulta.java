package med.voll.api.domain.consulta;

import java.time.LocalTime;

public record DatosDetalleConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalTime fecha
) {
}

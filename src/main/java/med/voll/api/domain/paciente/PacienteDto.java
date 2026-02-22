package med.voll.api.domain.paciente;

public record PacienteDto (
        Long id,
        String nombre,
        String email,
        String documento
){
    public PacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}

package med.voll.api.paciente;

public record PacienteDto (
        String nombre,
        String email,
        String documento
){
    public PacienteDto(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}

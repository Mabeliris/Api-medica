package med.voll.api.medico;

public record MedicoDto(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public MedicoDto(Medico medico) {
        this(medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}


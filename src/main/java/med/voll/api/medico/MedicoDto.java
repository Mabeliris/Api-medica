package med.voll.api.medico;

public record MedicoDto(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public MedicoDto(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}


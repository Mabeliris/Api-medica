package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.Direccion;

public record DatosdetalleMedico( Long id,
                                  String nombre,
                                  String email,
                                  String documento,
                                  String telefono,
                                  Especialidad especialidad,
                                  Direccion direccion) {

    public DatosdetalleMedico(Medico medico){
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getTelefono(),
                medico.getEspecialidad(),
                medico.getDireccion()
        );
    }

}

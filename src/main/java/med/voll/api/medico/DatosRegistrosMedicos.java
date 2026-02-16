package med.voll.api.medico;

import med.voll.api.direccion.Direccion;

public record DatosRegistrosMedicos(
        String nombre,
        String documento,
        Especialidad especialidad,
        Direccion direccion


) {
}

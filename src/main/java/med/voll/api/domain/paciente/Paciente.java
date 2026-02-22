package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direccion;


    public Paciente(@Valid DatosRegistroPaciente datos) {
        this.id = null;
        this.active=true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformacion(@Valid ActualizaPacienteDto datosActualizados) {

        if(datosActualizados.nombre()!= null){
            this.nombre= datosActualizados.nombre();
        }

        if (datosActualizados.telefono()!=null){
            this.telefono= datosActualizados.telefono();
        }

        if (datosActualizados.datosDireccion()!=null){
            this.direccion.actualizarDireccion((datosActualizados.datosDireccion()));
        }

    }

    public void eliminar() {
        this.active= false;
    }
}

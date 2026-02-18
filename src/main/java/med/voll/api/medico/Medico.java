package med.voll.api.medico;



import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistrosMedicos datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformacion(@Valid ActualizarMedicoDto datosActualizados) {
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
}
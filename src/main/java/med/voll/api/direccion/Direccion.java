package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String ciudad;
    private String codigo_postal;
    private String estado;

    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        this.barrio = datosDireccion.barrio();
        this.ciudad = datosDireccion.ciudad();
        this.codigo_postal = datosDireccion.codigo_postal();
        this.estado = datosDireccion.estado();
    }

    public void actualizarDireccion(DatosDireccion datosDireccion) {

        if(datosDireccion.calle()!=null){
            this.calle = datosDireccion.calle();

        }

        if (datosDireccion.numero()!=null){
            this.numero = datosDireccion.numero();
        }

        if (datosDireccion.complemento()!=null){
            this.complemento = datosDireccion.complemento();
        }

        if (datosDireccion.barrio()!=null){
            this.barrio = datosDireccion.barrio();
        }

        if (datosDireccion.ciudad()!=null){
            this.ciudad = datosDireccion.ciudad();
        }

        if (datosDireccion.codigo_postal()!=null){
            this.codigo_postal = datosDireccion.codigo_postal();
        }

        if (datosDireccion.estado()!=null){
            this.estado = datosDireccion.estado();
        }


    }
}
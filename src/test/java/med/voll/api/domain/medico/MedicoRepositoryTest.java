package med.voll.api.domain.medico;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
//Vamos a indicarle a Spring que utilice la misma base de datos que estamos utilizando
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

     @Autowired
     private EntityManager em;

    @Test
    @DisplayName("Deberia devolver un  null cuando el medico buscado existe pero no esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFecha() {

        var lunesSiguienteALas10= LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico =1; // este es el médico que vamos a tener que colocar en la base de datos
        var paciente = 1; // paciente que va a estar usando la consulta, reservando la consulta para el próximo lunes a las 10 de la mañana
        var consulta = 1; // creo que var consulta no lo voy a necesitar, pero vamos a ver

        var medicoLibre= medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(String.valueOf(Especialidad.CARDIOLOGIA), lunesSiguienteALas10);

        assertThat(medicoLibre).isNull();
    }
}
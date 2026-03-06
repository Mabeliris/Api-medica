package med.voll.api.controller;


import med.voll.api.domain.direccion.DatosDireccion;

import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DatosRegistrosMedicos> datosRegistrosMedicosTest;

    @Autowired
    private JacksonTester<DatosdetalleMedico> datosdetalleMedicoTest;

    @MockitoBean
    private MedicoRepository repository;



    @Test
    @DisplayName("Deberia devolver metodo HTTP 400 cuando la request no tenga datos")
    @WithMockUser
    void registrar_escenario1() throws Exception {
        var response = mockMvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }


    @Test
    @DisplayName("Deberia devolver metodo HTTP 201 cuando el registro sea exitoso")
    @WithMockUser
    void registrar_escenario2() throws Exception {

        var especialidad = Especialidad.CARDIOLOGIA;

        var direccion = new DatosDireccion(
                "Av Siempre Viva",
                "742",
                "Depto 1",
                "Centro",
                "Springfield",
                "1234",
                "RM"
        );

        var datosRegistro = new DatosRegistrosMedicos(
                "Juan Perez",
                "juan.perez@voll.med",
                "945623548",
                "123456789",
                especialidad,
                direccion
        );

        when(repository.save(any()))
                .thenAnswer(invocation -> {
                    Medico medico = invocation.getArgument(0);
                    ReflectionTestUtils.setField(medico, "id", 1L);
                    return medico;
                });

        var response = mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistrosMedicosTest.write(datosRegistro).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.CREATED.value());



    }


}
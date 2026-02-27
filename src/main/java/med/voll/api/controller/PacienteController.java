package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos ){
        pacienteRepository.save(new Paciente(datos));
    }

    @Autowired // PagedResourcesAssembler se usa para convertir una Page en un PagedModel.
    private PagedResourcesAssembler<PacienteDto> pagedResourcesAssembler;
    @Autowired // Inyectamos nuestro DatosListaMedicoModelAssembler para convertir DatosListaMedico en EntityModel.
    private PacienteDtoModelAssembler pacienteDtoModelAssembler;


    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<PacienteDto>>> listar (@PageableDefault(size = 10, sort = {"nombre"})Pageable paginacion){
        Page<PacienteDto> paginas=pacienteRepository.findAllByActiveTrue(paginacion).map(PacienteDto::new);
        // Usamos el pagedResourcesAssembler y el datosListaMedicoModelAssembler para convertir la Page en un PagedModel.
        // Esto garantiza que cada objeto DatosListaMedico sea envuelto en un EntityModel, proporcionando una estructura JSON estable y permitiendo añadir links adicionales.
       var page= pagedResourcesAssembler.toModel(paginas, pacienteDtoModelAssembler);

       return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarpaciente (@RequestBody @Valid ActualizaPacienteDto datosActualizados){

        var paciente = pacienteRepository.getReferenceById(datosActualizados.id());

        paciente.actualizarInformacion(datosActualizados);

        return ResponseEntity.ok(new Datosdetallepaciente(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPaciente (@PathVariable Long id){
        var paciente= pacienteRepository.getReferenceById(id);
        paciente.eliminar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarPaciente (@PathVariable Long id){
        var paciente= pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new Datosdetallepaciente(paciente));
    }



}

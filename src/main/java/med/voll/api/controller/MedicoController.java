package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistrosMedicos datos, UriComponentsBuilder uriComponentsBuilder){

        var medico= new Medico(datos);
        repository.save(medico);

        var uri= uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosdetalleMedico(medico));
    }

    @Autowired // PagedResourcesAssembler se usa para convertir una Page en un PagedModel.
    private PagedResourcesAssembler<MedicoDto> pagedResourcesAssembler;
    @Autowired // Inyectamos nuestro DatosListaMedicoModelAssembler para convertir DatosListaMedico en EntityModel.
    private MedicoDtoModelAssembler medicoDtoModelAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<MedicoDto>>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        Page<MedicoDto> pagina = repository.findAllByActivoTrue(paginacion).map(MedicoDto::new);
        // Usamos el pagedResourcesAssembler y el datosListaMedicoModelAssembler para convertir la Page en un PagedModel.
        // Esto garantiza que cada objeto DatosListaMedico sea envuelto en un EntityModel, proporcionando una estructura JSON estable y permitiendo añadir links adicionales.
        var page = pagedResourcesAssembler.toModel(pagina, medicoDtoModelAssembler);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid ActualizarMedicoDto datosActualizados){

        var medico= repository.getReferenceById(datosActualizados.id());
        medico.actualizarInformacion(datosActualizados);

        return ResponseEntity.ok(new DatosdetalleMedico(medico));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarMedico(@PathVariable Long id){
      var medico= repository.getReferenceById(id);
      medico.eliminar();

      return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarMedico(@PathVariable Long id){
        var medico= repository.getReferenceById(id);


        return ResponseEntity.ok(new DatosdetalleMedico(medico));
    }
}

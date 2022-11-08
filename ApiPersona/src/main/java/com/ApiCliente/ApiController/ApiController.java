package com.ApiCliente.ApiController;


import com.ApiCliente.models.Usuario;
import com.ApiCliente.repositories.UsuarioRepository;
import com.ApiCliente.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ApiController {

@Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ArrayList<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }


    @PostMapping("/usuarios")
    public  Usuario guardarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Usuario> obtenerEmpleadoPorId(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el usuario: " + id));
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarios = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException( "Error al modificar usuario"));


        usuario.setNombre(usuario.getNombre());
        usuario.setApellido(usuario.getApellido());
        usuario.setCorreo((usuario.getCorreo()));
        usuario.setComuna(usuario.getComuna());
        usuario.setRegion(usuario.getRegion());
        usuario.setTelefono(usuario.getTelefono());
        usuario.setFecha(usuario.getFecha());

        Usuario usuarioActualizado = usuarioRepository.save(usuarios);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public String eliminarUsiaro(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminó el usuario  " + id;
        }else{
            return "No pudo eliminar el usuario " + id;
        }

    }

}

package com.examen.controller;

import com.examen.dto.UsuarioDto;
import com.examen.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
@Slf4j
public class UsuarioController {

    @Qualifier("usuarioImplement")
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarUsuarios(){
        response.clear();
        response.put("usuarios",usuarioService.listarUsuarios());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{documento}")
    public ResponseEntity<?> obtenerUsuarioByDocumento(@PathVariable String documento) {
        response.clear();
        response.put("usuario", usuarioService.encontrarUsuarioByDocumento(documento));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody UsuarioDto usuarioDto){
        response.clear();
        UsuarioDto user = usuarioDto;
        usuarioDto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info(usuarioDto.toString());
        usuarioService.guardar(usuarioDto);
        response.put("message", "Usuario guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{documento}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String docuemento) {
        response.clear();
        usuarioService.eliminar(docuemento);
        response.put("message","Usuario eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/editar/{documento}")
    public ResponseEntity<?> editarDocumento(@PathVariable String documento,@Valid @RequestBody UsuarioDto usuarioDto) throws IOException {
        response.clear();
        UsuarioDto usuarioDto1 = usuarioService.editarUsuario(documento,usuarioDto);
        response.put("message", "Usuario actualizado satisfactoriamente");
        response.put("usuario", usuarioDto1);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

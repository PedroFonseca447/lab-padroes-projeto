package curso.javaSpring.lab_padroes_projeto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.javaSpring.lab_padroes_projeto.model.Usuario;
import curso.javaSpring.lab_padroes_projeto.model.Veiculo;
import curso.javaSpring.lab_padroes_projeto.service.UsuarioService;
import curso.javaSpring.lab_padroes_projeto.service.VeiculoService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VeiculoService veiculoService;

   
    @GetMapping
    public ResponseEntity<Iterable<Usuario>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PostMapping
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        
        if (usuario.getVeiculos() == null) {
            usuario.setVeiculos(new ArrayList<>());
        }

        usuarioService.inserir(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuarioService.atualizar(id, usuario);
        return ResponseEntity.ok(usuario);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

   
    @GetMapping("/{usuarioId}/veiculos")
    public ResponseEntity<List<Veiculo>> buscarVeiculosPorUsuario(@PathVariable Integer usuarioId) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorUsuario(usuarioId);
        if (!veiculos.isEmpty()) {
            return ResponseEntity.ok(veiculos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package curso.javaSpring.lab_padroes_projeto.controller;

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
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> buscarTodos() {
        List<Veiculo> veiculos = veiculoService.buscarTodos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Integer id) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        return veiculo != null ? ResponseEntity.ok(veiculo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Veiculo> inserir(@RequestBody Veiculo veiculo) {
        // Buscar o usuário pelo email
        Usuario usuario = usuarioService.buscarPorEmail(veiculo.getUsuario().getEmail());

        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retornar 404 se o usuário não existir
        }

        // Associar o veículo ao usuário
        veiculo.setUsuario(usuario);
        veiculoService.inserir(veiculo); // Supondo que o método inserir do serviço salva o veículo
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Integer id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoService.buscarPorId(id);
        if (veiculoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        veiculoExistente.setCor(veiculo.getCor());
        veiculoExistente.setAno(veiculo.getAno());
        
        if (veiculo.getUsuario() != null) {
            Usuario usuario = usuarioService.buscarPorId(veiculo.getUsuario().getId());
            if (usuario != null) {
                veiculoExistente.setUsuario(usuario);
            }
        }

        veiculoService.atualizar(id, veiculoExistente);
        return ResponseEntity.ok(veiculoExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Veiculo veiculoExistente = veiculoService.buscarPorId(id);
        if (veiculoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Veiculo>> buscarVeiculosPorUsuario(@PathVariable Integer usuarioId) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorUsuario(usuarioId);
        return !veiculos.isEmpty() ? ResponseEntity.ok(veiculos) : ResponseEntity.notFound().build();
    }
}

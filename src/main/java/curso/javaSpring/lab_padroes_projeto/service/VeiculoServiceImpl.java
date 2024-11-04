package curso.javaSpring.lab_padroes_projeto.service;

import java.util.List;
import java.util.Optional; // Certifique-se de ter o repositório criado

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.javaSpring.lab_padroes_projeto.model.Veiculo;
import curso.javaSpring.lab_padroes_projeto.repository.VeiculoRepository;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Override
    public List<Veiculo> buscarTodos() {
        return (List<Veiculo>) repository.findAll();
    }

    @Override
    public Veiculo buscarPorId(Integer id) {
        Optional<Veiculo> veiculo = repository.findById(id);
        return veiculo.orElse(null); // ou lance uma exceção conforme necessário
    }

    @Override
    public void inserir(Veiculo veiculo) {
        repository.save(veiculo);
        System.out.println("Veículo salvo: " + veiculo.getModelo());
    }

    @Override
    public void atualizar(Integer id, Veiculo veiculo) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Veículo não encontrado");
        }
        veiculo.setId(id); // garantir que o ID do veículo é atualizado
        repository.save(veiculo);
    }

    @Override
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Veículo não encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Veiculo> buscarVeiculosPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}

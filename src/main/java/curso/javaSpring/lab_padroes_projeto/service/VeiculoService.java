package curso.javaSpring.lab_padroes_projeto.service;

import java.util.List;

import curso.javaSpring.lab_padroes_projeto.model.Veiculo;

public interface VeiculoService {
    
    List<Veiculo> buscarTodos(); 

    Veiculo buscarPorId(Integer id);

    void inserir(Veiculo veiculo);

    void atualizar(Integer id, Veiculo veiculo);

    void deletar(Integer id);
    
    List<Veiculo> buscarVeiculosPorUsuario(Integer usuarioId);
}
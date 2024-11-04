package curso.javaSpring.lab_padroes_projeto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.javaSpring.lab_padroes_projeto.model.Veiculo;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer>{
    List<Veiculo> findByUsuarioId(Integer usuarioId);
}

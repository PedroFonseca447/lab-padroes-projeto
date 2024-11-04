package curso.javaSpring.lab_padroes_projeto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.javaSpring.lab_padroes_projeto.model.Usuario;


@Repository
public interface  UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}

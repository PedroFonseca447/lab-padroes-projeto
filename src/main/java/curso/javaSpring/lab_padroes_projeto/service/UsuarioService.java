package curso.javaSpring.lab_padroes_projeto.service;

import curso.javaSpring.lab_padroes_projeto.model.Usuario;


//interface que garante por padrao o dominido de cliente
public interface UsuarioService {
    
    Iterable<Usuario> buscarTodos();

    Usuario buscarPorId(Integer id);

    Usuario buscarPorEmail(String email);

    void inserir(Usuario user);

    void atualizar(Integer id, Usuario user);

    void deletar(Integer id);

}

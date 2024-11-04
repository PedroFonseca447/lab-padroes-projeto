package curso.javaSpring.lab_padroes_projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.javaSpring.lab_padroes_projeto.model.Usuario;
import curso.javaSpring.lab_padroes_projeto.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Iterable<Usuario> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElse(null); // ou lance uma exceção conforme necessário
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        return usuario.orElse(null); // ou lance uma exceção conforme necessário
    }

    @Override
    public void inserir(Usuario user) {
        // Verifique se a senha não é nula ou vazia
        String pass = user.getSenha();
        if (pass == null || pass.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Não criptografando a senha antes de salvar no banco de dados
        repository.save(user);
        System.out.println("Usuário salvo: " + user.getName());
    }

    @Override
    public void atualizar(Integer id, Usuario user) {
        // Verifique se o usuário existe
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        user.setId(id); // garantir que o ID do usuário é atualizado

       
        String pass = user.getSenha();
        if (pass != null && !pass.isEmpty()) {
            throw new IllegalArgumentException("Erro na senha ");
           }

        repository.save(user);
    }

    @Override
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        repository.deleteById(id);
    }
}

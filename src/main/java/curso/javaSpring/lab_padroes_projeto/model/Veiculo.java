package curso.javaSpring.lab_padroes_projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tab_veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Integer id;

    @NotEmpty(message = "O modelo do veículo não pode ser vazio")
    @Column(length = 50, nullable = false)
    private String modelo;

    @NotEmpty(message = "A placa do veículo não pode ser vazia")
    @Column(length = 10, nullable = false)
    private String placa;

    @NotEmpty(message = "A cor do veículo não pode ser vazia")
    @Column(length = 30, nullable = false)
    private String cor;

    @NotNull(message = "O ano do veículo não pode ser nulo")
    @Column(nullable = false)
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Veiculo() {
    }

    public Veiculo(Integer ano, String cor, String modelo, String placa, Usuario usuario) {
        this.ano = ano;
        this.cor = cor;
        this.modelo = modelo;
        this.placa = placa;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

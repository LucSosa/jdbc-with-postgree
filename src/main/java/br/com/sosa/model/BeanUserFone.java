package br.com.sosa.model;

public class BeanUserFone {
    private String nome;
    private Long numero;
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BeanUserFone{" +
                "nome='" + nome + '\'' +
                ", numero=" + numero +
                ", email='" + email + '\'' +
                '}';
    }
}

package Model;

import java.io.Serializable;

public class AgendarModel implements Serializable {

    private Long id;
    private String nome;
    private int telefone;
    private String email;
    private String endereco;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int Telefone) {
        this.telefone = Telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String Email) {
        this.email = Email;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String Endereco) {
        this.endereco = Endereco;
    }

    @Override
    public String toString(){
    return nome.toString();
}


}

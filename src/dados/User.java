package dados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
    private String nickName;
    private String name;
    private String sexo;
    private String email;
    private long telefone;
    private List<Publicacao> publicacoes = new ArrayList<Publicacao>();
    private List<User> seguindo = new ArrayList<User>();
    private List<User> seguidores = new ArrayList<User>();
    private String senha;

    public User(){}

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    // 0 = masc | 1 = FEM | 3 = Indefinido
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSeguidores(User usuario) {
        this.seguidores.add(usuario);
    }

    public void setSeguindo(User usuario) {
        this.seguindo.add(usuario);
    }

    public void removeSeguindo(User usuario){
        List<User> newList = new ArrayList<User>();

        for (User x : this.seguindo){
            if(x.equals(usuario)){
                continue;
            }else{
                newList.add(x);
            }
        }
        this.seguindo = newList;
    }

    public List<User> getSeguidores() {
        return seguidores;
    }

    public List<User> getSeguindo() {
        return seguindo;
    }

    public void setPublicacoes(Publicacao publicacoes) {
        this.publicacoes.add(publicacoes);
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof User) {
            User c = (User) o;
            return this.nickName.equals(c.getNickName());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", sexo=" + sexo +
                ", email='" + email + '\'' +
                ", telefone=" + telefone +
                ", senha='" + senha + '\'' +
                '}';
    }
}

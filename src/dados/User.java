package dados;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nickName;
    private String name;
    private String sexo;
    private String email;
    private String telefone;
    private List<String> publicacoes = new ArrayList<String>();
    private List<String> seguindo = new ArrayList<String>();
    private List<String> seguidores = new ArrayList<String>();
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

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSeguidores(User usuario) {
        this.seguidores.add(usuario.getNickName());
    }

    public void setSeguindo(User usuario) {
        this.seguindo.add(usuario.getNickName());
    }

    public void removeSeguindo(User usuario){
        List<String> newList = new ArrayList<String>();

        for (String x : this.seguindo){
            if(x.equals(usuario.getNickName())){
                continue;
            }else{
                newList.add(x);
            }
        }
        this.seguindo = newList;
    }

    public List<String> getSeguidores() {
        return seguidores;
    }

    public List<String> getSeguindo() {
        return seguindo;
    }

    public void setPublicacoes(String post) {
        this.publicacoes.add(post);
    }

    public void removePublicacoes(Publicacao publicacao){
        List<String> newList = new ArrayList<String>();

        for (String x : this.publicacoes){
            if(x.equals(publicacao.getId().toString())){
                continue;
            }else{
                newList.add(x);
            }
        }
        this.publicacoes = newList;
    }

    public List<String> getPublicacoes() {
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

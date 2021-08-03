package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String nickName;
    private String name;
    private String sexo;
    private String email;
    private int telefone;
    private List<User> seguindo = new ArrayList<User>();
    private List<User> seguidores = new ArrayList<User>();
    private String senha;

    public User(){}

    public User(int id,String name,String sexo, String email, int telefone,String senha,String nick,List<User>seguindo,List<User>seguidores){
        this.id = id;
        this.name = name;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.nickName = nick;
        this.seguindo = seguindo;
        this.seguidores = seguidores;
    }

    public User(int id, String nome, String sexo, String email, int telefone, String senha, String nick) {
        this.id = id;
        this.name = name;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.nickName = nick;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getTelefone() {
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

    public List<User> getSeguidores() {
        return seguidores;
    }

    public List<User> getSeguindo() {
        return seguindo;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof User) {
            final User c = (User) o;
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

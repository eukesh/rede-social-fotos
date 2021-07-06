package negocio;
import dados.ControleUsuarios;
import dados.User;
import dados.Publicacao;

import java.util.List;

import java.util.ArrayList;

public class Sistema {
    private ControleUsuarios controleUsuarios = new ControleUsuarios();
    private User userLogin;

    public boolean cadastrarUsuarios(User user) {
        if(controleUsuarios.cadastro(user)){
            return true;
        }else{
            return false;
        }
    }

    public boolean loginUsuario(User user) {
        if (controleUsuarios.autenticaLogin(user)) {
            userLogin = user;
            return true;
        } else {
            return false;
        }
    }

    public List<User> buscarUsuario(String nome) {
        List<User> buffer = new ArrayList<User>();
        for (User x : controleUsuarios.getUsuarios()) {
            if (x.getNickName().equals(nome)) {
                buffer.add(x);
            } else if (x.getName().equals(nome)) {
                buffer.add(x);
            }
        }

        return buffer;
    }

    public void seguirUsuario(String nick) {
        for (User x : buscarUsuario(nick)) {
            userLogin.setSeguindo(x);
            x.setSeguidores(userLogin);
        }
    }

    public void deixarDeSeguirUsuario(User user){
        userLogin.removeSeguindo(user);
    }

    public void addPost(Publicacao post){
        userLogin.setPublicacoes(post);
    }

    public void removePost(Publicacao post){
        userLogin.removePublicacoes(post);
    }

    public List<Publicacao> getPostagemUser(){
        return userLogin.getPublicacoes();
    }

    public List<User> getUsuariosSeguidos(){
        return userLogin.getSeguindo();
    }

    public List<User> getSeguidores(){
        return userLogin.getSeguidores();
    }

    public void deslogarUsuario(){
        this.userLogin = null;
    }

}

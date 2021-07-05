package negocio;
import dados.ControleUsuarios;
import dados.User;

import java.util.List;

import java.util.ArrayList;

public class Sistema {
    private ControleUsuarios controleUsuarios = new ControleUsuarios();
    private User userLogin;

    public boolean cadastrarUsuarios(User usuario) {
        if(controleUsuarios.cadastro(usuario)){
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

    // public void postarFoto(Foto foto){}

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

    public List<User> getUsuariosSeguidos(){
        return userLogin.getSeguindo();
    }

    public List<User> getSeguidores(){
        return userLogin.getSeguidores();
    }

    public void deslogarUsuario(){
        this.userLogin = null;
    }

    public List<User> getAllUsuarios(){
        return controleUsuarios.getUsuarios();
    }

}

package negocio;
import dados.ControleUsuarios;
import dados.User;
import dados.Publicacao;
import persistencia.PostDAO;

import java.util.List;

import java.util.ArrayList;

public class Sistema {
    private ControleUsuarios controleUsuarios = new ControleUsuarios();
    private User userLogin;
    private PostDAO postDao = new PostDAO();

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
        userLogin.setPublicacoes(post.getId());
        post.setUser(userLogin.getNickName());
        postDao.insert(post);
    }

    public void removePost(Publicacao post){
        postDao.delete(post);
    }

    public List<Publicacao> getPostagemUser(){
        List<Publicacao> temp = new ArrayList<Publicacao>();
        for (Publicacao x : postDao.getAll()){
            if(x.getUser().equals(userLogin.getNickName())){
                temp.add(x);
            }
        }
        return temp;
    }

    public List<Publicacao> getPostagemFeed(){
        List<Publicacao> temp = new ArrayList<Publicacao>();
        for (Publicacao x : postDao.getAll()){
            for(User y : userLogin.getSeguindo()){
                if(x.getUser().equals(y.getNickName())){
                    temp.add(x);
                }
            }
        }
        return temp;
    }

    public void likePublicacao(Publicacao post){
        post.setLikes();
        postDao.att(post);
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

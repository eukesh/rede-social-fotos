package controller;
import model.ControleUsuarios;
import model.User;
import model.Publicacao;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import database.Conexao;
import database.CurtidasDAO;
import database.PostDAO;
import database.SeguidorDAO;

import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;

public class Sistema {
    private final ControleUsuarios controleUsuarios;
    private User userLogin;
    private final PostDAO postDao;
    private final SeguidorDAO seguidorDAO;
    private final CurtidasDAO curtidasDAO;

    public Sistema(String senha) throws ClassNotFoundException, SQLException, SelectException {
        Conexao.setSenha(senha);
        controleUsuarios = ControleUsuarios.getInstance();
        postDao = PostDAO.getInstace();
        seguidorDAO = SeguidorDAO.getInstace();
        curtidasDAO = CurtidasDAO.getInstace();
    }

    public boolean cadastrarUsuarios(User user) throws InsertException, SelectException {
        return controleUsuarios.cadastro(user);
    }

    public boolean loginUsuario(User user) throws SelectException {
        userLogin = controleUsuarios.autenticaLogin(user);
        return userLogin != null;
    }

    public List<User> buscarUsuario(String nome) throws SelectException {
        List<User> buffer = new ArrayList<>();
        for (User x : controleUsuarios.getUsuarios()) {
            if (x.getNickName().equals(nome)) {
                buffer.add(x);
            } else if (x.getName().equals(nome)) {
                buffer.add(x);
            }
        }

        return buffer;
    }

    public void seguirUsuario(String nick) throws SelectException, InsertException {
        for (User x : buscarUsuario(nick)) {
            userLogin.setSeguindo(x);
            x.setSeguidores(userLogin);
            seguidorDAO.insert(userLogin,x);
        }
    }

    public void deixarDeSeguirUsuario(User user) throws DeleteException, SelectException {
        seguidorDAO.delete(userLogin,user);
        userLogin = controleUsuarios.autenticaLogin(userLogin);
    }

    public void addPost(Publicacao post) throws InsertException, SelectException {

        post.setUser(userLogin);
        postDao.insert(post);
    }


    public void removePost(Publicacao post) throws DeleteException, SelectException {
        postDao.delete(post);
        userLogin = controleUsuarios.autenticaLogin(userLogin);
    }

    public List<Publicacao> getPostagemUser() throws SelectException {
        return postDao.select(userLogin);
    }

    public List<Publicacao> getPostagemFeed() throws SelectException, InsertException {
        List<Publicacao> temp = new ArrayList<>();
        for (Publicacao x : postDao.getAll()){
            for(User y : userLogin.getSeguindo()){
                if(x.getUser().equals(y)){
                    // implementacao da curtida apenas para teste
                    likePublicacao(x);
                    temp.add(x);
                }
            }
        }
        return temp;
    }

    public void likePublicacao(Publicacao x) throws InsertException, SelectException {
        if(x.setLikes(userLogin))
            curtidasDAO.insert(x,userLogin);
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

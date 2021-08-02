package dados;

import java.sql.SQLException;
import java.util.List;

import exceptions.InsertException;
import exceptions.SelectException;
import persistencia.UserDAO;

public class ControleUsuarios {
    private final UserDAO userDAO;

    public ControleUsuarios() throws SQLException, SelectException, ClassNotFoundException {
        userDAO = UserDAO.getInstace();
    }

    public boolean cadastro (User user) throws SelectException, InsertException {
        for(User x : userDAO.getAll()){
            if(x.equals(user)){
                return false;
            }else{
                userDAO.insert(user);
                userDAO.getAll();
                return true;
            }
        }
        userDAO.insert(user);
        return true;
    }

    public User autenticaLogin(User user) throws SelectException {
        for(User x : userDAO.getAll()){
            if(x.getNickName().equals(user.getNickName())){
                if(x.getSenha().equals(user.getSenha())){
                    return x;
                }
            }else if(x.getEmail().equals(user.getEmail())){
                if(x.getSenha().equals(user.getSenha())){
                    return x;
                }
            }
        }

        return null;
    }


    public List<User> getUsuarios() throws SelectException {
        return userDAO.getAll();
    }


}

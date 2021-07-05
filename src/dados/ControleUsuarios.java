package dados;

import java.util.List;
import persistencia.PessoaDAO;

public class ControleUsuarios {
    private PessoaDAO pessoaDAO = new PessoaDAO();

    public boolean cadastro (User user){
        for(User x : pessoaDAO.getAll()){
            if(x.equals(user)){
                return false;
            }else{
                pessoaDAO.insert(user);
                return true;
            }
        }
        pessoaDAO.insert(user);
        return true;
    }

    public boolean autenticaLogin(User user){
        for(User x : pessoaDAO.getAll()){
            if(x.getNickName().equals(user.getNickName())){
                if(x.getSenha().equals(user.getSenha())){
                    return true;
                }
            }else if(x.getEmail().equals(user.getEmail())){
                if(x.getSenha().equals(user.getSenha())){
                    return true;
                }
            }
        }

        return false;
    }


    public List<User> getUsuarios(){
        return pessoaDAO.getAll();
    }


}

package persistencia;

import dados.Publicacao;
import dados.User;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurtidasDAO {

    private static CurtidasDAO instance = null;
    private static UserDAO userDAO = null;

    private final PreparedStatement selectNewId;
    private final PreparedStatement select;
    private final PreparedStatement insert;
    private final PreparedStatement delete;

    public static CurtidasDAO getInstace() throws ClassNotFoundException, SQLException, SelectException {
        if(instance == null){
            instance = new CurtidasDAO();
            userDAO = UserDAO.getInstace();
        }
        return instance;
    }

    private CurtidasDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('id_curtidas')");
        insert = conexao.prepareStatement("insert into curtidas values(?,?,?)");
        select = conexao.prepareStatement("select * from curtidas where id_publicacao=?");
        delete = conexao.prepareStatement("delete from curtidas where id_publicacao=?");
    }

    private int selectNewId() throws SelectException {
        try{
            ResultSet rs = selectNewId.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar novo id na tabela contato");
        }
        return 0;
    }

    public void insert(Publicacao post, User user2) throws InsertException, SelectException{
        try{
            insert.setInt(1,selectNewId());
            insert.setInt(2,post.getId());
            insert.setInt(3,user2.getId());
            insert.executeUpdate();

        }catch (SQLException e){
            throw new InsertException("Erro ao inserir Post");
        }
    }

    public List<User> selectPost(int idPost) throws SelectException{
        List<User> temp = new ArrayList<>();
        try{
            select.setInt(1,idPost);
            ResultSet rs = select.executeQuery();
            while(rs.next()){
                int id = rs.getInt(2);
                int id2 = rs.getInt(3);
                if(idPost == id){
                    temp.add (userDAO.select(id2));
                }

            }
            return temp;
        }catch (SQLException e) {
            throw new SelectException("Erro ao buscar Curtidas");
        }
    }


    public void delete(Publicacao post) throws DeleteException {
        try {
            delete.setInt(1,post.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar contato");
        }
    }
}

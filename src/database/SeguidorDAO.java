package database;

import model.User;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguidorDAO {

    private static SeguidorDAO instance = null;
    private static UserDAO userDAO = null;
    private final PreparedStatement selectNewId;
    private final PreparedStatement select1;
    private final PreparedStatement select2;
    private final PreparedStatement insert;
    private final PreparedStatement delete;

    public static SeguidorDAO getInstace() throws ClassNotFoundException, SQLException, SelectException {
        if(instance == null){
            instance = new SeguidorDAO();
            userDAO = UserDAO.getInstace();
            userDAO = UserDAO.getInstace();
        }
        return instance;
    }

    private SeguidorDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('id_seguidores')");
        insert = conexao.prepareStatement("insert into seguidores values(?,?,?)");
        select1 = conexao.prepareStatement("select * from seguidores where id_seguidor =?");
        select2 = conexao.prepareStatement("select * from seguidores where id_seguido =?");
        delete = conexao.prepareStatement("delete from seguidores where id_seguidor=? and id_seguido=?");
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

    public void insert(User user1,User user2) throws InsertException{
        try{
            insert.setInt(1,selectNewId());
            insert.setInt(2,user1.getId());
            insert.setInt(3,user2.getId());
            insert.executeUpdate();

        }catch (SQLException | SelectException e){
            throw new InsertException("Erro ao inserir Post");
        }
    }

    public List<User> selectSeguindo(int idUser) throws SelectException{
        List<User> temp = new ArrayList<>();
        try{
            select1.setInt(1,idUser);
            ResultSet rs = select1.executeQuery();
            while(rs.next()){
                int id = rs.getInt(2);
                int id2 = rs.getInt(3);
                if(idUser == id){
                    temp.add (userDAO.select(id2));
                }

            }
            return temp;
        }catch (SQLException e) {
            throw new SelectException("Erro ao buscar seguindo");
        }
    }

    public List<User> selectSeguidor(int idUser) throws SelectException{
        List<User> temp = new ArrayList<>();
        try{
            select2.setInt(1,idUser);
            ResultSet rs = select2.executeQuery();
            while(rs.next()){
                int id = rs.getInt(2);
                int id2 = rs.getInt(3);
                if(idUser == id2){
                    temp.add (userDAO.select(id));
                }

            }
            return temp;
        }catch (SQLException e) {
            throw new SelectException("Erro ao buscar seguidores");
        }
    }

    public void delete(User user1,User user2) throws DeleteException {
        try {
            delete.setInt(1,user1.getId());
            delete.setInt(2,user2.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar contato");
        }
    }

}

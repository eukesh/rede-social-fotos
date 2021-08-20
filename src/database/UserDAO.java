package database;

import model.User;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class UserDAO {

    private static UserDAO instance = null;
    private static SeguidorDAO seguidorDAO;

    private final PreparedStatement selectNewId;
    private final PreparedStatement select;
    private final PreparedStatement insert;
    private final PreparedStatement delete;
    private final PreparedStatement update;
    private final PreparedStatement selectAll;

    public static UserDAO getInstace() throws ClassNotFoundException, SQLException, SelectException {
        if(instance == null){
            instance = new UserDAO();
            seguidorDAO = SeguidorDAO.getInstace();
            seguidorDAO = SeguidorDAO.getInstace();
        }
        return instance;
    }

    private UserDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('id_usuario')");
        insert = conexao.prepareStatement("insert into usuario values(?,?,?,?,?,?,?)");
        select = conexao.prepareStatement("select * from usuario where id = ?");
        update = conexao.prepareStatement("update usuario set nome = ?, sexo=?,email=?,telefone = ?,senha=?,nick = ? where id = ?");
        delete = conexao.prepareStatement("delete from usuario where id=?");
        selectAll = conexao.prepareStatement("select * from usuario");
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

    public void insert(User usuario) throws InsertException{
        try{
            insert.setInt(1,selectNewId());
            insert.setString(2,usuario.getName());
            insert.setString(3,usuario.getSexo());
            insert.setString(4,usuario.getEmail());
            insert.setInt(5, usuario.getTelefone());
            insert.setString(6,usuario.getSenha());
            insert.setString(7,usuario.getNickName());
            insert.executeUpdate();
            System.out.println(insert);

        }catch (SQLException | SelectException e){
            throw new InsertException("Erro ao inserir contato");
        }
    }

    public User select(int idUsuario) throws SelectException{
        try{
            select.setInt(1,idUsuario);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String sexo = rs.getString(3);
                String email = rs.getString(4);
                int telefone  = rs.getInt(5);
                String senha = rs.getString(6);
                String nick = rs.getString(7);
                return new User(id,nome,sexo,email, telefone,senha,nick);

            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar contato do ID");
        }
        return null;
    }

    public void update(User usuario ) throws UpdateException {
        try{
            insert.setInt(1,selectNewId());
            insert.setString(2,usuario.getName());
            insert.setString(3,usuario.getSexo());
            insert.setString(4,usuario.getEmail());
            insert.setInt(5, usuario.getTelefone());
            insert.setString(6,usuario.getSenha());
            insert.setString(7,usuario.getNickName());
            insert.executeUpdate();

        }catch (SQLException | SelectException e){
            throw new UpdateException("Erro ao atualizar contato");
        }
    }

    public void delete(User usuario) throws DeleteException {
        try {
            delete.setInt(1,usuario.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar contato");
        }
    }

    public List<User> getAll() throws SelectException {
        List<User> usuarios = new ArrayList<>();
        try{

            ResultSet rs = selectAll.executeQuery();


            while (rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String sexo = rs.getString(3);
                String email = rs.getString(4);
                int telefone  = rs.getInt(5);
                String senha = rs.getString(6);
                String nick = rs.getString(7);
                usuarios.add(new User(id,nome,sexo,email, telefone,senha,nick, seguidorDAO.selectSeguindo(id), seguidorDAO.selectSeguidor(id)));

            }

            return usuarios;

        }catch (SQLException e){
            throw new SelectException("Erro ao buscar contatos");
        }

    }
}

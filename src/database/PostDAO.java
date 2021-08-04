package database;

import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;
import model.Post;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostDAO {

    private static PostDAO instance = null;
    private static UserDAO userDAO = null;
    private static CurtidasDAO curtidasDAO = null;
    private final PreparedStatement selectNewId;
    private final PreparedStatement select;
    private final PreparedStatement selectUser;
    private final PreparedStatement insert;
    private final PreparedStatement delete;
    private final PreparedStatement update;
    private final PreparedStatement selectAll;

    public static PostDAO getInstace() throws ClassNotFoundException, SQLException, SelectException {
        if(instance == null){
            instance = new PostDAO();
            userDAO = UserDAO.getInstace();
            curtidasDAO = CurtidasDAO.getInstace();
        }
        return instance;
    }

    private PostDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('id_post')");
        insert = conexao.prepareStatement("insert into post values(?,?,?,?)");
        select = conexao.prepareStatement("select * from post where id = ?");
        selectUser = conexao.prepareStatement("select * from post where id_user =?");
        update = conexao.prepareStatement("update post set texto = ? where id = ?");
        delete = conexao.prepareStatement("delete from post where id=?");
        selectAll = conexao.prepareStatement("select * from post");
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

    public void insert(Post post) throws InsertException {
        try{
            insert.setInt(1,selectNewId());
            insert.setString(2,post.getTexto());
            insert.setInt(3,post.getUser().getId());
            insert.setBytes(4,post.getImagem());
            insert.executeUpdate();

        }catch (SQLException | SelectException e){
            throw new InsertException("Erro ao inserir Post");
        }
    }

    public List<Post> select(User user) throws SelectException{
        List<Post> temp = new ArrayList<>();
        try{
            selectUser.setInt(1,user.getId());
            ResultSet rs = selectUser.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String texto = rs.getString(2);
                byte[] img = rs.getBytes(4);

                temp.add(new Post(id,texto,user,curtidasDAO.selectPost(id),img));
            }
            return temp;
        }catch (SQLException e) {
            throw new SelectException("Erro ao buscar contato do ID");
        }
    }

    public void update(Post post ) throws UpdateException {
        try{
            update.setString(1,post.getTexto());
            update.setInt(2,post.getId());
            update.executeUpdate();

        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar contato");
        }
    }

    public void delete(Post post) throws DeleteException {
        try {
            curtidasDAO.delete(post);
            delete.setInt(1,post.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar contato");
        }
    }

    public List<Post> getAll() throws SelectException {
        List<Post> temp = new ArrayList<>();
        try{
            ResultSet rs = selectAll.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                String texto = rs.getString(2);
                int idUser = rs.getInt(3);
                byte[] img = rs.getBytes(4);

                temp.add(new Post(id,texto,userDAO.select(idUser),curtidasDAO.selectPost(id),img));
            }

            return temp;

        }catch (SQLException e){
            throw new SelectException("Erro ao buscar contatos");
        }

    }
}

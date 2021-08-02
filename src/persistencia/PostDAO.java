package persistencia;

import dados.Publicacao;
import dados.User;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

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
        selectNewId = conexao.prepareStatement("select nextval('id_publicacao')");
        insert = conexao.prepareStatement("insert into publicacao values(?,?,?)");
        select = conexao.prepareStatement("select * from publicacao where id = ?");
        selectUser = conexao.prepareStatement("select * from publicacao where id_user =?");
        update = conexao.prepareStatement("update publicacao set texto = ? where id = ?");
        delete = conexao.prepareStatement("delete from publicacao where id=?");
        selectAll = conexao.prepareStatement("select * from publicacao");
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

    public void insert(Publicacao post) throws InsertException, SelectException{
        try{
            insert.setInt(1,selectNewId());
            insert.setString(2,post.getTexto());
            insert.setInt(3,post.getUser().getId());
            System.out.println(insert);
            insert.executeUpdate();

        }catch (SQLException e){
            throw new InsertException("Erro ao inserir Post");
        }
    }

    public List<Publicacao> select(User user) throws SelectException{
        List<Publicacao> temp = new ArrayList<>();
        try{
            selectUser.setInt(1,user.getId());
            ResultSet rs = selectUser.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String texto = rs.getString(2);

                temp.add (new Publicacao(id,texto,user));
            }
            return temp;
        }catch (SQLException e) {
            throw new SelectException("Erro ao buscar contato do ID");
        }
    }

    public void update(Publicacao post ) throws UpdateException {
        try{
            update.setString(1,post.getTexto());
            update.setInt(2,post.getId());
            update.executeUpdate();

        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar contato");
        }
    }

    public void delete(Publicacao post) throws DeleteException {
        try {
            curtidasDAO.delete(post);
            delete.setInt(1,post.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar contato");
        }
    }

    public List<Publicacao> getAll() throws SelectException {
        List<Publicacao> temp = new ArrayList<>();
        try{
            ResultSet rs = selectAll.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                String texto = rs.getString(2);
                int idUser = rs.getInt(3);

                temp.add(new Publicacao(id,texto,userDAO.select(idUser),curtidasDAO.selectPost(id)));
            }

            return temp;

        }catch (SQLException e){
            throw new SelectException("Erro ao buscar contatos");
        }

    }
}

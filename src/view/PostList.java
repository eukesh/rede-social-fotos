package view;

import controller.Sistema;
import exceptions.InsertException;
import exceptions.SelectException;
import model.Post;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PostList extends JFrame{
    private Sistema sistema = Sistema.getInstance();
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable feedTable;
    private List<Post> postFeed;

    public PostList() throws SQLException, SelectException, ClassNotFoundException, InsertException {
        super("Login");

        postFeed = sistema.getPostagemFeed();
        createTable();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 300));
        this.setResizable(true);
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    private void createTable()  {
        Object[][] data = new Object[postFeed.size()][3];

        for (int i = 0; i < postFeed.size(); i++) {
            Post a = postFeed.get(i);
            data[i][0] = a.getTexto();
            data[i][1] = a.getUser().getNickName();
            data[i][2] = String.valueOf(a.getCurtidas());
            System.out.println(a.getCurtidas());
        }

        feedTable.setModel(new DefaultTableModel( data, new String[]{"Resultado"}));
    }



    public static void main(String[] args) throws SQLException, InsertException, SelectException, ClassNotFoundException {
        User a = new User();
        Sistema sistema = Sistema.getInstance();
        a.setNickName("juju");
        a.setSenha("123");
        sistema.loginUsuario(a);
        PostList p = new PostList();
    }
}

package view.model;

import controller.Sistema;
import exceptions.InsertException;
import exceptions.SelectException;
import model.Post;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class JTableWithImage extends javax.swing.JFrame {
    private Sistema sistema = Sistema.getInstance();
    JTable jTable1;



    public JTableWithImage() throws SQLException, SelectException, ClassNotFoundException, InsertException {
        //initComponents();
        populateJTable();
    }

    public void populateJTable() throws InsertException, SelectException {
        List<Post> postFeed = sistema.getPostagemFeed();
        String[] columnName = {"Id","Texto","Usuario","Curtidas","Image"};
        Object[][] rows = new Object[postFeed.size()][5];
        for(int i = 0; i < postFeed.size(); i++){
            rows[i][0] = postFeed.get(i).getId();
            rows[i][1] = postFeed.get(i).getTexto();
            rows[i][2] = postFeed.get(i).getUser().getNickName();
            rows[i][3] = postFeed.get(i).getCurtidas();

            if(postFeed.get(i).getImagem() != null){

                ImageIcon image = new ImageIcon(new ImageIcon(postFeed.get(i).getImagem()).getImage()
                        .getScaledInstance(150, 120, Image.SCALE_SMOOTH) );

                rows[i][4] = image;
            }
            else{
                rows[i][4] = null;
            }
        }


    }

}
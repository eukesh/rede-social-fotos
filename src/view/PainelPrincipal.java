package view;

import controller.Sistema;
import exceptions.InsertException;
import exceptions.SelectException;
import model.ImageMethods;
import model.Post;
import model.User;
import view.model.TheModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PainelPrincipal extends JFrame{
    private Sistema sistema = Sistema.getInstance();
    private String[] feed = {"Image", "Usuario", "Texto", "Curtidas"};
    private String[] seguindo = {"Nickname"};
    private String[] seguidores = {"Nickname"};
    private List<User> busca = new ArrayList<>();
    private List<Post> postFeed;
    private List<Post> postUser;
    private List<User> userSeguidos;
    private JPanel panel1 ;
    private JTabbedPane tabbedPane1;
    private JTable table1 ;
    private JTable table2;
    private JTable table3;
    private JTable table4;
    private JTextField buscaField;
    private JButton buscarButton;
    private JList list1;
    private JButton seguirButton;
    private JTable table5;
    private JTextField textoImagem;
    private JButton selecionarImagemButton;
    private JButton postarButton;
    private JButton removerSelecionadosButton;
    private JButton deslogarButton;
    private JTextPane textPane1;
    private JLabel indicadorImagemField;
    private JTextField buscarNickField;
    private JButton curtirButton;
    private JButton deixarDeSeguirButton;
    private JTextField perfilNome;
    private JButton salvarDadosButton;
    private Post newPost;

    public PainelPrincipal() throws SQLException, SelectException, ClassNotFoundException, InsertException {
        super("Login");

        postFeed = sistema.getPostagemFeed();
        createTable();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1024, 768));
        this.setResizable(true);
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = buscarNickField.getText();

                try {
                    busca = sistema.buscarUsuario(nick);
                    if(busca.size()==0){
                        JOptionPane.showMessageDialog(null,"Nenhum Usuário Encontrado");
                    }
                    buscarNickField.setText("");
                    createTable();
                } catch (SelectException | SQLException | ClassNotFoundException | InsertException ex) {
                    ex.printStackTrace();
                }


            }
        });
        seguirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(busca.size() == 0){
                    JOptionPane.showMessageDialog(null,"Ninguém para seguir");
                }else if(sistema.getUsuariosSeguidos().contains(busca.get(0))){
                    JOptionPane.showMessageDialog(null,"Você já segue esse usuário");
                    busca.clear();
                    try {
                        createTable();
                    } catch (SQLException | SelectException | ClassNotFoundException | InsertException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    try {
                        sistema.seguirUsuario(busca.get(0).getNickName());
                        JOptionPane.showMessageDialog(null,"Você agora está seguindo '"+busca.get(0).getNickName()+"'");
                        busca.clear();
                        createTable();
                    } catch (SelectException | InsertException | SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }  catch (IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null,"erro ao segui usuarios");
                    }
                }

            }
        });
        selecionarImagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int i = file.showSaveDialog(null);
                if (i==0){
                    newPost = new Post();
                    newPost.setTexto(textPane1.getText());
                    File arquivo = file.getSelectedFile();
                    System.out.println(arquivo.getPath());
                    System.out.println(arquivo.getAbsolutePath());
                    try {

                        newPost.setImagem(ImageMethods.imageToByte(arquivo.getPath()));
                        indicadorImagemField.setText(arquivo.getName());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Erro ao enviar imagem");
                    }
                }
            }
        });
        postarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sistema.addPost(newPost);
                    newPost.setTexto("");
                    textPane1.setText("");
                    indicadorImagemField.setText("Nenhuma Imagem Selecionada");
                    JOptionPane.showMessageDialog(null,"Post enviado com sucesso!");
                    createTable();
                } catch (InsertException | SelectException | SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao adicionar Post");
                }
            }
        });


        removerSelecionadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TheModel tableTemp = (TheModel) table4.getModel();
                int item = table4.getSelectedRow();
                try{
                    sistema.removePost(postUser.get(item));
                    createTable();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Nada a remover");
                }
            }
        });
        deslogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = null;
                try {
                    login = new Login();
                } catch (SQLException | SelectException | ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao abrir Janela");
                }
                try{
                    sistema.deslogarUsuario();
                    login.show();
                    dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        curtirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TheModel tableTemp = (TheModel) table1.getModel();
                int item = table1.getSelectedRow();

                try{
                    sistema.likePublicacao(postFeed.get(item));
                    createTable();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Nenhuma publicação foi curtida");
                }
            }
        });
        deixarDeSeguirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TheModel tableTemp = (TheModel) table2.getModel();
                int item = table2.getSelectedRow();
                try{
                    sistema.deixarDeSeguirUsuario(userSeguidos.get(item));
                    createTable();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Ninguém foi deixado de seguir");
                }
            }
        });

    }


    private void createTable() throws SQLException, SelectException, ClassNotFoundException, InsertException {
        TheModel feedModel = new TheModel(feed(), feed);
        table1.setModel(feedModel);
        TheModel seguindoModel = new TheModel(seguindo(), seguindo);
        table2.setModel(seguindoModel);
        TheModel seguidoresModel = new TheModel(seguidores(), seguidores);
        table3.setModel(seguidoresModel);
        TheModel postagensUserModel = new TheModel(suasPostagens(), feed);
        table4.setModel(postagensUserModel);
        TheModel buscarUsuario = new TheModel(buscar(), seguindo);
        table5.setModel(buscarUsuario);
    }
    public Object[][] buscar() throws InsertException, SelectException {
        Object[][] rows = new Object[busca.size()][1];
        for (int i = 0; i < busca.size(); i++) {
            rows[i][0] = busca.get(i).getNickName();
        }

        return rows;
    }

    public Object[][] feed() throws InsertException, SelectException {
        postFeed = sistema.getPostagemFeed();
        Object[][] rows = new Object[postFeed.size()][4];
        for (int i = 0; i < postFeed.size(); i++) {
            rows[i][2] = postFeed.get(i).getTexto();
            rows[i][1] = postFeed.get(i).getUser().getNickName();
            rows[i][3] = String.valueOf(postFeed.get(i).getCurtidas())+ " Curtida(s)";

            if (postFeed.get(i).getImagem() != null) {

                ImageIcon image = new ImageIcon(new ImageIcon(postFeed.get(i).getImagem()).getImage()
                        .getScaledInstance(150, 120, Image.SCALE_SMOOTH));

                rows[i][0] = image;
            } else {
                rows[i][0] = null;
            }
        }

        return rows;
    }

    public Object[][] suasPostagens() throws InsertException, SelectException {
        postUser = sistema.getPostagemUser();
        Object[][] rows = new Object[postUser.size()][4];
        for (int i = 0; i < postUser.size(); i++) {
            rows[i][2] = postUser.get(i).getTexto();
            rows[i][1] = postUser.get(i).getUser().getNickName();
            rows[i][3] = String.valueOf(postUser.get(i).getCurtidas())+ " Curtida(s)";

            if (postUser.get(i).getImagem() != null) {

                ImageIcon image = new ImageIcon(new ImageIcon(postUser.get(i).getImagem()).getImage()
                        .getScaledInstance(150, 120, Image.SCALE_SMOOTH));

                rows[i][0] = image;
            } else {
                rows[i][0] = null;
            }
        }

        return rows;
    }

    public Object[][] seguindo() throws InsertException, SelectException {
        userSeguidos = sistema.getUsuariosSeguidos();
        Object[][] rows = new Object[userSeguidos.size()][1];
        for (int i = 0; i < userSeguidos.size(); i++) {
            rows[i][0] = userSeguidos.get(i).getNickName();
        }

        return rows;
    }

    public Object[][] seguidores() throws InsertException, SelectException {
        List<User> postFeed = sistema.getSeguidores();
        Object[][] rows = new Object[postFeed.size()][1];
        for (int i = 0; i < postFeed.size(); i++) {
            rows[i][0] = postFeed.get(i).getNickName();
        }

        return rows;
    }



}

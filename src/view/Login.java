package view;

import controller.Sistema;
import exceptions.InsertException;
import exceptions.SelectException;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame{
    private Sistema sistema = Sistema.getInstance();
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton registrarButton;
    private JButton loginButton;
    private JPasswordField passwordField1;

    public Login() throws SQLException, SelectException, ClassNotFoundException {
        super("Login");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension( 250,200));
        this.setResizable(false);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textField1.getText();
                String senha = passwordField1.getText();

                User user = new User();
                user.setNickName(login);
                user.setSenha(senha);

                try {
                    if(sistema.loginUsuario(user)){
                        System.out.println("Login efetuado com sucesso!!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Dados inválidos");
                    }
                } catch (SelectException ex) {
                    JOptionPane.showMessageDialog(null,"Dados inválidos");
                }
                PostList feedPage = null;
                try {
                    feedPage = new PostList();
                } catch (SQLException | SelectException | ClassNotFoundException | InsertException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao abrir Janela");
                }
                feedPage.show();
                dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register registerPage = null;
                try {
                    registerPage = new Register();
                } catch (SQLException | SelectException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao abrir Janela");
                }
                registerPage.show();
                dispose();
            }
        });
    }


    public static void main(String[] args) throws SQLException, SelectException, ClassNotFoundException {
        JFrame frame = new Login();
    }
}

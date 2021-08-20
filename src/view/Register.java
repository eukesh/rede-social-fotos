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

public class Register extends JFrame {
    private Sistema sistema = Sistema.getInstance();
    private JPanel panel1;
    private JTextField nomeField;
    private JTextField userField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField senhaField;
    private JTextField reSenhaField;
    private JButton registrarButton;
    private JButton voltarButton;
    private JComboBox comboBox1;

    public Register() throws SQLException, SelectException, ClassNotFoundException {
        super("Login");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension( 500,300));
        this.setResizable(false);
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String user = userField.getText();
                String email = Register.this.emailField.getText();
                String sexo = comboBox1.getSelectedItem().toString();
                System.out.println(sexo);
                String telefone = Register.this.telefoneField.getText();
                String senha = Register.this.senhaField.getText();
                String reSenha = Register.this.reSenhaField.getText();

                if(!senha.equals(reSenha)){
                    JOptionPane.showMessageDialog(null,"As senhas não correspondem");
                }else{
                    try{
                        sistema.cadastrarUsuarios(createUser(nome,user,email,sexo,telefone,senha));
                        JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!");
                        Login loginPage = null;
                        try {
                            loginPage = new Login();
                        } catch (SQLException | SelectException | ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(null,"Erro ao abrir Janela");
                        }
                        loginPage.show();
                        dispose();

                    }catch (InsertException | SelectException insertException) {
                        JOptionPane.showMessageDialog(null,"Erro durante o cadastro!");
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Não deixe nenhum campo vazio!");
                    }
                }

            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginPage = null;
                try {
                    loginPage = new Login();
                } catch (SQLException | SelectException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao abrir Janela");
                }
                loginPage.show();
                dispose();
            }
        });

    }

    private User createUser(String nome,String user,String email,String sexo,String telefone,String senha){
        User u = new User();
        u.setName(nome);
        u.setNickName(user);
        u.setEmail(email);
        u.setSexo(sexo);
        u.setTelefone(Integer.parseInt(telefone));
        u.setSenha(senha);

        return u;
    }

}

package controller;

import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import model.ImageMethods;
import model.Post;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Tests {

    private static final Scanner s = new Scanner(System.in);


    static Sistema sistema;

    static {
        try {
            sistema = Sistema.getInstance();
        } catch (ClassNotFoundException | SQLException | SelectException e) {
            e.printStackTrace();
        }
    }

    public static void login() throws Exception {
        User user = new User();
        s.nextLine();
        System.out.print("Usuário/Email: ");
        user.setNickName(s.nextLine());
        System.out.print("Senha: ");
        user.setSenha(s.nextLine());

        if(sistema.loginUsuario(user)){
            System.out.println("Login efetuado com sucesso!!");
            menuUsuarioInterface();
        }else{
            System.out.println("Falha no Login");

        }
    }

    public static void cadastrar() throws InsertException, SelectException {
        User user1 = new User();
        s.nextLine();
        System.out.print("Nome: ");
        user1.setName(s.nextLine());
        System.out.print("Nome de usuario: ");
        user1.setNickName(s.nextLine());
        System.out.print("Email: ");
        user1.setEmail(s.nextLine());
        System.out.print("Qual seu Sexo?: ");
        user1.setSexo(s.nextLine());
        System.out.print("Qual seu Telefone?: ");
        user1.setTelefone(Integer.parseInt(s.nextLine()));
        System.out.print("Senha: ");
        user1.setSenha(s.nextLine());

        if(sistema.cadastrarUsuarios(user1)){
            System.out.println("User Cadastrado com sucesso");
        }else{
            System.out.println("Usuario já cadastado");
        }
    }

    public static int menuUsuario(){
        System.out.printf("\n1 - Buscar Usuario\n2 - Mostrar Usuarios Seguidos\n3 - Mostrar Seguidores\n4 - Postar Foto\n5 - Fotos Feed\n6 - Remover Foto\n7 - Mostrar suas postagens\n0 - deslogar%n");
        int op;
        op = s.nextInt();
        return op;
    }

    public static void postarFoto() throws Exception {
        Post p = new Post();
        s.nextLine();
        System.out.println("Digite o texto");
        p.setTexto(s.nextLine());
        p.setImagem(ImageMethods.imageToByte("C:\\Users\\User\\Documents\\Code\\POO\\rede-social-fotos\\.temp\\UML.png"));
        sistema.addPost(p);
    }

    public static void mostrarFotos() throws Exception {      
        for(Post x : sistema.getPostagemUser()) {
            System.out.println(x);
            ImageMethods.saveByteToImage(x.getImagem());
        }
    }

    public static void mostrarFeed() throws SelectException, InsertException {
        for(Post x : sistema.getPostagemFeed()){
            System.out.println(x);

        }

    }

    public static void removerPost() throws SelectException, DeleteException {
        List<Post> temp = sistema.getPostagemUser();

        for (int i = 0; i <temp.size(); i++) {
            System.out.println(i);
            System.out.println(temp.get(i));
            System.out.println();
        }
        s.nextLine();
        int op = Integer.parseInt(s.nextLine());

        sistema.removePost(temp.get(op));
    }

    public static void mostrarUsuariosSeguindo(){
        System.out.println(sistema.getUsuariosSeguidos());
    }

    public static void mostrarSeguidores(){
        System.out.println(sistema.getSeguidores());
    }

    public static void buscarUsuario() throws SelectException, InsertException, DeleteException {
        s.nextLine();
        System.out.println("Qual o nick?");
        String nick = s.nextLine();
        for(User x : sistema.buscarUsuario(nick)){
            System.out.println(x);
        }

        first:
        for(User x : sistema.buscarUsuario(nick)){
            for(User y : sistema.getUsuariosSeguidos()){
                if(x.equals(y)){
                    System.out.println("1 - Deixar de seguir\n" +
                            "0 - sair");
                    int opcao = s.nextInt();
                    switch (opcao) {
                        case 0:
                            break;
                        case 1:
                            sistema.deixarDeSeguirUsuario(y);
                            break first;
                    }
                }
            }
            System.out.println("1 - Seguir\n" +
                    "0 - sair");
            int opcao = s.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    sistema.seguirUsuario(nick);
                    break;
            }
        }

    }

    public static void menuUsuarioInterface() throws Exception {
        first:
        while(true) {
            switch (menuUsuario()) {
                case 0:
                    sistema.deslogarUsuario();
                    break first;
                case 1:
                    buscarUsuario();
                    break;
                case 2:
                    mostrarUsuariosSeguindo();
                    break;
                case 3:
                    mostrarSeguidores();

                    break;
                case 4:
                    postarFoto();
                    break;
                case 5:
                    mostrarFeed();
                    break;
                case 6:
                    removerPost();
                    break;
                case 7:
                    mostrarFotos();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        menuInicialInterface();
    }

    public static int menuInicial(){
        System.out.printf("\n1 - Login\n2 - Cadastro\n0 - Sair%n");
        int op;
        op = s.nextInt();
        return op;
    }

    public static void menuInicialInterface() throws Exception {
        while(true) {
            switch (menuInicial()) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    cadastrar();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        menuInicialInterface();
    }
}

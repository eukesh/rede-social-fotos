package negocio;

import dados.Publicacao;
import dados.User;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import java.util.Scanner;

public class Tests {
    private static Sistema sistema = new Sistema();
    private static Scanner s = new Scanner(System.in);


    public static void login(){
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

    public static void cadastrar(){
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
        System.out.print("Senha: ");
        user1.setSenha(s.nextLine());

        if(sistema.cadastrarUsuarios(user1)){
            System.out.println("User1 Cadastrado com sucesso");
        }else{
            System.out.println("Usuario já cadastado");
        }
    }

    public static int menuUsuario(){
        System.out.printf("1 - Buscar Usuario\n2 - Mostrar Usuarios Seguidos\n3 - Mostrar Seguidores\n4 - Postar Foto\n5 - Fotos Feed\n6 - Remover Foto\n7 - mostrar suas postagens\n0 - deslogar%n");
        int op;
        op = s.nextInt();
        return op;
    }

    public static void postarFoto(){
        Random random = new Random();
        Publicacao p = new Publicacao();
        p.setId(random.nextInt(1000));
        s.nextLine();
        System.out.println("Digite o texto");
        p.setTexto(s.nextLine());

        sistema.addPost(p);
    }

    public static void mostrarFotos(){
        for(Publicacao x : sistema.getPostagemUser()) {
            System.out.println(x);
        }
    }

    public static void mostrarFotosSeguindo(){
        for(User x : sistema.getUsuariosSeguidos()){
            for(Publicacao y : x.getPublicacoes()){
                System.out.println(y);
                System.out.println();
            }
        }
    }

    public static void removerPost(){
        List<Publicacao> temp = sistema.getPostagemUser();

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

    public static void buscarUsuario(){
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

    public static void menuUsuarioInterface(){
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
                    mostrarFotosSeguindo();
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
        System.out.printf("1 - Login\n2 - Cadastro\n0 - Sair%n");
        int op;
        op = s.nextInt();
        return op;
    }

    public static void menuInicialInterface(){
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
    public static void main(String[] args) {
        menuInicialInterface();
    }
}

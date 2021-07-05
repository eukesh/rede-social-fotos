package negocio;

import dados.User;

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
        System.out.println("1 - Buscar Usuario\n" +
                "2 - Mostrar Usuarios Seguidos\n" +
                "3 - Mostrar Seguidores\n" +
                "0 - deslogar");
        int op = s.nextInt();
        return op;
    }

    public static void mostrarSeguidos(){

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
        int loop = 0;
        while(loop == 0) {
            switch (menuUsuario()) {
                case 0:
                    sistema.deslogarUsuario();
                    loop++;
                    break;
                case 1:
                    buscarUsuario();
                    break;
                case 2:
                    mostrarSeguidos();
                    break;
                case 3:
                    mostrarSeguidores();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

        menuInicialInterface();
    }

    public static int menuInicial(){
        System.out.println("1 - Login\n" +
                "2 - Cadastro\n" +
                "0 - Sair");
        int op = s.nextInt();
        return op;
    }

    public static void menuInicialInterface(){
        int loop = 0;
        while(loop == 0) {
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
            }
        }
    }
    public static void main(String[] args) {
        menuInicialInterface();
    }
}

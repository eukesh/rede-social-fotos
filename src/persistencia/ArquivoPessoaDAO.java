package persistencia;

import java.util.LinkedList;
import java.util.List;
import dados.User;



public class ArquivoPessoaDAO {
    private final String caminho = "C:\\Users\\User\\Documents\\Code\\POO\\rede-social-fotos\\persistencia\\usuarios.csv";
    private static EditorTexto arquivo = new EditorTexto();

    private String toCSV(User pessoa) {
        String p = "";
        p += pessoa.getName() + ",";
        p += pessoa.getEmail() + ",";
        p += pessoa.getNickName() + ",";
        p += pessoa.getSenha() + ",";
        p += pessoa.getSexo() + ",";
        p += pessoa.getTelefone() + ",\n";

        return p;
    }

    private User fromCSV(String linhaCSV) {
        String[] atributos = linhaCSV.split(",");
        User pessoa = new User();
        pessoa.setName(atributos[0]);
        pessoa.setEmail(atributos[1]);
        pessoa.setNickName(atributos[2]);
        pessoa.setSenha(atributos[3]);
        pessoa.setSexo(atributos[4]);
        pessoa.setTelefone(atributos[5]);

        return pessoa;
    }

    private List<String> listaPessoaToString(List<User> pessoas) {
        List<String> arquivo = new LinkedList<String>();

        for (User pessoa : pessoas) {
            arquivo.add(toCSV(pessoa));
        }

        return arquivo;
    }

    private List<User> stringToListaPessoa(List<String> arquivo) {
        List<User> pessoas = new LinkedList<User>();

        for (String linha : arquivo) {
            pessoas.add(fromCSV(linha));
        }

        return pessoas;
    }

    public List<User> lePessoasArquivo() {
        return stringToListaPessoa(arquivo.leTexto(caminho));
    }

    public void salvaPessoasArquivo(List<User> pessoas) {
        arquivo.gravaTexto(caminho, listaPessoaToString(pessoas));
    }

    public void salvaPessoaArquivo(User pessoa) {
        arquivo.gravaTexto(caminho, toCSV(pessoa));
    }

}

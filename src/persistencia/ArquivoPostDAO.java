package persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dados.Publicacao;
import dados.User;



public class ArquivoPostDAO {
    private final String caminho = "C:\\Users\\User\\Documents\\Code\\POO\\rede-social-fotos\\database\\postagens.csv";
    private static EditorTexto arquivo = new EditorTexto();

    private String toCSV(Publicacao post) {
        String p = "";
        p += post.getUser()+",";
        p += post.getId() + ",";
        p += post.getTexto() + ",";
        p += post.getLikes() + ",\n";

        return p;
    }

    private Publicacao fromCSV(String linhaCSV) {
        String[] atributos = linhaCSV.split(",");
        Publicacao post = new Publicacao();
        post.setUser(atributos[0]);
        post.setId(atributos[1]);
        post.setTexto(atributos[2]);
        post.setLikes(atributos[3]);

        return post;
    }

    private List<String> listaPostToString(List<Publicacao> post) {
        List<String> arquivo = new LinkedList<String>();

        for (Publicacao x : post) {
            arquivo.add(toCSV(x));
        }

        return arquivo;
    }

    private List<Publicacao> StringToListaPost(List<String> arquivo) {
        List<Publicacao> post = new LinkedList<Publicacao>();

        for (String linha : arquivo) {
            post.add(fromCSV(linha));
        }

        return post;
    }

    public List<Publicacao> lePostArquivo() {
        return StringToListaPost(arquivo.leTexto(caminho));
    }

    public void attArquivo(Publicacao post){
        List<Publicacao> temp = new ArrayList<Publicacao>();


        for(Publicacao x : lePostArquivo()){
            if(x.getId().equals(post.getId())){
                continue;
            }
            temp.add(x);

        }
        temp.add(post);
        salvaPostsArquivo(temp);
    }

    public void salvaPostsArquivo(List<Publicacao> post) {
        arquivo.gravaTexto(caminho, listaPostToString(post));
    }

    public void salvaPostArquivo(Publicacao post) {
        arquivo.gravaTexto(caminho, toCSV(post));
    }

}

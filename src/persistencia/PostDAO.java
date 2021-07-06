package persistencia;

import java.util.ArrayList;
import java.util.List;

import dados.Publicacao;
import dados.User;

public class PostDAO {
    private ArquivoPostDAO arquivoPostDAO = new ArquivoPostDAO();

    public void insert(Publicacao post) {
        arquivoPostDAO.salvaPostArquivo(post);
    }

    public void att(Publicacao post){
        arquivoPostDAO.attArquivo(post);
    }

    public void delete(Publicacao post) {
        List<Publicacao> temp = new ArrayList<Publicacao>();
        for(Publicacao x : arquivoPostDAO.lePostArquivo()){
            if(x.getId().equals(post.getId())){
                continue;
            }else{
                temp.add(x);
            }
        }
        arquivoPostDAO.salvaPostsArquivo(temp);
    }

    public List<Publicacao> getAll() {
        return arquivoPostDAO.lePostArquivo();
    }
}

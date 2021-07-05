package persistencia;

import java.util.List;
import dados.User;

public class PessoaDAO {
    private ArquivoPessoaDAO arquivoPessoaDAO = new ArquivoPessoaDAO();

    public void insert(User pessoa) {
        arquivoPessoaDAO.salvaPessoaArquivo(pessoa);
    }

    public void delete(User pessoa) {
        List<User> pessoas = arquivoPessoaDAO.lePessoasArquivo();
        pessoas.remove(pessoa);
        arquivoPessoaDAO.salvaPessoaArquivo(pessoa);
    }

    public List<User> getAll() {
        return arquivoPessoaDAO.lePessoasArquivo();
    }
}

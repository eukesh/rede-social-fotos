package exceptions;

public class ErroNaGravacaoException extends ErroArquivoException{

    public ErroNaGravacaoException(){
        super("Erro durante a gravacao do arquivo");
    }
}

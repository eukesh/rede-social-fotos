package dados;

public class Publicacao {
    private int id;
    // private Foto imagem;
    private String texto;
    private Like likes;

    public Publicacao(){

    }

    public void setLikes() {
        likes.setQuantLike();
    }

    public int getLikes() {
        return likes.getQuantLike();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "Publicacao{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", likes=" + likes +
                '}';
    }
}

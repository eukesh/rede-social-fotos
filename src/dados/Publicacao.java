package dados;

public class Publicacao {
    private int id;
    // private Foto imagem;
    private String texto;
    private Like likes = new Like();

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
    public boolean equals(Object o) {

        if (o instanceof Publicacao) {
            Publicacao c = (Publicacao) o;
            if(this.id == c.getId()){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
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

package dados;

public class Publicacao {
    private String id;
    private String user;
    // private Foto imagem;
    private String texto;
    private Like likes = new Like();

    public Publicacao(){

    }

    public void setLikes() {
        likes.setQuantLike();
    }

    public void setLikes(String like) {
        likes.setQuantLike(Integer.parseInt(like));
    }

    public int getLikes() {
        return likes.getQuantLike();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
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

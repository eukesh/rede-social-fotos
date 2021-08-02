package dados;

import java.util.ArrayList;
import java.util.List;

public class Publicacao {
    private int id;
    private User user;
    private byte[] imagem;
    private String texto;
    private List<User> likes = new ArrayList<User>();

    public Publicacao(){}

    public Publicacao(int id,String texto,User user){
        this.id = id;
        this.texto = texto;
        this.user = user;
    }

    public Publicacao(int id,String texto,User user,List<User>likes){
        this.id = id;
        this.texto = texto;
        this.user = user;
        this.likes = likes;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public boolean setLikes(User user) {
        if(!likes.contains(user)) {
            likes.add(user);
            return true;
        }
        return false;
    }

    public int getLikes() {
        return likes.size();
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
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
                ",curtidas="+getLikes() +
                ",QuemCurtiu="+likes+
                '}';
    }
}

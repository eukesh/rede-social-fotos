package dados;

public class Like {
    private int quantLike = 0;

    public void setQuantLike() {
        this.quantLike++;
    }

    public void setQuantLike(int k) {
        this.quantLike = k;
    }

    public int getQuantLike() {
        return quantLike;
    }

    @Override
    public String toString() {
        return "Like{" +
                "quantLike=" + quantLike +
                '}';
    }
}

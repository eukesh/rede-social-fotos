package model;

import java.io.*;

public class ImageMethods {

    public static byte[] imageToByte(String image) throws IOException {
        InputStream is = null;
        byte[] buffer = null;
        is = new FileInputStream(image);
        buffer = new byte[(int) is.available()];
        is.read(buffer);
        is.close();
        return buffer;
    }

    public static void saveByteToImage(byte[] bytes) throws Exception {
        byte[] imgBytes = bytes;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\User\\Documents\\Code\\POO\\rede-social-fotos\\.temp\\new-UML.png");
            fos.write(imgBytes);
            FileDescriptor fd = fos.getFD();
            fos.flush();
            fd.sync();
            fos.close();
        } catch (Exception e) {
            throw new Exception(    "Erro ao converter os bytes recebidos para imagem");
        }
    }
}

package files;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWrite {

    public static void main(String[] args) {

        String text = "Hello, world!";
        try(FileOutputStream fos = new FileOutputStream("C:\\Users\\kirill.nemykin\\Documents\\Test.txt")) {

            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        }
}

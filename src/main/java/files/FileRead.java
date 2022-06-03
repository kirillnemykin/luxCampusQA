package files;

import java.io.FileInputStream;
import java.io.IOException;

public class FileRead {

    public static void main(String[] args) {
        try(FileInputStream fin = new FileInputStream("C:\\Users\\kirill.nemykin\\Documents\\Test.txt")) {
            System.out.println("File size: " + fin.available() + " bytes");

            int i;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

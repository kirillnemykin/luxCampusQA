package files;

import java.io.FileInputStream;
import java.io.IOException;

public class FileEx {
    public static void main(String[] args) throws IOException {

        FileInputStream fin = new FileInputStream("C:\\Users\\kirill.nemykin\\Documents\\Test.txt");

        System.out.println("Channel: " + fin.getChannel());

        System.out.println("File description: " + fin.getFD());

        System.out.println("Available bytes: " + fin.available());

        long skip = fin.skip(10);
        System.out.println("skip " + skip);
        System.out.println("Number of remaining bytes: " + fin.available());
        System.out.println("FileContents: ");

        int ch;
        while ((ch = fin.read()) != -1) {
            System.out.print( (char) ch);
        }

        fin.close();

    }
}

package format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

public class JsonToJava {

    public static void main(String[] args) throws IOException {
        Person person = new Person("name", "location", 34, Arrays.asList("dir1", "dir2", "dir3"));
        try (Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/server1.json"), StandardCharsets.UTF_8)){

        Gson gson = new GsonBuilder().create();

        Person p = gson.fromJson(reader, Person.class);
        //System.out.println(p);
        //System.out.println(p.getAge());

        String s = gson.toJson(person);
        System.out.println(s);

    }

}}
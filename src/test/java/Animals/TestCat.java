package Animals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCat {

    @Test
    public void checkCatSound(){
        //GIVEN
        Cat cat = new Cat("testCat", 3, "pet");
        String expectedSound = "me meow";
        //WHEN
        String actualSound = cat.makeSound();
        //THEN
        Assertions.assertEquals(expectedSound, actualSound,
                String.format("Expected '%s', but was '%s'", expectedSound, actualSound));
    }

    @Test
    public void checkCatClass() throws NoSuchFieldException {
        //GIVEN
        Cat cat = new Cat("testCat", 3, "pet");
        List<String> expectedFieldNames = Collections.EMPTY_LIST;
        //WHEN
        Field[] fields = cat.getClass().getFields();

        List<String> actualFieldNames = new ArrayList<>();
        for (Field field : fields) {
            actualFieldNames.add(field.getName());
        }

        //THEN
        Assertions.assertTrue(actualFieldNames.isEmpty(), "actual: " + actualFieldNames);

    }


    }


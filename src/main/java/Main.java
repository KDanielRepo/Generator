import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    @ExcelAdn("testString")
    static String first = "pierwsze";
    @ExcelAdn("List1")
    static List<Integer> integers = new ArrayList<>();
    @ExcelAdn("List2")
    static List<String> strings = new ArrayList<>();
    @ExcelAdn("List3")
    static List<Double> doubles = new ArrayList<>();
    @ExcelAdn("testInt")
    static int sec = 4;

    public static void main(String[] args)throws IOException, InvalidFormatException,InstantiationException,IllegalAccessException,NoSuchFieldException {
        setList();
        ExcelGenerator excelGenerator = new ExcelGenerator();
        excelGenerator.create();
    }
    private static void setList(){
        for (int i = 0; i < 10; i++) {
            integers.add(i * 213);
            strings.add(Integer.toString(i));
            doubles.add(Double.valueOf(i));
        }
    }
}

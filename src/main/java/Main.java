import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException, InvalidFormatException {
        ExcelGenerator excelGenerator = new ExcelGenerator();
        excelGenerator.create();
    }
}

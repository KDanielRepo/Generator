import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args)throws IOException, InvalidFormatException {
        //ExcelGenerator excelGenerator = new ExcelGenerator();
       // excelGenerator.create();
        ItemSet itemSet = new ItemSet();
        for(int i = 0; i<10; i++) {
            Item item = new Item();
            item.setName(Integer.toString(i));
            item.setPrice(BigDecimal.valueOf(i * 50));
            item.setAmount((double) (100 * i));
            itemSet.getItems().add(item);
            System.out.println(item);
        }
    }
}

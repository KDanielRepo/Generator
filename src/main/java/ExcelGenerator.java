
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
    public static final String xlsxPath = "./Test.xlsx";

    @ExcelAdn(value = "test")
    private String test = "String";
    @ExcelAdn("test2")
    private String test2 = "Big Decimal";
    @ExcelAdn("test3")
    private String test3 = "Double";

    public void create()throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("test");
        ItemSet itemSet = new ItemSet();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setName(Integer.toString(i));
            item.setPrice(BigDecimal.valueOf(i * 50));
            item.setAmount((double) (100 * i));
            itemSet.getItems().add(item);
        }
        int colNum = 0;
        int rowNum = 1;
        Row firstRow = sheet.createRow(rowNum++);
        for (Field f : ExcelGenerator.class.getDeclaredFields()) {
            ExcelAdn excelAdn = f.getAnnotation(ExcelAdn.class);
            if(excelAdn != null){
                Cell cell = firstRow.createCell(colNum++);
                cell.setCellValue(excelAdn.value());
                System.out.println(excelAdn.value());
            }
        }
        for (Item objects : itemSet.getItems()) {
            Row row = sheet.createRow(rowNum++);
            colNum = 0;
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(objects.getName());
            Cell cell2 = row.createCell(colNum++);
            cell2.setCellValue(objects.getPrice().doubleValue());
            Cell cell3 = row.createCell(colNum++);
            cell3.setCellValue(objects.getAmount());

        }




        try {
            FileOutputStream outputStream = new FileOutputStream(xlsxPath);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
            Sheet sheet1 = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            System.out.println("\n\nIteruje rzedy i kolumny");
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.println(cellValue + "\t");
                }
            }

*/
/*int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(excel.getTest());*/

        /*int rowNum = 0;
        Row firstRow = sheet.createRow(rowNum++);
        for (Object[] objects : testing) {
            Row row = sheet.createRow(rowNum++);
            Cell firstCell = firstRow.createCell(0);
            firstCell.setCellValue(excel.getTest());
            int colNum = 0;
            for (Object field : objects) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }*/
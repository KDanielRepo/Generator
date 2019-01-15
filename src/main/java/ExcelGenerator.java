
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
    public static final String xlsxPath = "./Test.xlsx";
    @ExcelAdn("Name")
    private String test = "String";
    @ExcelAdn("Value")
    private String test2 = "Big Decimal";
    @ExcelAdn("Amount")
    private String test3 = "Double";
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("test");

    public void create()throws IOException, InvalidFormatException {

        ItemSet itemSet = new ItemSet();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i*213);
            Item item = new Item();
            item.setName(Integer.toString(i));
            item.setPrice(BigDecimal.valueOf(i * 50));
            item.setAmount((double) (100 * i));
            itemSet.getItems().add(item);
        }
        int colNum = 0;
        int rowNum = 0;
        Row firstRow = sheet.createRow(rowNum++);
        for (Field f : ExcelGenerator.class.getDeclaredFields()) {
            ExcelAdn excelAdn = f.getAnnotation(ExcelAdn.class);
            if(excelAdn != null){
                Cell cell = firstRow.createCell(colNum++);
                cell.setCellValue(excelAdn.value());
                System.out.println(excelAdn.value());
            }
        }
        /*for(int i =0; i<integers.size();i++){
            Row row = sheet.createRow(rowNum++);
            colNum = 0;
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(integers.get(i));
        }*/
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
/* public void createCollection(Collection<?> collection)throws IOException, InvalidFormatException{
        int colNum = 0;
        int rowNum = 0;
        for(Object o:collection){
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(o.toString());
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
    }*/
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
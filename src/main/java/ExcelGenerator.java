
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
    public static final String xlsxPath = "./Test.xlsx";

    public void create()throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("test");
        ExcelImpl excel = new ExcelImpl();
        excel.setList();
        ItemSet itemSet = new ItemSet();
        int size = itemSet.getItems().size();
        Object[][] testing = {
                {excel.getList().get(0),excel.getIntegers().get(0)},
                {excel.getList().get(1),excel.getIntegers().get(1)},
                {excel.getList().get(2),excel.getIntegers().get(2)},
                {excel.getList().get(3),excel.getIntegers().get(3)}
        };
        Item[][][] test2 = new Item[][][];
        for(int i =0; i<size; i++){
            Item[][] test = {
                    {itemSet.getItems().get(i)},
            };

                }
        /*int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(excel.getTest());*/
        int rowNum = 0;
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
        }
        for (Field f : ExcelImpl.class.getDeclaredFields()) {
            ExcelAdn excelAdn = f.getAnnotation(ExcelAdn.class);
            Optional<ExcelAdn> excelGenerator = Optional.ofNullable(excelAdn);
           // System.out.println(excelAdn.value());
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

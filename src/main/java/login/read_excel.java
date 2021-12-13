package login;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class read_excel
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream excel=new FileInputStream("C:\\Users\\prasanna.prabakaran\\Downloads\\login.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(excel);
        int noOfSheets=workbook.getNumberOfSheets();// count the no of sheets
       // System.out.println(noOfSheets);
        for (int sheetIndex=0;sheetIndex<noOfSheets;sheetIndex++){
            if (workbook.getSheetName(sheetIndex).equalsIgnoreCase("login"))
            {
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                Iterator<Row> rows= sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cell= firstRow.cellIterator();
                int temp=0;
                int column = 0;
                while (cell.hasNext()){
                  Cell value=  cell.next();
                  if(value.getStringCellValue().equalsIgnoreCase("username")){
                      column=temp;

                  }
                  temp++;
                }
                System.out.println(column);
               // System.out.println(firstRow);
            }

        }

    }
}

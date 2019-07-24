package  com.zyth.web.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelInputUtil {

	 public static ArrayList excelToList(InputStream  in){
	        //创建list集合存放对象
	        ArrayList list = new ArrayList();

	        Workbook workbook=null;
	        try {
	            workbook = WorkbookFactory.create(in);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        //读取默认第一个工作表sheet
	        Sheet sheet =  workbook.getSheetAt(0);
	        //获取sheet中最后一行行号
	        int lastRowNum = sheet.getLastRowNum();
	        //循环所有行
	        for (int i = 1; i <= lastRowNum; i++) {
	            //获取当前行中的内容
	            Row row = sheet.getRow(i);
	            short cell = row.getLastCellNum();
	            if(row !=null && cell!=0){
	            	HashMap<String, String> dataMap = new HashMap<String, String>();
	                for(int j=0;j<cell;j++){
	                    Cell name=row.getCell(j);
	                	dataMap.put(String.valueOf(j), getValue(name));
	                }
	                list.add(dataMap);
	            }
	        }
	        return list;
	    }


    //取单元格中的值
    private static String getValue(Cell cell){
        String result="";
        if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
            //返回布尔类型的值
            result = cell.getBooleanCellValue() +"";
        }else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
            //返回数值类型的值
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                result = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            }else{
                result = cell.getNumericCellValue()+"";
            }
            return String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType() == cell.CELL_TYPE_FORMULA){
            result = cell.getCellFormula();
        }else if(cell.getCellType() == cell.CELL_TYPE_STRING){
            result = cell.getStringCellValue();
        }else{
            //返回字符口串类型的值
            result = cell.getStringCellValue();
        }
        return result;
    }

}
package  com.zyth.web.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil<T> {
    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param fields
     *            表格属性列名对应的bean中的字段数组，和headers一一对应
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    public void exportExcel(String title, String[] headers, String [] fields,
                            Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(CellStyle.BORDER_THIN);
        style2.setBorderLeft(CellStyle.BORDER_THIN);
        style2.setBorderRight(CellStyle.BORDER_THIN);
        style2.setBorderTop(CellStyle.BORDER_THIN);
        style2.setAlignment(CellStyle.ALIGN_LEFT);
        style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);


        HSSFCellStyle style3 = workbook.createCellStyle();
        style3.setFillForegroundColor(HSSFColor.WHITE.index);
        style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style3.setAlignment(CellStyle.ALIGN_CENTER);
        style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        font3.setFontHeightInPoints((short) 14);
        font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style3.setFont(font3);

        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");

        //标题
        HSSFRow row = sheet.createRow(0);
        // 单元格合并
        // 四个参数分别是：起始行，结束行，起始列，结束列
        Integer len = headers.length;
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, len-1));
        HSSFCell celltitle =  row.createCell(0);
        celltitle.setCellStyle(style3);
        celltitle.setCellValue(title);


        //产生表格标题行
        HSSFRow row2 = sheet.createRow(2);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row2.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }


        //遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 2;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            //Field[] fields = t.getClass().getDeclaredFields();、
            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                //Field field = fields[i];
                //String fieldName = field.getName();
                String fieldName = fields[i];
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    //判断值的类型后进行强制类型转换
                    String textValue = null;
                    if(value!=null){
                        //                if (value instanceof Integer) {
                        //                   int intValue = (Integer) value;
                        //                   cell.setCellValue(intValue);
                        //                } else if (value instanceof Float) {
                        //                   float fValue = (Float) value;
                        //                   textValue = new HSSFRichTextString(
                        //                         String.valueOf(fValue));
                        //                   cell.setCellValue(textValue);
                        //                } else if (value instanceof Double) {
                        //                   double dValue = (Double) value;
                        //                   textValue = new HSSFRichTextString(
                        //                         String.valueOf(dValue));
                        //                   cell.setCellValue(textValue);
                        //                } else if (value instanceof Long) {
                        //                   long longValue = (Long) value;
                        //                   cell.setCellValue(longValue);
                        //                }
                        //                  if (value instanceof Boolean) {
                        //                     boolean bValue = (Boolean) value;
                        //                     textValue = "男";
                        //                     if (!bValue) {
                        //                        textValue ="女";
                        //                     }
                        //                  } else
                        if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        }  else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) value;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                    1023, 255, (short) 6, index, (short) 6, index);
                            anchor.setAnchorType(2);
                            patriarch.createPicture(anchor, workbook.addPicture(
                                    bsValue, Workbook.PICTURE_TYPE_JPEG));
                        } else{
                            //其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                        //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if(textValue!=null){
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if(matcher.matches()){
                                //是数字当作double处理
                                DecimalFormat df = new DecimalFormat("0");
//                             cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//
//                             String whatYourWant = df.format(Double.parseDouble(textValue));
//                             cell.setCellValue(whatYourWant);

                                cell.setCellValue(Double.parseDouble(textValue));
                            }else{
                                HSSFRichTextString richString = new HSSFRichTextString(textValue);
//                            HSSFFont font3 = workbook.createFont();
//                            font3.setColor(HSSFColor.BLUE.index);
//                            richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }
                    }
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    //清理资源
                }
            }

        }
        try {
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
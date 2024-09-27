package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelUtility {    
	
//Whenever you want to access any methods from this utility file we need to Create Object of This utility class(ExcelUtility) through that object we can access.
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	/*We have to create One Constructor also,here we directly passing the path of excel,whenever we Create Object for this class,we immediately pass 
	 
	location of excel path and this constructor will take care of it,
	 
	*we no need to pass "Path" in every method,
	
	*why we are not passing path?
	because the Excel sheet,path Already capturing through Constructor ,by using constructor we are getting path.assing class path to local path,and 
	it will act as common variable for every method. 
	*/
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);

		workbook=new XSSFWorkbook(fi);

		sheet=workbook.getSheet(sheetName);

		int rowcount=sheet.getLastRowNum();

		workbook.close();

		fi.close();

		return rowcount;
		}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		
		workbook=new XSSFWorkbook(fi);
		
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		
		int cellcount=row.getLastCellNum();
		
		return cellcount;
		
	}

	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		
		workbook=new XSSFWorkbook(fi);
		
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter(); 
		
		String data;

		try{
		data=formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardles
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;

	}
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);

		if(!xlfile.exists()) // If file not exists then create new file

		{

		workbook=new XSSFWorkbook();

		fo=new FileOutputStream(path);

		workbook.write(fo);

		}

		fi=new FileInputStream(path);

		workbook=new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheetName)== 1) // If sheet not exists then create new Sheet workbook.createSheet(sheet.Name);

		sheet= workbook.getSheet(sheetName);

		if(sheet.getRow(rownum)==null) // If row not exists then create new Row

		sheet.createRow(rownum);

		row=sheet.getRow(rownum);

		cell=row.createCell(colnum);

		cell.setCellValue(data);

		fo=new FileOutputStream(path);

		workbook.write(fo);
		workbook.close();

		fi.close(); 
		fo.close();
	}
	
	public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	

	public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);	
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}

	
	
				
	
	
}

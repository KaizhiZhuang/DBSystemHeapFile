
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import dbsystem.heapfile.BytesUtils;
import dbsystem.heapfile.Page;
import dbsystem.heapfile.RecordFix;

/**
 * this class is used to load the csv file to the binary file
 * @author Kaizhi.Zhuang
 * @studentId s3535252
 * Mar 25, 2018
 * dbload.java
 * Describe:
 */
public class dbload
{
	private static String heapFileName = "";
	private static int   page_size    = 0;
	private static File  loadFile;
	private int   		 rows_per_page=0;
	private static long record_count = 0L;
	
	public static void main(String[] args)
	{
		if (args.length != 3)
		{
			return;
		}
		if (!args[0].equals("-p"))
		{
			System.out.println("Argument Error, input -p");
			return;
		}
		if (args[1] == null && args[1].equals(""))
		{
			System.out.println("Give the size of page.");
			return;
		}else
		{
			try
			{
				page_size = Integer.parseInt(args[1]);
			}catch(Exception e)
			{
				System.out.println("The size of page need int type.");
				e.printStackTrace();
				return;
				
			}
		}
		
		if (args[2] == null && args[2].equals(""))
		{
			System.out.println("Give the file you need to load.");
			return;
		}else
		{
			try
			{
				loadFile = new File(args[2]);
			} catch (Exception e)
			{
				System.out.println("Can not find the file.");
				e.printStackTrace();
				return;
			}
			
		}
		heapFileName = "data/heap." + page_size;
		
		System.out.println("The size of a single page is "+ page_size + " bytes");
		System.out.println("Processing...");
		Date ds = new Date(); 
		new dbload().processData();
		Date de = new Date(); 
		long difference = (de.getTime() - ds.getTime());
		System.out.println("The number of records loaded is " + record_count);
		System.out.println("The number of milliseconds to create the heap file is " + difference);
	}

	/**
	 * this method is to execute the process
	 */
	public void processData()
	{
		File file = new File(heapFileName);
		if (file.exists())
		{
			file.delete();
		}
		
		//get the fixed length of a record
		int recordLength = BytesUtils.getRecordFixLength();
		rows_per_page = page_size/recordLength;
		BufferedReader bfReader = null;
		int row = 0;
		try
		{
			//create a new page with page size input as a parameter
			Page p = new Page(page_size);
			bfReader = new BufferedReader(new FileReader(loadFile));
			
			//ignore the first line
			bfReader.readLine();
			
			String line = "";
			while((line=bfReader.readLine())!=null)
	        {  
				if (row == rows_per_page)
				{
					//write byte array to binary file
					//becasue the page has been full
					p.witeToFile(heapFileName);
					
					//initialize variable again
					row = 0;
					p = new Page(page_size);
				}
				String item[] = new String[9]; 
				item = line.split("\t",-1);

				//convert an item array to a Record Object
				RecordFix record = p.covert_to_recordFix(item);
				
				//get the byte array of the record
				byte[] argData = BytesUtils.getRecordFixBytes(record);
				
				//add this record byte array to the page class
				p.addBytesToArray(argData);
				
				row = row + 1;
				record_count = record_count + 1;
	        }
			//write the last byte array to binary file
			p.witeToFile(heapFileName);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				bfReader.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
	
	
		
}

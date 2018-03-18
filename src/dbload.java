import heapfile_package.HeapPage;
import heapfile_package.Record;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class dbload
{
	private static int page_size = 0;
	private static File loadFile;
	//private static ArrayList<HeapPage> arrPage = new ArrayList<HeapPage>();
	private static int page_numbers = 0;
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
		
		System.out.println("The number of a single page size is "+ page_size + " Bytes");
		System.out.println("Processing...");
		Date ds = new Date(); 
		new dbload().processData();
		Date de = new Date(); 
		long difference = (de.getTime() - ds.getTime());
		System.out.println("The number of records loaded is " + record_count);
		System.out.println("The number of pages used is " + page_numbers);		
		System.out.println("The number of milliseconds to create the heap file is " + difference);
	}
	public void processData()
	{
		BufferedReader bfReader = null;
		try
		{
			bfReader = new BufferedReader(new FileReader(loadFile));
			//ignore the first line
			bfReader.readLine();
			String line = null;
			HeapPage page = new HeapPage(0, page_size);
			int i = 0;
			while((line=bfReader.readLine())!=null)
	        {  
				String item[] = new String[9]; 
				item = line.split("\t",-1);
				Record record = page.covert_to_record(item);
				//record_count = record_count + 1;
				if (record == null)
				{
					continue;
				}
				int remainSpace = page.getRemainSpace();
				int recordSpace = record.getDynamicRecordLength();
				if (remainSpace  < recordSpace)
				{
					page.writePageFile(new File("data/heap."+page_size + "_" + i +".dat"));
					//arrPage.add(page);
					record_count = record_count + page.getRecords().size();
					page_numbers = page_numbers + 1;
					try
					{
						Thread.sleep(500L);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					i = i + 1;
					page = new HeapPage(i, page_size); 
				}
				page.insertRecord(record);
	        } 
			page.writePageFile(new File("data/heap."+page_size + "_" + i +".dat"));
			record_count = record_count + page.getRecords().size();
			page_numbers = page_numbers + 1;
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
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

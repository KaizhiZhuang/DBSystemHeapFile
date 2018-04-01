
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

import dbsystem.heapfile.BytesUtils;
import dbsystem.heapfile.RecordFix;

/**
 * this class is used to retreive the business name form binary file
 * by using reading page by page
 * @author Kaizhi.Zhuang
 * @studentId s3535252
 * Mar 25, 2018
 * dbquery.java
 * Describe:
 */
public class dbquery
{
	private static String bn_name="";
	private static int  page_size = 0;
	private static Date ds = null;
	private File file;

	public static void main(String[] args) throws IOException
	{
		if (args.length != 2)
		{
			System.out.println("The number of arguments is not correct.");
			return;
		}
		if (!args[0].equals(""))
		{
			bn_name = args[0];
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
		System.out.println("Retrieving...");
		ds = new Date();
		new dbquery().retrieve();
		Date de = new Date(); 
		long difference = (de.getTime() - ds.getTime());
		System.out.println("The number of milliseconds to seek the heap file is " + difference);
	}

	@SuppressWarnings("unused")
	public void retrieve()
	{
		int oneRecordLength = BytesUtils.getRecordFixLength();
		RandomAccessFile raf = null;
		long readPosition = 0;
		int  i=0;
		long fileLength = 0;
		try
		{
			file = new File("data/heap."+page_size);
			fileLength = file.length();
			raf = new RandomAccessFile(file, "r");
			int pageNO = 0;
			while(true)
			{
				byte[] page_bytes  = new byte[page_size];

				//get the current pointer
				long pointPosition = raf.getFilePointer();
				//get the remain length of the file
				long restLength = fileLength - pointPosition;
				if (restLength == 0)
				{
					break;
				}else
				{
					//the last page
					if (restLength < page_size)
					{
						page_bytes = new byte[(int)restLength];
					}
				}
				//read the file to page array from the pointer position
				long len = raf.read(page_bytes);
				if (len == -1)
				{
					break;
				}

				//search the business name from this page bytes array
				findNameFromPageBytes(bn_name,page_bytes,pageNO);
				
				readPosition = readPosition + len;

				//move the pointer to a new positon
				raf.seek(readPosition);
				pageNO = pageNO + 1;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
		try
		{
			raf.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
				
	}
	
	/**
	 * This method is used to find the business name from page bytes array
	 * @param strName
	 * @param page_bytes
	 * @param pageNo
	 * @return
	 */
	public ArrayList<RecordFix>  findNameFromPageBytes(String strName,byte[] page_bytes,int pageNo)
	{
		ArrayList<RecordFix> retValue = new ArrayList<RecordFix>();
		
		RecordFix record = new RecordFix();
		int recordLength = BytesUtils.getRecordFixLength();
		//
		int ridLength = 4;
		int rEGISTER_NAMELength = record.getREGISTER_NAME().length * 2;
		int bN_NAMELength = record.getBN_NAME().length * 2;

		///the start pointer position
		int offset = ridLength + rEGISTER_NAMELength;
		
		//
		int namePositon = offset;
		String tempName="";
		int rowCount=0;
		while(true)
		{
			if (namePositon >= page_bytes.length)
			{
				break;
			}
			//
			int j = 0;
			byte[] bn_name_bytes = new byte[bN_NAMELength];
			for (int i=namePositon; i< (namePositon + bN_NAMELength); i++)
			{
				if (i == page_size)
				{
					break;
				}
				bn_name_bytes[j] = page_bytes[i];
				j = j + 1;
			}
			//convert business name from bytes array to String
			tempName = BytesUtils.bytesToStringUTFCustom(bn_name_bytes).trim().toLowerCase();

			//found the name
			if (tempName.contains(strName.trim().toLowerCase()))
			{
				byte[] record_bytes = new byte[recordLength];
				int ridx = 0;
				int recordStartPosition = namePositon - offset;
				for (int r=recordStartPosition; r< (recordStartPosition + recordLength); r++)
				{
					record_bytes[ridx] =  page_bytes[r];
					ridx = ridx + 1;
				}
				RecordFix r = BytesUtils.getRecordFixByBytes(record_bytes);
				System.out.println("Page ID:"+pageNo + "," + r.toString());		
				retValue.add(r);
			}
			namePositon = namePositon + recordLength;
			rowCount = rowCount + 1;
		}
		
		//System.out.println("rowcount" + rowCount);
		return retValue;
	}

}

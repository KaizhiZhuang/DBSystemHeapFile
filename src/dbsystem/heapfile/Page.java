package dbsystem.heapfile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * this class is used to deal with Page function
 * @author Kaizhi.Zhuang
 * @studentId s3535252
 * Mar 25, 2018
 * Page.java
 * Describe:
 */
public class Page
{
	private int page_size;

	private byte[] page_bytes;
	
	private int position = 0;

	private ArrayList<RecordFix> recordList = new ArrayList<RecordFix>();

	public Page(int pageSize)
	{
		this.page_size = pageSize;
		this.page_bytes = new byte[pageSize];
	}

	public int getPage_size()
	{
		return page_size;
	}

	public byte[] getPage_bytes()
	{
		return page_bytes;
	}

	public ArrayList<RecordFix> getRecordList()
	{
		return recordList;
	}

	/**
	 * convert a String array to a Record
	 * @param item
	 * @return
	 */
	public RecordFix covert_to_recordFix(String[] item)
	{
		if (item.length != 9)
		{
			System.out.println("The record" + item[1] + " is wrong.");
		}

		RecordFix record = new RecordFix();
		int rid = recordList.size() + 1;
		record.setRid(rid);

		if (item[0] != null)
		{
			String s = item[0];
			s.getChars(0, s.length(), record.getREGISTER_NAME(), 0);
		}

		if (item[1] != null)
		{
			String s = item[1];
			s.getChars(0, s.length(), record.getBN_NAME(), 0);
		}

		if (item[2] != null)
		{
			String s = item[2];
			s.getChars(0, s.length(), record.getBN_STATUS(), 0);
		}

		// the integer type year month and date
		String[] bn_reg_date = new String[3];
		int[] bN_REG_DT = new int[3];
		if (item[3] != null && !item[3].equals(""))
		{
			bn_reg_date = item[3].split("/");
			try
			{
				bN_REG_DT[0] = Integer.parseInt(bn_reg_date[2]);
				bN_REG_DT[1] = Integer.parseInt(bn_reg_date[1]);
				bN_REG_DT[2] = Integer.parseInt(bn_reg_date[0]);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		record.setBN_REG_DT(bN_REG_DT);

		// the integer type year month and date
		String[] bn_cancel_date = new String[3];
		int[] bN_CANCEL_DT = new int[3];
		if (item[4] != null && !item[4].equals(""))
		{
			bn_cancel_date = item[4].split("/");
			try
			{
				bN_CANCEL_DT[0] = Integer.parseInt(bn_cancel_date[2]);
				bN_CANCEL_DT[1] = Integer.parseInt(bn_cancel_date[1]);
				bN_CANCEL_DT[2] = Integer.parseInt(bn_cancel_date[0]);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		record.setBN_CANCEL_DT(bN_CANCEL_DT);

		// the integer type year month and date
		String[] bn_renew_date = new String[3];
		int[] bN_RENEW_DT = new int[3];
		if (item[5] != null && !item[5].equals(""))
		{
			bn_renew_date = item[5].split("/");
			try
			{
				bN_RENEW_DT[0] = Integer.parseInt(bn_renew_date[2]);
				bN_RENEW_DT[1] = Integer.parseInt(bn_renew_date[1]);
				bN_RENEW_DT[2] = Integer.parseInt(bn_renew_date[0]);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		record.setBN_RENEW_DT(bN_RENEW_DT);

		if (item[6] != null)
		{
			String s = item[6];
			s.getChars(0, s.length(), record.getBN_STATE_NUM(), 0);
		}

		if (item[7] != null)
		{
			String s = item[7];
			s.getChars(0, s.length(), record.getBN_STATE_OF_REG(), 0);
		}

		long bN_ABN = 0;
		if (item[8] != null && !item[8].equals(""))
		{
			bN_ABN = Long.parseLong(item[8]);
		}
		record.setBN_ABN(bN_ABN);

		recordList.add(record);
		
		return record;
	}

	public void addBytesToArray(byte[] argData)
	{
		int arrLength = argData.length;
		System.arraycopy(argData, 0, page_bytes, position, arrLength); 
		position = position + arrLength;
	}
	
	/**
	 * Write this page to a file
	 * @param fileName
	 */
	public void witeToFile(String fileName)
	{
		File file = new File(fileName);
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file,true);//true is append
			fos.write(this.page_bytes);
			fos.flush();
			fos.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}

package heapfile_package;

import java.io.Serializable;



/**
 * The record class, it contains a record id and nine other columns
 * @author Jason.Zhuang
 * @studentId s3535252
 * Mar 17, 2018
 * Record.java
 * Describe:
 */
public class Record implements Serializable 
{
	private static final long serialVersionUID = 1L;
	//REGISTER_NAME	BN_NAME	BN_STATUS	BN_REG_DT	BN_CANCEL_DT	BN_RENEW_DT	BN_STATE_NUM	BN_STATE_OF_REG	BN_ABN
	//BUSINESS NAMES	Warby Wares	Deregistered	31/05/2015	12/10/2017	31/05/2017			48697696446
	int rid; //the Record id
	char[] REGISTER_NAME ;//= new char[20];
	char[] BN_NAME		 ;//= new char[200];
	char[] BN_STATUS     ;//= new char[15];
	//the integer type year month and date
	int[]  BN_REG_DT	 ;//= new int[3];
	int[]  BN_CANCEL_DT	 ;//= new int[3];
	int[]  BN_RENEW_DT	 ;//= new int[3];
	//
	char[] BN_STATE_NUM  ;//= new char[10];
	//
	char[] BN_STATE_OF_REG  ;//= new char[5];
	//
	long BN_ABN;
	
	public Record(int rid, char[] rEGISTER_NAME, char[] bN_NAME,
			char[] bN_STATUS, int[] bN_REG_DT, int[] bN_CANCEL_DT,
			int[] bN_RENEW_DT, char[] bN_STATE_NUM, char[] bN_STATE_OF_REG,
			long bN_ABN)
	{
		super();
		this.rid = rid;
		REGISTER_NAME = rEGISTER_NAME;
		BN_NAME = bN_NAME;
		BN_STATUS = bN_STATUS;
		BN_REG_DT = bN_REG_DT;
		BN_CANCEL_DT = bN_CANCEL_DT;
		BN_RENEW_DT = bN_RENEW_DT;
		BN_STATE_NUM = bN_STATE_NUM;
		BN_STATE_OF_REG = bN_STATE_OF_REG;
		BN_ABN = bN_ABN;
	}

	public Record()
	{
		;
	}
	
	public int getRid()
	{
		return rid;
	}

	public void setRid(int rid)
	{
		this.rid = rid;
	}

	public char[] getREGISTER_NAME()
	{
		return REGISTER_NAME;
	}

	public void setREGISTER_NAME(char[] rEGISTER_NAME)
	{
		REGISTER_NAME = rEGISTER_NAME;
	}

	public char[] getBN_NAME()
	{
		return BN_NAME;
	}

	public void setBN_NAME(char[] bN_NAME)
	{
		BN_NAME = bN_NAME;
	}

	public char[] getBN_STATUS()
	{
		return BN_STATUS;
	}

	public void setBN_STATUS(char[] bN_STATUS)
	{
		BN_STATUS = bN_STATUS;
	}

	public int[] getBN_REG_DT()
	{
		return BN_REG_DT;
	}

	public void setBN_REG_DT(int[] bN_REG_DT)
	{
		BN_REG_DT = bN_REG_DT;
	}

	public int[] getBN_CANCEL_DT()
	{
		return BN_CANCEL_DT;
	}

	public void setBN_CANCEL_DT(int[] bN_CANCEL_DT)
	{
		BN_CANCEL_DT = bN_CANCEL_DT;
	}

	public int[] getBN_RENEW_DT()
	{
		return BN_RENEW_DT;
	}

	public void setBN_RENEW_DT(int[] bN_RENEW_DT)
	{
		BN_RENEW_DT = bN_RENEW_DT;
	}

	public char[] getBN_STATE_NUM()
	{
		return BN_STATE_NUM;
	}

	public void setBN_STATE_NUM(char[] bN_STATE_NUM)
	{
		BN_STATE_NUM = bN_STATE_NUM;
	}

	public char[] getBN_STATE_OF_REG()
	{
		return BN_STATE_OF_REG;
	}

	public void setBN_STATE_OF_REG(char[] bN_STATE_OF_REG)
	{
		BN_STATE_OF_REG = bN_STATE_OF_REG;
	}

	public long getBN_ABN()
	{
		return BN_ABN;
	}

	public void setBN_ABN(long bN_ABN)
	{
		BN_ABN = bN_ABN;
	}

	public static int getFixRecordLength()
	{
		int retValue =  (1 + 3 + 3 + 3 + 1) * Integer.BYTES + (20+200+15+10+5) * Character.BYTES;
		return retValue;
	}
	
	public int getDynamicRecordLength()
	{
		int a = 1 * Integer.BYTES;
		//
		int b = REGISTER_NAME.length * Character.BYTES;
		int c = BN_NAME.length * Character.BYTES;
		int d = BN_STATUS.length * Character.BYTES;
		//
		int e = BN_REG_DT.length * Integer.BYTES;
		int f = BN_CANCEL_DT.length * Integer.BYTES;
		int g = BN_RENEW_DT.length * Integer.BYTES;
		//
		int h = BN_STATE_NUM.length * Character.BYTES;
		int i = BN_STATE_OF_REG.length * Character.BYTES;
		//
		int j = 1 * Long.BYTES;
		
		return a+b+c+d+e+f+g+h+i+j;
	}
}
	
	

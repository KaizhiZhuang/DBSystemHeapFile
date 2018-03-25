package dbsystem.heapfile;

/**
 * this is an object class of Record
 * @author Jason.Zhuang
 * @studentId s3535252
 * Mar 25, 2018
 * RecordFix.java
 * Describe:
 */
public class RecordFix
{
	//REGISTER_NAME	BN_NAME	BN_STATUS	BN_REG_DT	BN_CANCEL_DT	BN_RENEW_DT	BN_STATE_NUM	BN_STATE_OF_REG	BN_ABN
	//BUSINESS NAMES	Warby Wares	Deregistered	31/05/2015	12/10/2017	31/05/2017			48697696446
	private int    rid; //the Record id
	private char[] REGISTER_NAME	= new char[20];
	private char[] BN_NAME	   		= new char[200];
	private char[] BN_STATUS     	= new char[15];
	
	//the integer type year month and date
	private int[]  BN_REG_DT	 	= new int[3];
	private int[]  BN_CANCEL_DT	 	= new int[3];
	private int[]  BN_RENEW_DT	 	= new int[3];
	//
	private char[] BN_STATE_NUM  	= new char[10];
	//
	private char[] BN_STATE_OF_REG  = new char[5];
	//
	private long BN_ABN;

	public RecordFix()
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
	
	public String toString()
	{
		int rid = this.getRid();
		String reg_name = new String(getREGISTER_NAME()).trim();
		String bn_name  = new String(getBN_NAME()).trim();
		String bn_status= new String(getBN_STATUS()).trim();
		
		String bn_reg_dt= "";
		if (getBN_REG_DT()==null || getBN_REG_DT().length!=3)
		{
			;
		}else
		{
			bn_reg_dt = getBN_REG_DT()[0]+"-"+getBN_REG_DT()[1]+"-"+getBN_REG_DT()[2];
		}
		String bn_cancel_dt ="";
		if (getBN_CANCEL_DT()==null || getBN_CANCEL_DT().length!=3)
		{
			;
		}else
		{
			bn_cancel_dt = getBN_CANCEL_DT()[0]+"-"+getBN_CANCEL_DT()[1]+"-"+getBN_CANCEL_DT()[2];
		}
		String bn_renew_dt ="";
		if (getBN_RENEW_DT()==null || getBN_RENEW_DT().length!=3)
		{
			;
		}else
		{
			bn_renew_dt = getBN_RENEW_DT()[0]+"-"+getBN_RENEW_DT()[1]+"-"+getBN_RENEW_DT()[2];
		}
		
		String bn_state_num 	=  new String(getBN_STATE_NUM()).trim();
		String bn_state_of_reg 	=  new String(getBN_STATE_OF_REG()).trim();
		String bn_abn = String.valueOf(getBN_ABN());
		
		String strRecord  ="RID:"+rid+","
						  +"REGISTER_NAME:" + reg_name +","
						  +"BN_NAME:"+bn_name +","
						  +"BN_STATUS:"+bn_status +","
						  +"BN_REG_DT:"+bn_reg_dt +","
						  +"BN_CANCEL_DT:"+bn_cancel_dt +","
						  +"BN_RENEW_DT:"+bn_renew_dt +","
						  +"BN_STATE_NUM:"+bn_state_num +","
						  +"BN_STATE_OF_REG:"+bn_state_of_reg +","
						  +"BN_ABN:"+bn_abn ;
						  
		
		return strRecord;
	}
}

package dbsystem.heapfile;

import java.nio.ByteBuffer;

/**
 * this is the some common method used in the project
 * @author Kaizhi.Zhuang
 * @studentId s3535252
 * Mar 25, 2018
 * BytesUtils.java
 * Describe:
 */
public class BytesUtils
{
	/**
	 * conver integer to bytes array
	 * @param value
	 * @return
	 */
	public static byte[] intTobytesArray(int value)
	{
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * convert bytes array to an integer
	 * @param b
	 * @return
	 */
	public static int bytesArrayToInt(byte[] b)
	{
		return b[0] & 0xFF | (b[1] & 0xFF) << 8 | (b[2] & 0xFF) << 16
				| (b[3] & 0xFF) << 24;
	}

	/**
	 * convert bytes array to an Integer, from a offset positon
	 * @param src
	 * @param offset
	 * @return
	 */
	public static int bytesArrayToInt(byte[] src, int offset)
	{
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8)
				| ((src[offset + 2] & 0xFF) << 16) | ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

	/**
	 * convert a Long type value to bytes array
	 * @param x
	 * @return
	 */
	public static byte[] longToBytesArray(long x)
	{
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(0, x);
		return buffer.array();
	}

	/**
	 * convert bytes array to a Long type value
	 * @param bytes
	 * @return
	 */
	public static long bytesArrayToLong(byte[] bytes)
	{
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();// need flip
		return buffer.getLong();
	}

	/**
	 * Convert a String to Bytes array
	 * @param str
	 * @return
	 */
	public static byte[] stringToBytesUTFCustom(String str)
	{
		byte[] b = new byte[str.length() << 1];
		for (int i = 0; i < str.length(); i++)
		{
			char strChar = str.charAt(i);
			int bpos = i << 1;
			b[bpos] = (byte) ((strChar & 0xFF00) >> 8);
			b[bpos + 1] = (byte) (strChar & 0x00FF);
		}
		return b;
	}

	/**
	 * convert a bytes array to a String
	 * @param bytes
	 * @return
	 */
	public static String bytesToStringUTFCustom(byte[] bytes)
	{
		char[] buffer = new char[bytes.length >> 1];
		for (int i = 0; i < buffer.length; i++)
		{
			int bpos = i << 1;
			char c = (char) (((bytes[bpos] & 0x00FF) << 8) + (bytes[bpos + 1] & 0x00FF));
			buffer[i] = c;
		}
		return new String(buffer);
	}

	/**
	 * convert a char array to a bytes array
	 * @param arr
	 * @return
	 */
	public static byte[] charArrayToBytes(char[] arr)
	{
		byte[] bytes = new byte[2 * arr.length];
		for (int i = 0; i < arr.length; i++)
		{
			bytes[2 * i] = (byte) ((arr[i] & 0xFF00) >> 8);
			bytes[2 * i + 1] = (byte) (arr[i] & 0xFF);
		}
		return bytes;
	}

	/**
	 * convert a bytes array to a bytes array, from start positon to stop positon
	 * @param bytes
	 * @param start
	 * @param stop
	 * @return
	 */
	public static char[] bytesToCharArray(byte[] bytes, int start, int stop)
	{
		char[] arr = new char[(stop - start) / 2];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (char) (((bytes[start + 2 * i] & 0xFF) << 8) | (bytes[start
					+ 2 * i + 1] & 0xFF));
		}
		return arr;
	}

	/**
	 * convert a char to bytes array
	 * @param c
	 * @return
	 */
	public static byte[] charToBytes(char c)
	{
		byte[] b = new byte[2];
		b[0] = (byte) ((c & 0xFF00) >> 8);
		b[1] = (byte) (c & 0xFF);
		return b;
	}

	/**
	 * get fixed length of a record
	 * @return
	 */
	public static int getRecordFixLength()
	{
		RecordFix record = new RecordFix();
		return getRecordFixBytes(record).length;
	}

	/**
	 * conver a record to a byte array
	 * @param record
	 * @return
	 */
	public static byte[] getRecordFixBytes(RecordFix record)
	{
		byte[] returnValue = null;
		// byte[] b_split_recode = charTobyte((char)';');
		// byte[] b_split_column = charTobyte((char)',');
		byte[] b_recordID = intTobytesArray(record.getRid());
		byte[] b_REGISTER_NAME = charArrayToBytes(record.getREGISTER_NAME());
		byte[] b_BN_NAME = charArrayToBytes(record.getBN_NAME());
		byte[] b_BN_STATUS = charArrayToBytes(record.getBN_STATUS());

		// the integer type year month and date
		// int[] BN_REG_DT = new int[3];
		byte[] b_BN_REG_DT_Y = intTobytesArray(record.getBN_REG_DT()[0]);
		byte[] b_BN_REG_DT_M = intTobytesArray(record.getBN_REG_DT()[1]);
		byte[] b_BN_REG_DT_D = intTobytesArray(record.getBN_REG_DT()[2]);

		// int[] BN_CANCEL_DT = new int[3];
		byte[] b_BN_CANCEL_DT_Y = intTobytesArray(record.getBN_CANCEL_DT()[0]);
		byte[] b_BN_CANCEL_DT_M = intTobytesArray(record.getBN_CANCEL_DT()[1]);
		byte[] b_BN_CANCEL_DT_D = intTobytesArray(record.getBN_CANCEL_DT()[2]);

		// int[] BN_RENEW_DT = new int[3];
		byte[] b_BN_RENEW_DT_Y = intTobytesArray(record.getBN_RENEW_DT()[0]);
		byte[] b_BN_RENEW_DT_M = intTobytesArray(record.getBN_RENEW_DT()[1]);
		byte[] b_BN_RENEW_DT_D = intTobytesArray(record.getBN_RENEW_DT()[2]);

		//
		byte[] b_BN_STATE_NUM = charArrayToBytes(record.getBN_STATE_NUM());
		//
		byte[] b_BN_STATE_OF_REG = charArrayToBytes(record.getBN_STATE_OF_REG());
		//
		byte[] b_BN_ABN = longToBytesArray(record.getBN_ABN());

		int len = b_recordID.length + b_REGISTER_NAME.length + b_BN_NAME.length
				+ b_BN_STATUS.length + b_BN_REG_DT_Y.length + b_BN_REG_DT_M.length + b_BN_REG_DT_D.length
				+ b_BN_CANCEL_DT_Y.length + b_BN_CANCEL_DT_M.length + b_BN_CANCEL_DT_D.length+
				+ b_BN_RENEW_DT_Y.length + b_BN_RENEW_DT_M.length + b_BN_RENEW_DT_D.length
				+ b_BN_STATE_NUM.length + b_BN_STATE_OF_REG.length + b_BN_ABN.length;
				
		returnValue = new byte[len];
		int position = 0;

		System.arraycopy(b_recordID, 0, returnValue, 0, b_recordID.length);
		position = position + b_recordID.length;

		System.arraycopy(b_REGISTER_NAME, 0, returnValue, position,
				b_REGISTER_NAME.length);
		position = position + b_REGISTER_NAME.length;

		System.arraycopy(b_BN_NAME, 0, returnValue, position, b_BN_NAME.length);
		position = position + b_BN_NAME.length;

		System.arraycopy(b_BN_STATUS, 0, returnValue, position,
				b_BN_STATUS.length);
		position = position + b_BN_STATUS.length;

		System.arraycopy(b_BN_REG_DT_Y, 0, returnValue, position,
				b_BN_REG_DT_Y.length);
		position = position + b_BN_REG_DT_Y.length;

		System.arraycopy(b_BN_REG_DT_M, 0, returnValue, position,
				b_BN_REG_DT_M.length);
		position = position + b_BN_REG_DT_M.length;

		System.arraycopy(b_BN_REG_DT_D, 0, returnValue, position,
				b_BN_REG_DT_D.length);
		position = position + b_BN_REG_DT_D.length;

		System.arraycopy(b_BN_CANCEL_DT_Y, 0, returnValue, position,
				b_BN_CANCEL_DT_Y.length);
		position = position + b_BN_CANCEL_DT_Y.length;

		System.arraycopy(b_BN_CANCEL_DT_M, 0, returnValue, position,
				b_BN_CANCEL_DT_M.length);
		position = position + b_BN_CANCEL_DT_M.length;

		System.arraycopy(b_BN_CANCEL_DT_D, 0, returnValue, position,
				b_BN_CANCEL_DT_D.length);
		position = position + b_BN_CANCEL_DT_D.length;

		System.arraycopy(b_BN_RENEW_DT_Y, 0, returnValue, position,
				b_BN_RENEW_DT_Y.length);
		position = position + b_BN_RENEW_DT_Y.length;

		System.arraycopy(b_BN_RENEW_DT_M, 0, returnValue, position,
				b_BN_RENEW_DT_M.length);
		position = position + b_BN_RENEW_DT_M.length;

		System.arraycopy(b_BN_RENEW_DT_D, 0, returnValue, position,
				b_BN_RENEW_DT_D.length);
		position = position + b_BN_RENEW_DT_D.length;

		System.arraycopy(b_BN_STATE_NUM, 0, returnValue, position,
				b_BN_STATE_NUM.length);
		position = position + b_BN_STATE_NUM.length;

		System.arraycopy(b_BN_STATE_OF_REG, 0, returnValue, position,
				b_BN_STATE_OF_REG.length);
		position = position + b_BN_STATE_OF_REG.length;

		System.arraycopy(b_BN_ABN, 0, returnValue, position, b_BN_ABN.length);
		position = position + b_BN_ABN.length;

		return returnValue;
	}

	/**
	 * convert byte array to a Record
	 * @param recordBytes
	 * @return
	 */
	public static RecordFix getRecordFixByBytes(byte[] recordBytes)
	{
		RecordFix retValue = new RecordFix();
		int columnStartPosition = 0;
		int columnEndPosition = 0;
		//=====
		int columnLength  = 4;
		byte[] tempColumnBytes = new byte[columnLength];
		int idx = 0;
		columnEndPosition = columnStartPosition + columnLength;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setRid(BytesUtils.bytesArrayToInt(tempColumnBytes));
		
		//=====
		columnLength  = retValue.getREGISTER_NAME().length * 2;
		columnEndPosition = columnStartPosition + columnLength;
		tempColumnBytes = new byte[columnLength];
		idx = 0;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setREGISTER_NAME(BytesUtils.bytesToCharArray(tempColumnBytes, 0, columnLength));
		
		//=====
		columnLength  = retValue.getBN_NAME().length * 2;
		columnEndPosition = columnStartPosition + columnLength;
		tempColumnBytes = new byte[columnLength];
		idx = 0;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setBN_NAME(BytesUtils.bytesToCharArray(tempColumnBytes, 0, columnLength));
		
		//=====
		columnLength  = retValue.getBN_STATUS().length * 2;
		columnEndPosition = columnStartPosition + columnLength;
		tempColumnBytes = new byte[columnLength];
		idx = 0;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setBN_STATUS(BytesUtils.bytesToCharArray(tempColumnBytes, 0, columnLength));		
		
		//=====
		int[] bN_REG_DT  = new int[3];
		for(int datei=0;datei<3;datei++)
		{
			columnLength  = 4;
			columnEndPosition = columnStartPosition + columnLength;
			tempColumnBytes = new byte[columnLength];
			idx = 0;
			for(int i=columnStartPosition; i< columnEndPosition; i++)
			{
				tempColumnBytes[idx] = recordBytes[i];
				idx = idx + 1;
			}
			columnStartPosition = columnEndPosition;
			bN_REG_DT[datei]= BytesUtils.bytesArrayToInt(tempColumnBytes);
		}
		retValue.setBN_REG_DT(bN_REG_DT);
	
		//====
		int[] bN_CANCEL_DT  = new int[3];
		for(int datei=0;datei<3;datei++)
		{
			columnLength  = 4;
			columnEndPosition = columnStartPosition + columnLength;
			tempColumnBytes = new byte[columnLength];
			idx = 0;
			for(int i=columnStartPosition; i< columnEndPosition; i++)
			{
				tempColumnBytes[idx] = recordBytes[i];
				idx = idx + 1;
			}
			columnStartPosition = columnEndPosition;
			bN_CANCEL_DT[datei]= BytesUtils.bytesArrayToInt(tempColumnBytes);
		}
		retValue.setBN_CANCEL_DT(bN_CANCEL_DT);
				
		//====
		int[] bN_RENEW_DT  = new int[3];
		for(int datei=0;datei<3;datei++)
		{
			columnLength  = 4;
			columnEndPosition = columnStartPosition + columnLength;
			tempColumnBytes = new byte[columnLength];
			idx = 0;
			for(int i=columnStartPosition; i< columnEndPosition; i++)
			{
				tempColumnBytes[idx] = recordBytes[i];
				idx = idx + 1;
			}
			columnStartPosition = columnEndPosition;
			bN_RENEW_DT[datei]= BytesUtils.bytesArrayToInt(tempColumnBytes);
		}
		retValue.setBN_RENEW_DT(bN_RENEW_DT);

		//
		columnLength  = retValue.getBN_STATE_NUM().length * 2;
		columnEndPosition = columnStartPosition + columnLength;
		tempColumnBytes = new byte[columnLength];
		idx = 0;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setBN_STATE_NUM(BytesUtils.bytesToCharArray(tempColumnBytes, 0, columnLength));	
		
		//
		columnLength  = retValue.getBN_STATE_OF_REG().length * 2;
		columnEndPosition = columnStartPosition + columnLength;
		tempColumnBytes = new byte[columnLength];
		idx = 0;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setBN_STATE_OF_REG(BytesUtils.bytesToCharArray(tempColumnBytes, 0, columnLength));	
		
		//
		columnLength  = 8;
		columnEndPosition = columnStartPosition + columnLength;
		tempColumnBytes = new byte[columnLength];
		idx = 0;
		for(int i=columnStartPosition; i< columnEndPosition; i++)
		{
			tempColumnBytes[idx] = recordBytes[i];
			idx = idx + 1;
		}
		columnStartPosition = columnEndPosition;
		retValue.setBN_ABN(BytesUtils.bytesArrayToLong(tempColumnBytes));	
		
		//
		return retValue;
	}
	
	/**
	 * combine two byte arraies to a new arrray
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static byte[] addBytes1(byte[] data1, byte[] data2)
	{
		byte[] data3 = new byte[data1.length + data2.length];
		System.arraycopy(data1, 0, data3, 0, data1.length);
		System.arraycopy(data2, 0, data3, data1.length, data2.length);
		return data3;
	}

}

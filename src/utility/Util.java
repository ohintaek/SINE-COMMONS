package utility;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;


public class Util
{

	
	/*************************************************************************************
	 * ���� 	 : Serialized interface �� ������ ��ü�� ����ȭ�� byte �迭�� ���Ѵ�.
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2006. 12. 13
	 * @param _oObject	: Serialized interface �� ������ ��ü
	 * @return : byte �迭
	 * @throws Exception 
	 * �����̷�
	 *  1. ...
	 */
	public static byte[] ObjectToByte(Object _oObject) throws Exception
	{
		ByteArrayOutputStream baosMsg = null;
		ObjectOutputStream oosMsg = null;
		try
		{		
			// 1. _oRSVDPkt�� Body�κ���  byte �迭�� ��ȯ�Ѵ�.
            baosMsg = new ByteArrayOutputStream();
            oosMsg = new ObjectOutputStream(baosMsg);
            oosMsg.writeObject(_oObject);
            oosMsg.flush();
            baosMsg.flush();
            
            // 2. ��ȯ�� byte �迭�� �����Ѵ�.
            return baosMsg.toByteArray();
		}
		catch(Exception e)
		{
			throw new Exception("Message ������ ����", e);
		}
		finally
		{
			try
			{
				// ��Ʈ���� �ݴ´�.
				if(oosMsg != null)
				{
					oosMsg.close();
					oosMsg = null;
				}
				
				if(baosMsg != null)
				{
					baosMsg.close();
					baosMsg = null;
				}
			}
			catch(IOException e)
			{
				// Ignore...
			}
		}
	
	}
	
	/*************************************************************************************
	 * ���� 	 :
	 * �ۼ��� : lee mira
	 * �ۼ��� : 2007. 
	 * @param _bData
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * �����̷�
	 *  1. ...
	 */
	public static Object ByteToObject( byte[] _bData ) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream biMsg = null;
		ObjectInputStream oisMsg = null;
		
		try
		{
			biMsg = new ByteArrayInputStream( _bData );
			oisMsg = new ObjectInputStream( biMsg );
			
			return oisMsg.readObject();
		}
		catch(IOException e)
		{
			throw e;
		}
		catch(ClassNotFoundException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if(oisMsg != null)
				{
					oisMsg.close();
					oisMsg = null;
				}
				if(biMsg != null)
				{
					biMsg.close();
					biMsg = null;
				}				
			}
			catch (IOException e)
			{
				// Ignore...
			}
		}
	}
	
	/*************************************************************************************
	 * ���� 	 :
	 * �ۼ��� : lee mira
	 * �ۼ��� : 2007. 
	 * @param str
	 * @return 
	 * �����̷�
	 *  1. ...
	 * @throws UnsupportedEncodingException 
	 */
	public static String uni2ksc(String str) throws UnsupportedEncodingException 
	{
		String result = null;
		try 
		{
			result = new String(str.getBytes("8859_1"), "KSC5601");
		} 
		catch (java.io.UnsupportedEncodingException e) 
		{
			throw e;
		}
		return result;
	}
	
	/*************************************************************************************
	 * ���� 	 : 
	 * �ۼ��� : lee mira
	 * �ۼ��� : 2007. 
	 * @param str
	 * @return 
	 * �����̷�
	 *  1. ...
	 * @throws UnsupportedEncodingException 
	 */
	public static String ksc2uni( String str ) throws UnsupportedEncodingException
	{
		String result = null;
		
		try 
		{
			result = new String(str.getBytes("KSC5601"), "8859_1");
		} 
		catch (java.io.UnsupportedEncodingException e) 
		{
			throw e;
		}
		return result;
	}
	
	/*************************************************************************************
	 * Ŭ������ ª�� �̸��� ���Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2007. 07. 04
	 * @param cls : Class
	 * @return 
	 * @�����̷�
	 *  1. ...
	 */
	public static String getClassShortName(@SuppressWarnings("rawtypes") Class cls)
	{
		return cls.getName().replaceFirst(cls.getPackage().getName(), "T");
	}
	
	/*************************************************************************************
	 * ���๮�ڸ� ���Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2007. 07. 25
	 * @return 
	 * 	1. UNIX �迭 : \n
	 *  2. MS �迭 : \r\n
	 * @�����̷�
	 *  1. ...
	 */
	public static String getLineSeparator()
	{
		return System.getProperty("line.separator");
	}
	
	/*************************************************************************************
	 * Exception��ü�� ��������޽����� ��� ���ؼ� ���Ѵ�.
	 * 	1. ���������� Exception�� ��ø�Ǿ�������� �����޽��������� ��ø���� �ʵ��� ó�� �Ͽ���  
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2009. 02. 09
	 * @param t
	 * @return 
	 * @�����̷�
	 *  1. ...
	 */
	public static String getExceptionMessage(Exception t)
	{
		if(t == null)
			return "";
		
		String errMsg = t.getMessage();
		
		Throwable st = t.getCause();
		String stMsg = null;
		int iDepth = 0;
		while((st != null) && (iDepth < 10/*Exception �� 10�� ���Ϸθ� Ȯ���Ѵ�.*/))
		{
			stMsg = st.getMessage();
			if(errMsg == null)
				errMsg = stMsg;
			else if(stMsg == null)
				break;
			else if(errMsg.endsWith(stMsg) == false)
				errMsg += "\n"+ stMsg;
				
			
			st = st.getCause();
			iDepth++;
		}
		
		return errMsg;
	}
	
	/*************************************************************************************
	 * ����Ʈ�迭�� int ������ ��ȯ�Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2009. 09. 21
	 * @param b : ����Ʈ�迭
	 * @return : int ��
	 * @�����̷�
	 *  1. ...
	 */
	public static int byteArrayToInt(byte[] b)
	{
		return 	(b[0] << 24) + 
				((b[1] & 0xFF) << 16) +
				((b[2] & 0xFF) << 8 ) +
				(b[3] & 0xFF);
	}
	
	/*************************************************************************************
	 * int ������ ����Ʈ�迭�� ��ȭ�Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2009. 09. 21
	 * @param value : int ��
	 * @return : ����Ʈ�迭
	 * @�����̷�
	 *  1. ...
	 */
	public static byte [] intToByteArray(int value)
	{
		return new byte[]
		                {
							(byte)(value >>> 24),
							(byte)(value >>> 16),
							(byte)(value >>> 8),
							(byte)(value)
		                };
	}
}

package utility;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;


public class Util
{

	/*************************************************************************************
	 * Serialized interface 를 구현한 객체의 직렬화된 byte 배열을 구한다.
	 * @작성자 : ohintaek
	 * @작성일 : 2006. 12. 13
	 * @param _oObject	: Serialized interface 를 구현한 객체
	 * @return : byte 배열
	 * @throws Exception 
	 * @수정이력
	 *  1. ...
	 */
	public static byte[] ObjectToByte(Object _oObject) throws Exception
	{
		ByteArrayOutputStream baosMsg = null;
		ObjectOutputStream oosMsg = null;
		try
		{		
			// 1. _oRSVDPkt의 Body부분을  byte 배열로 변환한다.
            baosMsg = new ByteArrayOutputStream();
            oosMsg = new ObjectOutputStream(baosMsg);
            oosMsg.writeObject(_oObject);
            oosMsg.flush();
            baosMsg.flush();
            
            // 2. 변환된 byte 배열을 리턴한다.
            return baosMsg.toByteArray();
		}
		catch(Exception e)
		{
			throw new Exception("Message 마샬링 실패", e);
		}
		finally
		{
			try
			{
				// 스트림을 닫는다.
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
	 * 설명 	 :
	 * 작성자 : lee mira
	 * 작성일 : 2007. 
	 * @param _bData
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * 수정이력
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
	 * 설명 	 :
	 * 작성자 : lee mira
	 * 작성일 : 2007. 
	 * @param str
	 * @return 
	 * 수정이력
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
	 * 설명 	 : 
	 * 작성자 : lee mira
	 * 작성일 : 2007. 
	 * @param str
	 * @return 
	 * 수정이력
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
	 * 클래스의 짧은 이름을 구한다.
	 * @작성자 : flytaek
	 * @작성일 : 2007. 07. 04
	 * @param cls : Class
	 * @return 
	 * @수정이력
	 *  1. ...
	 */
	public static String getClassShortName(@SuppressWarnings("rawtypes") Class cls)
	{
		return cls.getName().replaceFirst(cls.getPackage().getName(), "T");
	}
	
	/*************************************************************************************
	 * 개행문자를 구한다.
	 * @작성자 : flytaek
	 * @작성일 : 2007. 07. 25
	 * @return 
	 * 	1. UNIX 계열 : \n
	 *  2. MS 계열 : \r\n
	 * @수정이력
	 *  1. ...
	 */
	public static String getLineSeparator()
	{
		return System.getProperty("line.separator");
	}
	
	/*************************************************************************************
	 * Exception객체의 서브오류메시지를 모두 합해서 구한다.
	 * 	1. 여러종류의 Exception이 중첩되어있을경우 오류메시지내용이 중첩되지 않도록 처리 하였음  
	 * @작성자 : flytaek
	 * @작성일 : 2009. 02. 09
	 * @param t
	 * @return 
	 * @수정이력
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
		while((st != null) && (iDepth < 10/*Exception 을 10개 이하로만 확인한다.*/))
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
	 * 바이트배열을 int 형으로 변환한다.
	 * @작성자 : flytaek
	 * @작성일 : 2009. 09. 21
	 * @param b : 바이트배열
	 * @return : int 값
	 * @수정이력
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
	 * int 형값을 바이트배열로 변화한다.
	 * @작성자 : flytaek
	 * @작성일 : 2009. 09. 21
	 * @param value : int 값
	 * @return : 바이트배열
	 * @수정이력
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

/**========================================================
 *@FileName : StringUtil.java
 *
 *@LastModifyDate : 2007. 4. 26
 *@LastModifier : flytaek
 *@LastVersion : 1.0
 *    2007. 4. 26    flytaek    1.0    ���ʻ��� 
 =========================================================*/
package utility;



/**
 * ����	 : ���ڿ� ���� Uitlity
 * �ۼ��� : flytaek
 * �ۼ��� : 2007. 4. 26
 *
 */
public class StringUtil
{

	/*************************************************************************************
	 * ���� 	 : ������ �ڷḸ �ִ� ���ڿ����� Ȯ���Ѵ�.
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2007. 4. 26
	 * @param _sValue : �˻��� ���ڿ�
	 * @return : true(������ �ڷ�), false(������ �ڷ� �ƴ�)
	 * �����̷�
	 *  1. ...
	 */
	public static boolean isNumericInt(String _sValue)
	{
		boolean bResutn = true;
		char ci = _sValue.charAt(0);
		int iStartIndex = 0;
		if(ci == '-')
			iStartIndex = 1;
		
		for(int i = iStartIndex; i < _sValue.length(); i++)
		{
			ci = _sValue.charAt(i);
			if(!(ci == '0' || ci == '1' || ci == '2' || ci == '3' || ci == '4' || 
				 ci == '5' || ci == '6' || ci == '7' || ci == '8' || ci == '9' ))
			{
				bResutn = false;
				break; // for(i)
			}
		}
		
		return bResutn;
	}
	

	/*************************************************************************************
	 * �޼��� ������ ����Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2007. 07. 05
	 * @param _sFormat
	 * @param _iValue
	 * @return 
	 * @�����̷�
	 *  1. ...
	 */
	public static String getNumberFormat(String _sFormat, int _iValue)
	{
		java.text.DecimalFormat df = new java.text.DecimalFormat(_sFormat);
		return df.format(_iValue);
	}
}

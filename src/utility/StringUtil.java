/**========================================================
 *@FileName : StringUtil.java
 *
 *@LastModifyDate : 2007. 4. 26
 *@LastModifier : flytaek
 *@LastVersion : 1.0
 *    2007. 4. 26    flytaek    1.0    최초생성 
 =========================================================*/
package utility;



/**
 * 설명	 : 문자열 관련 Uitlity
 * 작성자 : flytaek
 * 작성일 : 2007. 4. 26
 *
 */
public class StringUtil
{

	/*************************************************************************************
	 * 설명 	 : 정수형 자료만 있는 문자열인지 확인한다.
	 * 작성자 : flytaek
	 * 작성일 : 2007. 4. 26
	 * @param _sValue : 검사할 문자열
	 * @return : true(정수형 자료), false(정수형 자료 아님)
	 * 수정이력
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
	 * 메서드 설명을 기술한다.
	 * @작성자 : flytaek
	 * @작성일 : 2007. 07. 05
	 * @param _sFormat
	 * @param _iValue
	 * @return 
	 * @수정이력
	 *  1. ...
	 */
	public static String getNumberFormat(String _sFormat, int _iValue)
	{
		java.text.DecimalFormat df = new java.text.DecimalFormat(_sFormat);
		return df.format(_iValue);
	}
}

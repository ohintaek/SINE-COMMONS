/**========================================================
 *@FileName : DateUtil.java
 *
 *@LastModifyDate : 2008. 08. 20
 *@LastModifier : flytaek
 *@LastVersion : 1.0
 *    2008. 08. 20    flytaek    1.0    최초생성 
 =========================================================*/
package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 일자/시간 정보 관련 유틸리티
 * <BR>
 * <p>Copyright 2006-2007 the original author or authors.</p>
 *
 * @version 1.0
 * @author flytaek
 */
public class DateUtil
{
    /*************************************************************************************
     * Date를 패턴형식에 맞도록 문자열로 변환해 준다.
     * @작성자 : flytaek
     * @작성일 : 2008. 07. 15
     * @param date		: Date
     * @param pattern	: 패턴형식(ex, yyyy-MM-dd HH:mm:ss)
     * @return 
     * @수정이력
     *  1. ...
     */
    public static String getFormatedDate(Date date, String pattern) 
    {
        return new SimpleDateFormat(pattern).format( date );
    }
    
    /*************************************************************************************
     * 현재시간을 패턴형식에 맞도록 문자열로 변환해 준다.
     * @작성자 : flytaek
     * @작성일 : 2009. 02. 04
     * @param pattern
     * @return 
     * @수정이력
     *  1. ...
     */
    public static String getFormatedCurrentDate(String pattern) 
    {
        return getFormatedDate(Calendar.getInstance().getTime(), pattern);
    }    
    
    /*************************************************************************************
     * 날자값문자열을 Date객체로 변환한다.
     * @작성자 : flytaek
     * @작성일 : 2009. 05. 26
     * @param dateValue : '20090301', '2009030112', '200903011201', '20090301120130'
     * @return : 변환된 Date 객체
     * @수정이력
     *  1. ...
     */
    public static Date getDate(String dateValue)
    {
		int iYYYY = Integer.parseInt(dateValue.substring(0,4));
		int iMM = Integer.parseInt(dateValue.substring(4,6));
		int iDD = Integer.parseInt(dateValue.substring(6,8));

		int iHH = (dateValue.length() >= 10) ? Integer.parseInt(dateValue.substring(8,10)) : 0;
		int iMI = (dateValue.length() >= 12) ? Integer.parseInt(dateValue.substring(10,12)) : 0;
		int iSS = (dateValue.length() >= 14) ? Integer.parseInt(dateValue.substring(12,14)) : 0;

		Calendar cal = Calendar.getInstance();
		cal.set(iYYYY, iMM - 1/*JDK 스펙상 1을  빼줘야 한다.*/, iDD, iHH, iMI, iSS);
		return cal.getTime();
    }
    
    /*************************************************************************************
     * 줄루타임 값을 Date 값으로 변환한다.
     * @작성자 : flytaek
     * @작성일 : 2009. 08. 21
     * @param zTime : '210556ZAUG2009'
     * @return : 변환된 Date 객체
     * @throws Exception 
     * @수정이력
     *  1. ...
     */
    public static Date convertZTimeToDate(String zTime) throws Exception
    {
    	// 210556ZAUG2009
    	// 01234567890123
    	
		int iYYYY = Integer.parseInt(zTime.substring(10,14));
		int iMM;
		String month = zTime.substring(7,10);
		if(month.equalsIgnoreCase("JAN"))
			iMM = 1;
		else if(month.equalsIgnoreCase("FEB"))
			iMM = 2;
		else if(month.equalsIgnoreCase("MAR"))
			iMM = 3;
		else if(month.equalsIgnoreCase("APR"))
			iMM = 4;
		else if(month.equalsIgnoreCase("MAY"))
			iMM = 5;
		else if(month.equalsIgnoreCase("JUN"))
			iMM = 6;
		else if(month.equalsIgnoreCase("JUL"))
			iMM = 7;
		else if(month.equalsIgnoreCase("AUG"))
			iMM = 8;
		else if(month.equalsIgnoreCase("SEP"))
			iMM = 9;
		else if(month.equalsIgnoreCase("OCT"))
			iMM = 10;
		else if(month.equalsIgnoreCase("NOV"))
			iMM = 11;
		else if(month.equalsIgnoreCase("DEC"))
			iMM = 12;
		else
			throw new Exception(month + " 알수 없는 식별자입니다.");
		
		int iDD = Integer.parseInt(zTime.substring(0,2));

		int iHH = Integer.parseInt(zTime.substring(2,4));
		int iMI = Integer.parseInt(zTime.substring(4,6));
		int iSS = 0;

		Calendar cal = Calendar.getInstance();
		cal.set(iYYYY, iMM - 1/*JDK 스펙상 1을  빼줘야 한다.*/, iDD, iHH, iMI, iSS);
		return cal.getTime();
    }


}

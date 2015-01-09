/**========================================================
 *@FileName : DateUtil.java
 *
 *@LastModifyDate : 2008. 08. 20
 *@LastModifier : flytaek
 *@LastVersion : 1.0
 *    2008. 08. 20    flytaek    1.0    ���ʻ��� 
 =========================================================*/
package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ����/�ð� ���� ���� ��ƿ��Ƽ
 * <BR>
 * <p>Copyright 2006-2007 the original author or authors.</p>
 *
 * @version 1.0
 * @author flytaek
 */
public class DateUtil
{
    /*************************************************************************************
     * Date�� �������Ŀ� �µ��� ���ڿ��� ��ȯ�� �ش�.
     * @�ۼ��� : flytaek
     * @�ۼ��� : 2008. 07. 15
     * @param date		: Date
     * @param pattern	: ��������(ex, yyyy-MM-dd HH:mm:ss)
     * @return 
     * @�����̷�
     *  1. ...
     */
    public static String getFormatedDate(Date date, String pattern) 
    {
        return new SimpleDateFormat(pattern).format( date );
    }
    
    /*************************************************************************************
     * ����ð��� �������Ŀ� �µ��� ���ڿ��� ��ȯ�� �ش�.
     * @�ۼ��� : flytaek
     * @�ۼ��� : 2009. 02. 04
     * @param pattern
     * @return 
     * @�����̷�
     *  1. ...
     */
    public static String getFormatedCurrentDate(String pattern) 
    {
        return getFormatedDate(Calendar.getInstance().getTime(), pattern);
    }    
    
    /*************************************************************************************
     * ���ڰ����ڿ��� Date��ü�� ��ȯ�Ѵ�.
     * @�ۼ��� : flytaek
     * @�ۼ��� : 2009. 05. 26
     * @param dateValue : '20090301', '2009030112', '200903011201', '20090301120130'
     * @return : ��ȯ�� Date ��ü
     * @�����̷�
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
		cal.set(iYYYY, iMM - 1/*JDK ����� 1��  ����� �Ѵ�.*/, iDD, iHH, iMI, iSS);
		return cal.getTime();
    }
    
    /*************************************************************************************
     * �ٷ�Ÿ�� ���� Date ������ ��ȯ�Ѵ�.
     * @�ۼ��� : flytaek
     * @�ۼ��� : 2009. 08. 21
     * @param zTime : '210556ZAUG2009'
     * @return : ��ȯ�� Date ��ü
     * @throws Exception 
     * @�����̷�
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
			throw new Exception(month + " �˼� ���� �ĺ����Դϴ�.");
		
		int iDD = Integer.parseInt(zTime.substring(0,2));

		int iHH = Integer.parseInt(zTime.substring(2,4));
		int iMI = Integer.parseInt(zTime.substring(4,6));
		int iSS = 0;

		Calendar cal = Calendar.getInstance();
		cal.set(iYYYY, iMM - 1/*JDK ����� 1��  ����� �Ѵ�.*/, iDD, iHH, iMI, iSS);
		return cal.getTime();
    }


}

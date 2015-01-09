/**========================================================
 *@FileName : FileUtil.java
 *
 *@LastModifyDate : 2006-11-17
 *@LastModifier : flytaek
 *@LastVersion : 1.0
 *    2006-11-17    flytaek    1.0    ���ʻ��� 
 =========================================================*/
package utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * ����	 : ������Ʈ ��ü������ ����� �� �ִ� Utility Tool
 * �ۼ��� : flytaek
 * �ۼ��� : 2006-11-17
 *
 */
public class FileUtil
{
	/*************************************************************************************
	 * ���� 	 : ���丮�� '/' ���̱�
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2006. 11. 17
	 * @param _sDirName : ���丮�� ('C:\AQueue', 'C:\AQueue\', 'C:/AQueue/' ���� �Է��� ������)
	 * @return : '/' �� �ٿ��� ���丮��
	 * �����̷�
	 *  1. ...
	 */
	public static String MakeDirSubfix(String _sDirName)
	{
		if( !(_sDirName.endsWith("/") || _sDirName.endsWith("\\")) )
			//return MakeStrongPath(_sDirName) + "/";
			return _sDirName + "/";
		else
			return _sDirName;
	}
	
	/*************************************************************************************
	 * ���� 	 : ���丮�� �ǵ� '/' �����ϱ�
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2006. 12. 19
	 * @param _sDirName : ���丮��
	 * @return : '/' �� ���ŵ� ���丮��
	 * �����̷�
	 *  1. ...
	 */
	public static String RemoveDirSubfix(String _sDirName)
	{
		if( (_sDirName.endsWith("/") || _sDirName.endsWith("\\")) )
			return _sDirName.substring(0, _sDirName.length() - 1);
		else
			return _sDirName;		
	}
	
	/*************************************************************************************
	 * ���� 	 : ���丮���� '\\' �� �Ȱ��� '/' �� �����Ѵ�.
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2006. 12. 19
	 * @param _sDirName	: �ٲ� Path Name
	 * @return : �ٲ� Path Name
	 * �����̷�
	 *  1. ...
	 */
	/*
	public static String MakeStrongPath(String _sDirName)
	{
		return _sDirName.replace('/','\\');
	}*/
	
	/*************************************************************************************
	 * ���� 	 : System�� Temp ������ ���Ѵ�.
	 *         �����ʿ� 
	 *          - ȯ�溯������ Temp ����Path�� Ȯ���ϴ� ���� �ٶ��� �ϴ�.
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2006. 12. 13
	 * @return 
	 * �����̷�
	 *  1. ...
	 * @throws Exception 
	 */
	public static String getSystemTempPath() throws Exception
	{
    	try
		{
			UIManager.setLookAndFeel(UIManager.getLookAndFeel());	
	    	String platform = UIManager.getSystemLookAndFeelClassName();
			
	    	String sTempPath;
	    	
			if (platform.indexOf("window") != -1) 
			{
				sTempPath = "C:/WINDOWS/Temp/";				
			} else 
			{
				sTempPath = "/Temp/";
			}	
			
			return sTempPath;
		}
		catch (UnsupportedLookAndFeelException e)
		{
			throw new Exception("System�� Temp Path�� ���ϱ� ����", e);
		}			
	}
	
	/*************************************************************************************
	 * �ý����� OS�� �Ǵ��Ͽ� EIX ��Ʈ���丮�� ���Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2008. 01. 07
	 * @return
	 * @throws Exception 
	 * @�����̷�
	 *  1. ...
	 */
	public static String getSystemEIXPath() throws Exception
	{
    	try
		{
			UIManager.setLookAndFeel(UIManager.getLookAndFeel());	
	    	String platform = UIManager.getSystemLookAndFeelClassName();
			
	    	String sEixRootPath;
	    	
			if (platform.indexOf("window") != -1) 
			{
				sEixRootPath = "C:/EIX/";				
			} else 
			{
				sEixRootPath = "/EIX/";
			}	
			
			return sEixRootPath;
		}
		catch (UnsupportedLookAndFeelException e)
		{
			throw new Exception("System�� EIX Path�� ���ϱ� ����", e);
		}			
	} 
	
	/*************************************************************************************
	 * ���� 	 : ���ϱ��� ����ִ� ���丮 ���� ����������
	 * �ۼ��� : flytaek
	 * �ۼ��� : 2006. 12. 20
	 * @param dir : ���� ���丮 ��ü
	 * @return : ������ ���ϵ� ũ���� ��
	 * �����̷�
	 *  1. ...
	 */
	public static long deleteDir(File dir) 
	{
		long sizeOfDeletedFiles = 0;
		if (dir.exists() && dir.isDirectory()) 
		{
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) 
			{
				if (files[i].isDirectory()) 
				{
					sizeOfDeletedFiles += deleteDir(files[i]);
					files[i].delete();
				} 
				else 
				{
					sizeOfDeletedFiles += files[i].length();
					files[i].delete();
				}
			}
			dir.delete();
		}
		return sizeOfDeletedFiles;
	}		
	
	/*************************************************************************************
	 * ������ �����Ѵ�.
	 * @�ۼ��� : flytaek
	 * @�ۼ��� : 2009. 02. 26
	 * @param fSource	: �ҽ�����
	 * @param fTarget	: Ÿ������
	 * @throws Exception 
	 * @�����̷�
	 *  1. ...
	 */
	public static void copyFile(File fSource, File fTarget) throws Exception
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		byte [] buf = null;
		
		try
		{
			fis = new FileInputStream(fSource);
			fos = new FileOutputStream(fTarget);
			
			while(fis.available() > 0)
			{
				if(fis.available() > 1024)
					buf = new byte[1024];
				else
					buf = new byte[fis.available()];
					
				fis.read(buf);
				fos.write(buf);
				fos.flush();
			}
		}
		catch (FileNotFoundException e)
		{
			throw new Exception(e);
		}
		catch (IOException e)
		{
			throw new Exception(e);
		}
		finally
		{
			try
			{
				if(fis != null)
				{
					fis.close();
					fis = null;
				}
				
				if(fos != null)
				{
					fos.close();
					fos = null;
				}
			}
			catch(IOException e)
			{
				// Ignore...
			}
		}
	}
}

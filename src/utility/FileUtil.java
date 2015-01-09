/**========================================================
 *@FileName : FileUtil.java
 *
 *@LastModifyDate : 2006-11-17
 *@LastModifier : flytaek
 *@LastVersion : 1.0
 *    2006-11-17    flytaek    1.0    최초생성 
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
 * 설명	 : 프로젝트 전체적으로 사용할 수 있는 Utility Tool
 * 작성자 : flytaek
 * 작성일 : 2006-11-17
 *
 */
public class FileUtil
{
	/*************************************************************************************
	 * 설명 	 : 디랙토리명에 '/' 붙이기
	 * 작성자 : flytaek
	 * 작성일 : 2006. 11. 17
	 * @param _sDirName : 디렉토리명 ('C:\AQueue', 'C:\AQueue\', 'C:/AQueue/' 등의 입력이 가능함)
	 * @return : '/' 이 붙여진 디렉토리명
	 * 수정이력
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
	 * 설명 	 : 디랙토리명에 맨뒤 '/' 제거하기
	 * 작성자 : flytaek
	 * 작성일 : 2006. 12. 19
	 * @param _sDirName : 디렉토리명
	 * @return : '/' 이 제거된 디렉토리명
	 * 수정이력
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
	 * 설명 	 : 디렉토리명이 '\\' 로 된것을 '/' 로 변경한다.
	 * 작성자 : flytaek
	 * 작성일 : 2006. 12. 19
	 * @param _sDirName	: 바꿀 Path Name
	 * @return : 바뀐 Path Name
	 * 수정이력
	 *  1. ...
	 */
	/*
	public static String MakeStrongPath(String _sDirName)
	{
		return _sDirName.replace('/','\\');
	}*/
	
	/*************************************************************************************
	 * 설명 	 : System의 Temp 폴더를 구한다.
	 *         보완필요 
	 *          - 환경변수에서 Temp 폴더Path를 확인하는 것이 바람직 하다.
	 * 작성자 : flytaek
	 * 작성일 : 2006. 12. 13
	 * @return 
	 * 수정이력
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
			throw new Exception("System의 Temp Path를 구하기 실패", e);
		}			
	}
	
	/*************************************************************************************
	 * 시스템의 OS를 판단하여 EIX 루트디렉토리를 구한다.
	 * @작성자 : flytaek
	 * @작성일 : 2008. 01. 07
	 * @return
	 * @throws Exception 
	 * @수정이력
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
			throw new Exception("System의 EIX Path를 구하기 실패", e);
		}			
	} 
	
	/*************************************************************************************
	 * 설명 	 : 파일까지 들어있는 디렉토리 몽땅 지워버리기
	 * 작성자 : flytaek
	 * 작성일 : 2006. 12. 20
	 * @param dir : 지울 디렉토리 객체
	 * @return : 지워진 파일들 크기의 합
	 * 수정이력
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
	 * 파일을 복사한다.
	 * @작성자 : flytaek
	 * @작성일 : 2009. 02. 26
	 * @param fSource	: 소스파일
	 * @param fTarget	: 타깃파일
	 * @throws Exception 
	 * @수정이력
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

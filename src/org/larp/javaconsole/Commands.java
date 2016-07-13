package org.larp.javaconsole;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Commands {
	
	private String directory = System.getProperty("user.dir");
	private String separator = System.getProperty("file.separator");
	
	public void touch (String filename){
		boolean bool = false;
		File file = new File(filename);
		bool = file.exists();
		try {
			if(!bool){
				file.createNewFile();
		} else {
			System.out.println("File " + filename + " already exists. Cannot overwrite.");
		}
			} catch (Exception e){
				e.printStackTrace();
			}
		 }
	public void ls () {
	      File file = null;
	      String[] paths;
	      try{      
	         file = new File(directory);                        
	         paths = file.list();	            
	         for(String path:paths)
	         {
	            System.out.println(path);
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	
	public void echo (String params[]) {
		System.out.println(params);
		}
	
	public void cd (){
		
	}
	
	public void pwd (){
		System.out.println(directory);
	}
	
	public void mkdir(String params){
		boolean bool = false;
		try{
			File newDir = new File(directory + separator + params);
			bool = newDir.exists();
			if (!bool){
			newDir.mkdir();
			} else {
				System.out.println("The directory " + params + " already exists. Cannot overwrite,");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void cat(String params){
		try{
		File file = new File(params);
		boolean bool = file.exists();
		if (bool){
		FileReader fr = new FileReader(file); 
	    char [] a = new char[50];
	      fr.read(a); 
	      for(char c : a)
	          System.out.print(c);
	      fr.close();
		} else {
			System.out.println("File doesn't exists");
		}
		} catch (Exception e){
			
		}
	}
	
	public void cp(){
		
	}
	
	public void mv(){
		
	}
	
	public void rm(){
		
	}
}
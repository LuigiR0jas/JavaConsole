package org.larp.javaconsole;
import java.io.*;

public class Commands {
	
	private String separator = System.getProperty("file.separator");
	private String home = System.getProperty("user.home");
	private String OS = System.getProperty("os.name");
	private String OSversion = System.getProperty("os.version");
	
	
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
	         file = new File(System.getProperty("user.dir"));                        
	         paths = file.list();	            
	         for(String path:paths)
	         {
	            System.out.println(path);
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	
	public void echo (String params) {
		System.out.println(params);
		}
	
	public void echo (String params1, String params2) {
		File file = new File(params2);
		Boolean bool = file.exists();
		try {
			if(!bool) file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(params1);
			bw.flush();
			bw.close();
	} catch (Exception e){
		e.printStackTrace();
		}
	}
	
	public void cd (String params){
		switch(params){
		case "cd":
		    System.setProperty("user.dir", home);
			break;
		case " ":
			System.out.println(home);
			System.setProperty("user.dir", home);
			break;
		case ".":
			break;
		case "..":
			if (System.getProperty("user.dir") == "/"){                // <-----PREGUNTAR!
				break;
			} else {
				File currentDir = new File(System.getProperty("user.dir"));
				System.setProperty("user.dir", currentDir.getParent());
			}
			break;
		default:
			File dir = new File(System.getProperty("user.dir") + separator + params);
			Boolean bool = dir.isDirectory();
			Boolean bool2 = dir.isFile();
			String dir2 = dir.toString();
			if (bool){
				System.setProperty("user.dir", dir2);
			} else if(bool2) {
				System.out.println("-bash: cd: " +  params + ": " + " Not a directory");
			} else {
				System.out.println("-bash: cd: " + params +  ": " + " No such file or directory");
			}
			break; 
		} 
	}
	
	public void pwd (){
		System.out.println(System.getProperty("user.dir"));
	}
	
	public void mkdir(String params){
		boolean bool = false;
		try{
			File newDir = new File(System.getProperty("user.dir")+ separator + params);
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
		File file = new File(params);
		boolean bool = file.exists();
		System.out.println(bool);
		
		try{
		if (bool){
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
	    String line = br.readLine();
	    while(line != null){
	    	System.out.println(line);
	    	line = br.readLine();
	    }
		} else {
			System.out.println(params + " is not a file or does not exist");
		}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void cp(){
		
	}
	
	public void mv(String params1, String params2){
		try{
			
	    	   File file =new File(System.getProperty("user.dir") + separator + params1);
	    	   File file2 =new File(params2);
	    	   Boolean bool1 = file.exists();
	    	   Boolean bool2 = file2.exists();
	    		if (bool1 && bool2){
	    			 file.renameTo(new File(file2 + separator + file.getName()));
	    		} else if(bool1 && !bool2) {
	    			System.out.println("The given path does not exist");
	    		} else if (!bool1 && bool2){
	    			System.out.println("The given file does not exist");
	    		} else {
	    			System.out.println("The given path nor the given file does not exist");
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	}
	
	public void rm(String params){
		try{
			File file = new File(System.getProperty("user.dir") + separator+ params);
			Boolean bool = file.exists();
			if (bool){
				file.delete();
			} else{
				System.out.println("rm: cannot remove " + params +": No such file or directory");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void whoami(){
		System.out.println(System.getProperty("user.name"));
	}
	
	public void help(String params){
		System.out.println("LuigiR0jas_'s JavaConsole GNU bash emulator, version 1.0 for " + OS + OSversion);
		System.out.println("\nThese shell commands are defined internally.  Type 'help' to see this list.");
		System.out.println("\nType 'help name' to find out more about the function 'name'");
	}
}
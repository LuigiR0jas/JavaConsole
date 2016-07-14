package org.larp.JavaConsoleLast;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;

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
            System.out.println("MY PARAMS ARE" + params);
		File file = new File(System.getProperty("user.dir") + separator + params);
                System.out.println(file);
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
	
	public void cp(Path source, Path dest)throws IOException {
		Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES);
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
	    			System.out.println("mv: cannot stat: The given path does not exist");
	    		} else if (!bool1 && bool2){
	    			System.out.println("mv: cannot stat: The given file does not exist");
	    		} else {
	    			System.out.println("mv: cannot stat: The given path nor the given file does not exist");
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
		switch(params){
		case "help":
			System.out.println("LuigiR0jas's JavaConsole GNU bash emulator, version 1.0 for " + OS + OSversion);
			System.out.println("These shell commands are defined internally.  Type 'help' to see this list.");
			System.out.println("Type 'help name' to find out more about the function 'name'");	
			System.out.println("Commands available: \n\t\thelp\t\techo\t\tcat\n\t\tls\t\tpwd\t\tcd\n\t\tcp\t\trm\t\tmv\n\t\twhoami\t\tmkdir\t\ttouch");
			break;
		case "echo":
			System.out.println("echo: echo [args] [>>] [destination file]\n		Write arguments to the standard output, or pipes them if defined to a provided destination file.");
			break;
		case "cat":
			System.out.println("cat: cat [file]\n	Print on the standard output");
			break;
		case "ls":
			System.out.println("ls: ls\n	List directory contents");
			break;
		case "pwd":
			System.out.println("pwd: pwd\n	Print the name of the current working directory.");
			break;
		case "cd":
			System.out.println("cd: cd [dir]\n	'..' is processed by removing the immediately previous pathname component back to a slash or the beginning of DIR.");
			break;	
		case "cp":
			System.out.println("cp: cp [file] [absolute path of destination]\n	Copy files to a provided directory");
			break;
		case "rm":
			System.out.println("rm: rm [file]\n		Remove file");
			break;
		case "mv":
			System.out.println("mv: mv [file] [absolute path of destination]\n	Move (rename) files");
			break;
		case "whoami":
			System.out.println("whoami: whoami\n	Print effective userid");
			break;
		case "mkdir":
			System.out.println("mkdir: mkdir [dir]\n	Creates directory in current working directory.");
			break;
		case "touch":
			System.out.println("touch: touch [file]\n	Creates file in current working directory.");
			break;
		default:
			System.out.println(params + ": command not found");
			break;
		}
	
	}
}
package org.larp.javaconsole;

import java.nio.file.Files;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


	public class Console {
		
		private Scanner input = new Scanner (System.in);
		private Commands cmds = new Commands();
		private String username = System.getProperty("user.name");

		
		public void start () throws IOException {
			cmds.cd("cd");
			while (true) {
				System.out.print(username + "@" + System.getProperty("user.dir") + "$ ");
				String CLI = input.nextLine();
				String params [] = CLI.split(" ", 2);
				switch (params[0]) {
					case "touch":
						if (params.length <= 1){
							System.out.println("You must especify a file to create");
							break;
						}
						cmds.touch(params[1]);
						break;						
					case "echo":
						String arrayParams [] = params[1].split(">> ", 2);
						if (arrayParams.length == 1){
							cmds.echo(params[1]);
						} else {
							cmds.echo(arrayParams[0], arrayParams[1]);
						} 
						break;																		
					case "ls":
						cmds.ls();
						break;												
					case "pwd":
						cmds.pwd();
						break;											
					case "mkdir":
						if(params.length <= 1){
							System.out.println("You must especify a directory to create");
							break;
						} else {
						cmds.mkdir(params[1]);
						break;
						}												
					case "cat":
						if (params.length <= 1){
							System.out.println("You must especify a file to read");
							break;
						} else {
						cmds.cat(params[1]);
						break;	
						}												
					case "cd":
						if(params.length == 1){
							cmds.cd(params[0]);
							break;
						} else {
							cmds.cd(params[1]);
							break;
						}						
					case "whoami":
						cmds.whoami();
						break;
					case "help":
						cmds.help(params[1]);
						break;
					case "mv":
						String arrayParams2 [] = params[1].split(" ", 2);
						if (arrayParams2.length == 1){
							System.out.println("You must especify correct arguments");
						} else {
							cmds.mv(arrayParams2[0], arrayParams2[1]);
						} 
						break;			
					case "cp":
						String arrayParams3 [] = params[1].split(" ", 2);
						if (arrayParams3.length == 1){
							System.out.println("You must especify correct arguments");
						} else {
							Path source = Paths.get(arrayParams3[0]);
                                                        Path dest = Paths.get(arrayParams3[1]);
							cmds.cp(source, dest);
						} 
						break;				
						
					
					
					
					
						
						
						
					}
				}
			}	
		}

package org.larp.javaconsole;

import java.util.Scanner;

	public class Console {
		
		private Scanner input = new Scanner (System.in);
		private Commands cmds = new Commands();
		private String username = System.getProperty("user.name");
		private String directory = System.getProperty("user.dir");

		
		public void start () {
			
			while (true) {
				System.out.print(username + "@" + directory + "$ ");
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
							cmds.echoat(arrayParams[0], arrayParams[1]);
						} break;
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
				}
			}
			
		}
		
	}

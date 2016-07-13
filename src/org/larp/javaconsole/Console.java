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
				String params [] = CLI.split(" ");
				
				switch (params[0]) {
					case "touch":
						if (params.length <= 1) break;
						cmds.touch(params[1]);
						break;
					case "echo":
						if (params.length <= 1) break;
						cmds.echo(params[1]);
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
				}
			}
			
		}
		
	}

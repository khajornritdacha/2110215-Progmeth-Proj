package application;

import java.util.Scanner;

//TODO: implement this class
public class Main {
	private static Scanner keyBoard;

	public static void main(String[] args) {		
		keyBoard = new Scanner(System.in);
		

	}

	public static int getChoice() {
		System.out.print(">> ");
		String input = keyBoard.nextLine();
		try{
			return Integer.parseInt(input);
		}catch(NumberFormatException e){
            return -1;
        }
	}
	
	public static void startGame() {
		
	}
}

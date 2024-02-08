
import java.util.Scanner;

public class Menu {
	private static int userIn;
	private static Scanner scan = new Scanner(System.in);
	private static boolean isValid = false;
	
	public static String poly1, poly2;
	/**
	 * "start" menu
	 * displays initial options
	 */
	public static void Start() {
		//output header and options
		System.out.println("Add Two Polynomials\n\nSelect Menu Option\n");
		System.out.println("1) Enter first polynomial\n2) Enter second polynomial\n3) Add polynomials\n4) Display result\n");
		
		//collect user input
		System.out.print("Choice: ");
		while(!isValid) {
			if(scan.hasNextInt()) { //ensure int
				userIn = Integer.parseInt(scan.nextLine());
				if(userIn <= 4 && userIn > 0) { 
					//determine user choice
					if(userIn == 1) {
						System.out.print("Enter 1st polynomial: ");
						Menu.EnterFirstPoly();
						isValid = true;
					}
					else if(userIn == 2) {
						System.out.print("Enter 2nd polynomial: ");
						Menu.EnterSecondPoly();
						isValid = true;
					}
					else if(userIn == 3) {
						System.out.print("Add polynomials: ");
						Menu.AddPoly();
						isValid = true;
					}
					else if(userIn == 4) {
						System.out.print("Display result: ");
						Menu.DisplayResult();
						isValid = true;
					}
				}
				else {
					System.out.println("Invalid input, please try again.");
					System.out.print("Choice: ");
				}
			}
			else {
				System.out.println("Invalid input, please try again.");
				System.out.print("Choice: ");
			}
			
		}
		
	}
	public static void EnterFirstPoly() {
		poly1 = scan.nextLine();
		//FIXME: call polynomial constructor, add return to menu
		
	}
	public static void EnterSecondPoly() {
		poly2 = scan.nextLine();
		//FIXME: call polynomial constructor, add return to menu
	}
	public static void AddPoly() {
		//FIXME: call addPolynomial, add return to menu, possibly have method return polynomial to main
	}
	public static void DisplayResult() {
		//FIXME: display result of addPolynomial, add option for a new set of polynomials and return to menu

	}
}


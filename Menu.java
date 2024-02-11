import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(in);
    String userIn = "";
    Polynomial poly1, poly2;

    public Menu() throws IOException {

        while (!userIn.equalsIgnoreCase("q")) {

            System.out.println("Add 2 Polynomials Program");
            System.out.println("Select Menu Option\n");
            System.out.println("Enter '1' to enter first polynomial to be added.\n");
            System.out.println("Enter '2' to enter second polynomial to be added.\n");
            System.out.println("Enter '3' to add inputted polynomials and display result.\n");
            System.out.println("Enter 'Q' or 'q' to exit program.");

            userIn = reader.readLine();

            switch (userIn) {
                case "1":
                    System.out.println("Please enter polynomial with no spaces and using a caret '^' to denote exponents.");
                    poly1 = new Polynomial(reader.readLine());
                    break;
                case "2":
                    System.out.println("Please enter polynomial with no spaces and using a caret '^' to denote exponents.");
                    poly2 = new Polynomial(reader.readLine());
                    break;
                case "3":
                    if (poly1 != null && poly2 != null)
                        System.out.println("Result of " + poly1 + "+ " + poly2 + "= " + poly1.addPolynomial(poly2) + "\n");
                    else
                        System.out.println("Please enter both polynomials first.");
                    break;
                case "q":
                case "Q":
                    System.out.println("Ending Program");
                    return;
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }

    public static void start() throws IOException {
        new Menu();
    }
}

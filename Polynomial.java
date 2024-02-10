import java.util.ArrayList;

public class Polynomial {

	private Linked_List<Term> poly;

	// Default constructor

	public Polynomial() {
		poly = new Linked_List<>();
	}

	public Polynomial(Linked_List<Term> other) {
		this.poly = other;
	}

	/**
	 * Constructor that parses input into terms and creates linked list of terms
	 * from input polynomial and sorts and combines terms of new polynomial
	 * 
	 * @param input: polynomial inputed as string
	 */

	public Polynomial(String input) {
		poly = new Linked_List<Term>();
		Linked_List<Character> data = new Linked_List<>();
		for (int i = 0; i < input.length(); i++) {
			data.addLast(input.charAt(i));
		}
		List_Iterator<Character> it = data.iterator();
		while (it.hasNext()) {
			int coefficient = 0;
			int exponent = 0;
			char sign = '+';
			String variable = "";

			char currentChar = it.next();

			if (currentChar == '+' || currentChar == '-') {
				sign = currentChar;
			} else {
				it.previous(); // Move iterator back if first character was an unsigned number or variable
			}

			if (it.hasNext() && Character.isDigit(it.peek())) { // Check for Coefficient
				String temp = "";
				temp += it.next();
				if (it.hasNext() && Character.isDigit(it.peek())) { // Check if there is a second digit
					temp += it.next();
				}
				if (sign == '-') {
					coefficient = Integer.parseInt(temp) * -1;
				} else {
					coefficient = Integer.parseInt(temp);
				}
			}

			if (it.hasNext() && Character.isAlphabetic(it.peek())) { // Check for variable
				variable = sign + it.next().toString();
			}

			if (it.hasNext() && it.peek() == '^') { // Check for exponent
				it.next();
				String temp = "";
				if (it.hasNext() && it.peek() == '-') { // Check for negative exponent and if it has second digit
					it.next();
					temp = it.next().toString();
					if (it.hasNext() && Character.isDigit(it.peek())) {
						temp += it.next();
					}
					exponent = Integer.parseInt(temp) * -1;
				} else if (it.hasNext()) { // Check for positive exponent and if it has second digit
					temp = it.next().toString();

					if (it.hasNext() && Character.isDigit(it.peek())) {
						temp += it.next();
					}
					if (!temp.isEmpty()) {
						exponent = Integer.parseInt(temp);
					}
				}
			}
			if (coefficient == 0 && variable.contains("x") && sign == '+') { // Handles variables with no coefficient
																				// and properly give them a coefficient
																				// of 1
				coefficient = 1;
			} else if (coefficient == 0 && variable.contains("x") && sign == '-') {
				coefficient = -1;
			}
			poly.addLast(new Term(coefficient, exponent, variable));
		}
		this.sort(); // Sort and combine terms of input polynomial
		this.combineTerms();
	}

	/**
	 * Adds 2 polynomials and returns the result
	 * 
	 * @param other: Second polynomial to add
	 * @return: New polynomial from result of the addition
	 */

	public Polynomial addPolynomial(Polynomial other) {
		Linked_List<Term> newPoly = new Linked_List<>(this.poly);
		List_Iterator<Term> it = other.poly.iterator();

		while (it.hasNext()) {
			newPoly.addLast(it.next());
		}

		Polynomial poly = new Polynomial(newPoly);

		poly.sort();
		poly.combineTerms();

		return poly;
	}

	/**
	 * Sort terms of polynomial in descending order based on exponent
	 */

	public void sort() {
		Linked_List<Term> sortedPoly = new Linked_List<>();

		while (!poly.isEmpty()) {
			List_Iterator<Term> it = poly.iterator();
			Term current = it.next();
			Term maxTerm = current;

			// Find the maximum term
			while (it.hasNext()) {
				Term nextTerm = it.peek();
				if (nextTerm.compareTo(maxTerm) > 0) {
					maxTerm = nextTerm;
				}
				it.next();
			}

			// Remove the maximum term from the original list and add it to the sorted list
			it = poly.iterator(); // Reset the iterator to the beginning of the list
			while (it.hasNext()) {
				Term nextTerm = it.next();
				if (nextTerm.compareTo(maxTerm) == 0) {
					it.removePrevious();
					sortedPoly.addLast(maxTerm);
					break;
				}
			}
		}

		this.poly = sortedPoly;
	}

	/**
	 * Combines terms of polynomial with same exponents
	 */

	public void combineTerms() {
	    Linked_List<Term> newPoly = new Linked_List<>();
	    List_Iterator<Term> it = poly.iterator();
	    
	    while (it.hasNext()) {
	        Term currentTerm = it.next();
	        int coefficient = currentTerm.getCoefficient();
	        int exponent = currentTerm.getExponent();
	        String var = currentTerm.getVar();
	        
	        
	        List_Iterator<Term> it2 = poly.iterator();     // Search for like terms
	        while (it2.hasNext()) {
	            Term nextTerm = it2.next();
	            if (nextTerm != currentTerm && nextTerm.getVar().equals(var) && nextTerm.getExponent() == exponent) {  // If similar term found add coefficients
	                coefficient += nextTerm.getCoefficient();
	                it2.removePrevious(); // Remove the nextTerm from the original list
	            }
	            else if (nextTerm != currentTerm && nextTerm.getVar().contains("x") && var.contains("x") && nextTerm.getExponent() == exponent ) { // Combine terms that are variables without exponents
	            	coefficient += nextTerm.getCoefficient();
	                it2.removePrevious();         // Remove the nextTerm from list
	            }
	        }
	        it.removePrevious();   // Removes term previous term from list 
	        
	       if (coefficient == 0 ) {   // Check to not add terms with coefficient of 0
	    	   continue;
	       }
	       else {
	        newPoly.addLast(new Term(coefficient, exponent, var));
	       }
	    }
	    
	    poly = newPoly;
	}



	/**
	 * Converts polynomial stored in linked list into a String for output
	 * 
	 * @return: polynomial to string
	 */

	@Override
	public String toString() {
		Iterator<Term> it = poly.iterator();
		StringBuilder builder = new StringBuilder();
		while (it.hasNext()) {
			builder.append("(" + it.next().toString() + ")" + " "); // Remove additional space and parenthesis before
																	// submitting just
																	// used to show the separate between terms
																	// Leading '+' sign will be removed correctly when
																	// parenthesis are removed

		}
		if (builder.charAt(0) == '+') { // Removes leading '+' sign if first term is positive
			builder.deleteCharAt(0);
		}

		return builder.toString();

	}
}

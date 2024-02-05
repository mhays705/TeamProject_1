
public class Polynomial {

	private Linked_List<Term> poly;

	// Default constructor

	public Polynomial() {
		poly = null;
	}

	// Constructor that parses input into terms and creates linked list of terms
	// from inputed polynomial
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
			poly.addLast(new Term(coefficient, exponent, variable));
		}
	}

	/**
	 * Adds 2 polynomials and returns the result
	 * 
	 * @param other: Second polynomial to add
	 * @return: New polynomial from result of the addition
	 *
	 *          public Polynomial addPolynomial(Polynomial other) { Iterator<Term>
	 *          it1 = this.poly.iterator(); Iterator<Term> it2 =
	 *          other.poly.iterator(); Linked_List<Term> newPoly = new
	 *          Linked_List<>();
	 * 
	 * 
	 *          return newPoly;
	 * 
	 *          }
	 */

	
	
	
	/**
	 * Converts polynomial stored in linked list into a String for output
	 * 
	 * @return: polynomial converted to string
	 */

	@Override
	public String toString() {
		Iterator<Term> it = poly.iterator();
		StringBuilder builder = new StringBuilder();
		while (it.hasNext()) {
			builder.append("(" + it.next().toString() + ")" + " ");     // Remove additional space before submitting just used to show the separate between terms 
		}
		if (builder.charAt(0) == '+') { // Removes
			builder.deleteCharAt(0);
		}

		return builder.toString();

	}
}

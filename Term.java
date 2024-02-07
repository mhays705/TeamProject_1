
public class Term implements Comparable<Term> {

	private int coefficient; // Stores coefficient of term
	private int exponent; // Stores exponent of term if present
	private String var; // Stores variable of term if present
	// Default Constructor

	public Term() {
	}

	public Term(int coefficient, int exponent, String var) {
		this.coefficient = coefficient;
		this.exponent = exponent;
		this.var = var;

	}

	// Getters

	public int getCoefficient() {
		return coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public String getVar() {
		return var;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (coefficient > 0) {
			builder.append("+"); // Always add a leading '+' for positive coefficients
		} else if (coefficient < 0) {
			builder.append("-"); // Handle negative coefficient
		}

		if (coefficient != 0) {
			builder.append(Math.abs(coefficient));
		}

		if (!var.isEmpty() && coefficient != 0) { // Removes sign from variable with coefficient
			builder.append(var.substring(1));
		} else if (!var.isEmpty() && coefficient == 0) {
			builder.append(var);
		}

		if (exponent != 0) {
			builder.append("^" + exponent);
		}

		return builder.toString();
	}

	@Override
	public int compareTo(Term other) {
		
		if (this.exponent > other.exponent) {
			return 1;
		}
		else if (this.exponent < other.exponent) {
			return -1;
					
		}
		
		return 0;   // If exponents are the same
		
		
		
	}
}

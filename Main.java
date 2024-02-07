
public class Main {

	public static void main(String[] args) {
      

		Polynomial poly1 = new Polynomial("5x-5x^-2+10-5x+x^2");
		Polynomial poly2 = new Polynomial("-x+5+x^2-10");
		Polynomial sumPoly;
		
        System.out.println(poly1.toString());
        System.out.println(poly2.toString());
        sumPoly = poly1.addPolynomial(poly2);
        
        System.out.println(sumPoly.toString());
        

	}

}

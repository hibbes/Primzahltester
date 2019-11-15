public class Probedivision {

	
	static long maxnumber = 100000;
	static boolean isPrime = true;
	
	public static void main(String[] args) {
		final long timeStart = System.currentTimeMillis();
	 for(long number = 2; number<=maxnumber; number++){
		isPrime = true;
		for(int i = 2; i<=number/2 && isPrime; i++){
			if(number%i==0){
				isPrime=false;
			}
		}
     
		 if(isPrime){
			 System.out.println(number);
		 }
		 
        }
	 final long timeEnd = System.currentTimeMillis();
	    System.out.println("Laufzeit: " + (timeEnd - timeStart) + " Millisek.");
	 
	 }
}
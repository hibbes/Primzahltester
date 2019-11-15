import java.util.Arrays;


public class Erastothenes {

	static int max = 100000;
	
	public static void main(String[] args){
		long timeprobe;
		long timeerasto;
		long timeStart = System.currentTimeMillis();
		boolean[] primzahlen = probedivision(erzeugeArray(max));
		long timeEnd = System.currentTimeMillis();
		timeprobe = timeEnd - timeStart;
		timeStart = System.currentTimeMillis();
		primzahlen = erastothenes(erzeugeArray(max));
		timeEnd = System.currentTimeMillis();
		timeerasto = timeEnd - timeStart;
		
		
		System.out.println("Laufzeit Erastothenes: " + (timeerasto) + " Millisek.");
		System.out.println("Laufzeit Probedivision: " + (timeprobe) + " Millisek.");
		
	}
	
	private static boolean[] erastothenes(boolean[] primzahlen){
		
		  for (int i = 2; i <= max; i++) {
	            int momentanerWert = i; // Anfangs Wert von i, später Vielfaches von i
	            if (primzahlen[momentanerWert]) {
	                momentanerWert += i;
	                
	                while (momentanerWert <= max) {
	                    primzahlen[momentanerWert] = false;
	                    momentanerWert += i; // Momentanen Wert um i erhöhen
	                }
	            }
	        }
		    
		    return primzahlen;
		    
		 }
	private static boolean[] probedivision (boolean[] primzahlen){
	
		for(int number = 2; number<=max; number++){
			
			for(int i = 2; i<=number/2 && primzahlen[number]; i++){
				
				if(number%i==0){
					
					primzahlen[number]=false;
				}
			
		   }
		}
			return primzahlen;
	}
		   
	private static void ausgabe(boolean[]primzahlen){
		for (int i=1; i<=max; i++){
			if(primzahlen[i]){
				System.out.println(i);
			}
		}
	}
	      //  Arrays.fill(primzahlen, Boolean.TRUE); 
		
	 
		
		
		   
		private static boolean[] erzeugeArray(int max){
			boolean[] primzahlen = new boolean[max+1];
			Arrays.fill(primzahlen, Boolean.TRUE); 
			primzahlen[0]=false;
			primzahlen[1]=false;
			
			return primzahlen;
		}
		   
		    
		  
	
}

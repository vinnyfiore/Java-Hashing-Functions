/**
 *@author Vincent Fiore
 *This program is an implementation of a SHA-512 Hash that attempts to find the time it takes to get a collision. 
 *
 *Note: This program uses a method found online to properly convert the byte array after being
 * decrypted using the built in Java methods.
 * This code can be found at https://community.oracle.com/message/8677690 and did not include the formal name of
 * any creator or licensing information and is used in lieu of similar code provided directly by Apache for conversion to hex.
 *
 *Created for CUS 1185 homework assignment 5 question 5.4.
 */

import java.security.*;
import java.util.Random;

public class Hash3 {
	
	

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		byte testInput1[] = new byte[] {(byte) 0x3D, (byte) 0x4B};
		//byte testInput1[] = new byte[] {(byte) 0x3A, (byte) 0x7F, (byte) 0x27}; //Creates the array to test against.
		//byte testInput1[] = new byte[] {(byte) 0xC3, (byte) 0xC0, (byte) 0x35, (byte) 0x7C};
		
        MessageDigest mDigestFunc = MessageDigest.getInstance("SHA-512"); //Initializes the Digest
        
        int i =0;
        long startTime = System.nanoTime();
        System.out.println("Started");//Starts the timer.
        int count=0;
        while(!(i==1)){
        	byte[] byteArray1 = new byte[2];
            new Random().nextBytes(byteArray1); //These two initialize a random byte array on every run.
            
            byte[] newHash = mDigestFunc.digest(byteArray1); //This performs the hash function on the random byte array on every run.
            
            //System.out.println(asHex(testInput1)); //Prints the array that we are testing against for debugging.
            //System.out.println(asHex(newHash)); //Prints the hashed message for debugging purposes.
            //System.out.println("");
            String testInAsString = asHex(testInput1);
            String resultNewHash = asHex(newHash);
            resultNewHash = resultNewHash.substring(0,4);
            //System.out.println(testInAsString);
            //System.out.println(resultNewHash);
            //System.out.println("");
            count++;
            if (count%1000000==0){
            System.out.println(count/1000000 + " million");
            }

            
            if (testInAsString.equals(resultNewHash)){
            	System.out.println("Found it");
                System.out.println(asHex(byteArray1));
            	i++; //Ends the while loop if a match is found.
            }
            

        }
        long endTime = System.nanoTime(); //Ends the timer.
 
        long duration = (endTime - startTime); //Calculates the time.
        System.out.println(duration/1000000 + " milliseconds");  //Prints the time in milliseconds.
        



	}
	public static String asHex (byte buffer[]) {
	   	 //This method takes the byte array that encryption produces and converts it into a hex string to be printed. 
		      StringBuffer strbuf = new StringBuffer(buffer.length * 2);
		      //Creates a string buffer with twice the length of the byte array to hold the converted info.
		      int i;
		      for (i = 0; i < buffer.length; i++) {
		       if (((int) buffer[i] & 0xff) < 0x10)
			    strbuf.append("0");
		       strbuf.append(Long.toString((int) buffer[i] & 0xff, 16));
		       //Does the actual conversion on every byte in the array.
		      }
		      return strbuf.toString();
		      //Returns the array as a string to be printed.
		     }
}	
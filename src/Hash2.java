/**
 *@author Vincent Fiore
 *This program is an implementation of a SHA-512 Hash that attempts to find the time it takes to get a collision. 
 *
 *Note: This program uses a method found online to properly convert the byte array to hex strings for printing.
 * This code can be found at https://community.oracle.com/message/8677690 and did not include the formal name of
 * any creator or licensing information. It is used in lieu of similar code provided directly by Apache for conversion to hex.
 *
 *Created for CUS 1185 homework assignment 5 question 5.4.
 */

import java.security.*;
import java.util.Random;

public class Hash2 {
	
	

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		byte testInput1[] = new byte[] {(byte) 0xA9}; //Creates the array to test against.
		//byte testInput1[] = new byte[] {(byte) 0x3D, (byte) 0x4B};
		//byte testInput1[] = new byte[] {(byte) 0x3A, (byte) 0x7F, (byte) 0x27}; 
		//byte testInput1[] = new byte[] {(byte) 0xC3, (byte) 0xC0, (byte) 0x35, (byte) 0x7C}; //These commented out arrays are the tests for the other numbers.

		MessageDigest mDigestFunc = MessageDigest.getInstance("SHA-512"); //Initializes the Digest
        
        int i =0;
        long startTime = System.nanoTime(); //Starts the timer.
        while(!(i==1)){
        	byte[] byteArray1 = new byte[1]; //This array size changes when the size of the test changes. IE: 2 for bytes 3D 4B.
            new Random().nextBytes(byteArray1); //These two initialize a random byte array on every run.
            
            byte[] newHash = mDigestFunc.digest(byteArray1); //This performs the hash function on the random byte array on every run.
            
            //System.out.println(asHex(testInput1)); //Prints the array that we are testing against for debugging.
            //System.out.println(asHex(newHash)); //Prints the hashed message for debugging purposes.
            String testInAsString = asHex(testInput1); //Creates a string of given values.
            String resultNewHash = asHex(newHash); //Creates a string of the hash result.
            
            resultNewHash = resultNewHash.substring(0,2); //Truncates the resulting hash to the correct size.
            //This substring changes when the size of the test changes. IE: 2 becomes 4 for 3D 4B.
            
            System.out.println(testInAsString); //Prints out the given values.
            System.out.println(resultNewHash); //Prints out the given values. 
            System.out.println(""); 

            
            if (testInAsString.equals(resultNewHash)){
            	i++; //Ends the while loop if a match is found.
            	System.out.println("Found it");
                System.out.println(asHex(byteArray1)); //Prints out the message that we are looking for.
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
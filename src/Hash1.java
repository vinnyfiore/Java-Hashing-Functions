/**
 *@author Vincent Fiore
 *This program is an implementation of a SHA-512 Hash.
 *
 *Note: This program uses a method found online to properly convert the byte array to hex strings for printing.
 * This code can be found at https://community.oracle.com/message/8677690 and did not include the formal name of
 * any creator or licensing information. It is used in lieu of similar code provided directly by Apache for conversion to hex.
 *
 *Created for CUS 1185 homework assignment 5 question 5.2.
 */

import java.security.*;

public class Hash1 {
	
	

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
        MessageDigest mDigestFunc = MessageDigest.getInstance("SHA-512");
   	 	byte rawInput[] = new byte[] {(byte) 0x48, (byte)0x65, (byte)0x6C, (byte)0x6C, (byte)0x6F, (byte)0x2C, (byte)0x20, (byte)0x77, (byte)0x6F, 
				(byte)0x72, (byte)0x6C, (byte)0x64, (byte)0x2E, (byte)0x20, (byte)0x20, (byte)0x20};
		
	    byte[] newHash = mDigestFunc.digest(rawInput);

        System.out.println(asHex(newHash).toUpperCase());


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

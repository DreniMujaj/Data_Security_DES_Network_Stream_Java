import java.io.BufferedReader;
import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.crypto.*;
import  java.security.*;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class Client {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    	gen_key();
     	byte[] r;     
    	Key k=getkey("C:\\Users\\mujaj\\OneDrive\\Desktop\\key.txt");
    	r = encrypt("drenimujaj",k);
    	String rBase64 = Base64.getEncoder().encodeToString(r);
  
       Socket socket = new Socket("127.0.0.1", 3006);
       DataOutputStream os=new  DataOutputStream(socket.getOutputStream());
       os.writeUTF(rBase64);
    }

    public static Key getkey(String filename) throws FileNotFoundException, IOException{
        InputStream is=new FileInputStream(filename);
        BufferedReader buffer=new BufferedReader(new InputStreamReader(is));
		 String line=buffer.readLine();
		 StringBuilder sb=new StringBuilder();
		while(line!=null)
		{
		sb.append(line);
		line=buffer.readLine();
		}
		String fileAsString=sb.toString();
		byte[] decodedkey=Base64.getDecoder().decode(fileAsString);
		SecretKey originkey=new SecretKeySpec(decodedkey, 0,decodedkey.length,"DES");
		
		  return originkey;
		 
    }
	
    public static void gen_key() throws NoSuchAlgorithmException{
    KeyGenerator kg=KeyGenerator.getInstance("DES");
    Key k=kg.generateKey();
    String encodedKey=Base64.getEncoder().encodeToString(k.getEncoded());
  try {
     FileWriter myWriter = new FileWriter("C:\\Users\\mujaj\\OneDrive\\Desktop\\key.txt");
     myWriter.write(encodedKey);

     myWriter.close();
     System.out.println("Successfully wrote  key to the file.");
   } catch (IOException e) {
     System.out.println("An error occurred.");
     e.printStackTrace();
   }   

}
 public static byte [] encrypt(String plain,Key k ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {        
        Cipher cipher = Cipher.getInstance("DES"); 
       cipher.init(Cipher.ENCRYPT_MODE, k); 
        byte[] data = plain.getBytes(); 
       byte[] result = cipher.doFinal(data); 
      return result;
    } 
}
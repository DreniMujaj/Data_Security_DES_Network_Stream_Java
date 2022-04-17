import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.crypto.*;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;

import client.com.Client;

public class Server {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try (ServerSocket listener = new ServerSocket(3005)) {
            System.out.println("The date server is running...");
            while (true) {

                try (Socket socket = listener.accept()) {
                    Key k = Client.getkey("C:\Users\Lenovo\Desktop\key.txt");

                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    String x = in.readUTF();

                    System.out.println("Key: "+k);
                    System.out.println("Encrypted: "+x);
                    System.out.println("Decrypted: "+Decrypt(x, k));

                }

        }
    }

}

public static String Decrypt(String cipherText, Key k) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, k);
  
return new String (cipher.doFinal(Base64.getDecoder().decode(cipherText)),"UTF-8");
}

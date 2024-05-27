package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncDec {

  public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(256);
    return keyGenerator.generateKey();
  }

  public static String encrypt(String plaintext, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);

    byte[] plainTextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
    byte[] encryptedBytes = cipher.doFinal(plainTextBytes);

    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  public static String decrypt(String encryptedData, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter plaintext: ");
    String plaintext = scanner.nextLine();

    SecretKey key = generateAESKey();
    String keyString = Base64.getEncoder().encodeToString(key.getEncoded());
    System.out.println("Generated Key: " + keyString);

    String encryptedText = encrypt(plaintext, key);
    System.out.println("Encrypted: " + encryptedText);

    String decryptedText = decrypt(encryptedText, key);
    System.out.println("Decrypted: " + decryptedText);

    scanner.close();
  }
}

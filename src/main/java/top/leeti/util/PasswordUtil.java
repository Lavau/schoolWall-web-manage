package top.leeti.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {

    public static String encrypt(String username, String password){
        String hashAlgorithmName = "MD5";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleHash hash = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        return hash.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("lavau", "a"));
    }
}

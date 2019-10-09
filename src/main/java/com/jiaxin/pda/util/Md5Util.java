package com.jiaxin.pda.util;

import com.jiaxin.pda.util.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/4/13.
 */
public class Md5Util  extends SimpleHash {

    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    public static final String ALGORITHM_NAME = "MD5";

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String MD5Encode(String origin) throws NoSuchAlgorithmException {
        String resultString = new String(origin);
        MessageDigest md = MessageDigest.getInstance("MD5");
        resultString = byteArrayToHexString(md.digest(resultString
                .getBytes()));
        return resultString;
    }

    /**
     * 金楼短信发送md5加密
     */
    public static String MD51(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public Md5Util() {
        super(ALGORITHM_NAME);
    }

    public Md5Util(Object source, Object salt, int hashIterations) {
        super(ALGORITHM_NAME, source, salt, hashIterations);
    }

    //MD5加密
    public static String md5(String source,String salt,int hashIterations){

        Md5Util md5 = new Md5Util(source,salt,hashIterations);
        return  md5.toString();
    }

    public static String fromHexString(String hex) {
        Md5Util hash = new Md5Util();
        hash.setBytes(Hex.decode(hex));
        return hash.toString();
    }

    public static String fromBase64String(String base64) {
        Md5Util hash = new Md5Util();
        hash.setBytes(Base64.decode(base64));
        return hash.toString();
    }

    public static void main(String[] args){
        //原始密码
        String source="111111";
        //盐
        String salt="pda";
        //散列次数
        int hashIterations = 2;
        //上边散列一次 f3694f162729b7d0254c6e40260bf15c
        //上边散列2次 36f2dfa24d0a9fa97276abbe13e596fc
        // 参数一：明文原始密码
        //参数二： 盐，通过使用随机数
        //参数三： 散列次数，比如散列两次相当如 md5(md5(''))
        String password= Md5Util.md5(source,salt,hashIterations);
        logger.info(password);
    }
}

package pers.lintao.eorp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: MD5加密工具类
 * @author: lilintao@163.com
 * @time: 2023/4/16 12时03分 周一
 */
public class MD5Util {

    public static String getMd5(String origin){
        //盐
        char[] hexArray = {
                'l','i','l','i','n',
                't','a','o','x','u',
                't','i','a','n','z',
                'i'
        };
        try {
            byte[] originBytes = origin.getBytes();
            //加密对象实例
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //加密
            md5.update(originBytes);
            //获取加密后的数组(容易破解,进一步使用盐加密)
            byte[] digest = md5.digest();
            //准备加盐后返回的数组
            char[] str = new char[digest.length * 2];
            int k = 0;
            //对加密后的数组加盐
            //首先判断加密后的数组的长度,遍历数组,对每个元素进行移位运算
            for (int i = 0; i < digest.length; i++) {
                byte b = digest[i];
                str[k++] = hexArray[b>>>4&0xf];
                //System.out.println(b>>>4&0xf);
                str[k++] = hexArray[b&0xf];
                //System.out.println(b&0xf);
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "123456";
        }
    }

    public static void main(String[] args) {
        System.out.println(getMd5("admin"));
    }

}

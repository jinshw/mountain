package com.site.mountain.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * MD5进行文件、字符串、byte[]数组进行加密
 *
 * @作者： 张晓东
 * @创建日期： 2015年11月12日
 *
 * @修改记录（修改时间、作者、原因）：
 */
public class MD5Util {
	//哈希值规则包含的字符
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	protected static MessageDigest messagedigest = null;

	/**
	 * MessageDigest初始化（信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。）
	 */
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");//获取信息摘要实例
		} catch (NoSuchAlgorithmException e) {
			System.err.println("MD5FileUtil messagedigest初始化失败");
			e.printStackTrace();
		}
	}

	/**
	 * 对文件进行MD5加密
	 *
	 * @作者: 张晓东
	 * @创建日期： 2015年11月12日
	 *
	 * @param file
	 * @throws IOException
	 * @返回值： String
	 *
	 * @修改记录（修改时间、作者、原因）：
	 */
	public static String getFileMD5String(File file) throws IOException {
		@SuppressWarnings("resource")
        FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());//进行哈希值计算
	}

	/**
	 * 对字符串进行MD5加密
	 *
	 * @作者: 张晓东
	 * @创建日期： 2015年11月12日
	 *
	 * @返回值： String
	 *
	 * @修改记录（修改时间、作者、原因）：
	 */
	public static String getMD5String(String s) {
		return getMD5Byte(s.getBytes());
	}

	/**
	 * 对byte类型的数组进行MD5加密
	 *
	 * @作者: 张晓东
	 * @创建日期： 2015年11月12日
	 *
	 * @param bytes
	 * @返回值： String
	 *
	 * @修改记录（修改时间、作者、原因）：
	 */
	public static String getMD5Byte(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	/**
	 * 
	 *
	 * @作者: 张晓东
	 * @创建日期： 2015年11月14日
	 *
	 * @param bytes 字节数组
	 * @param m 起始字节
	 * @param n 字节长度
	 * @return
	 * @返回值： String
	 *
	 * @修改记录（修改时间、作者、原因）：
	 */
	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
			char c1 = hexDigits[bytes[l] & 0xf];
			stringbuffer.append(c0);
			stringbuffer.append(c1);
		}
		return stringbuffer.toString();
	}
	
	public static String getId() {
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5");
            UUID uuid = UUID.randomUUID();
            String guidStr = uuid.toString();
            md.update(guidStr.getBytes(), 0, guidStr.length()); 
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null; 
        } 
    }

	/** 
     * 自定义规则生成32位编码 
     * @return string 
     */  
    public static String getUUIDByRules(String rules)
    {  
      int rpoint = 0;  
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random();
        int length = 32;  
        for(int i=0;i<length;i++)  
        {  
            if(rules!=null){  
                rpoint = rules.length();  
                int randNum = rand.nextInt(rpoint);  
                generateRandStr.append(rules.substring(randNum,randNum+1));  
            }  
        }  
        return generateRandStr+"";  
    }  
	
	public static void main(String[] args) throws IOException {
		/*String filePath2 = "F:/图片/搜狗图片/766971.jpg";
		System.out.println("766971文件加密结果为：" + getFileMD5String(new File(filePath2)));
		String filePath = "F:/图片/搜狗图片/766972.jpg";
		System.out.println("766972文件加密结果为：" + getFileMD5String(new File(filePath)));*/
//		String filePath1 = UUIDUtil.create32UpperUUID();
		System.out.println(getMD5String("123456"));
		System.out.println(getId());
		System.out.println(getUUIDByRules("1234567890qwertyuiopasdfghjklzxcvbnm"));
		BigInteger bigInteger = new BigInteger("12345");
		List<String> list = new ArrayList();
		list.add("12345");
		System.out.println(list.contains(bigInteger));
	}
}

package generatecode;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import com.alibaba.druid.sql.visitor.functions.Substring;

import sun.misc.BASE64Decoder;

public class tester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str = "abcdefghijklmnopqrstuvwxyz1234567890";

		byte[] b = Base64.getEncoder().encode(str.getBytes());
		String tempStr = new String(b);
		System.out.println(tempStr);

        OutputStream out = new FileOutputStream("D:\\aa.txt");

        BASE64Decoder decoder = new BASE64Decoder();
		for(int i=0;i<tempStr.length();i=i+40){
			String subStr = "";
			if(i+40>tempStr.length()){
				subStr = tempStr.substring(i,tempStr.length());
			}else {
				subStr = tempStr.substring(i,i+40);
			}
	        byte[] b2 = decoder.decodeBuffer(subStr);
	        out.write(b2);
	        out.flush();
		}
        out.close();


//
//        byte[] b3 = decoder.decodeBuffer("MQ==");
//		System.out.println(new String(b3));
	}

}

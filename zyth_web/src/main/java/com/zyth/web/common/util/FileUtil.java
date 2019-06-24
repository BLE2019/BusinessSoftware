package com.zyth.web.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static String CombineTempApk(String id,String fileName,int fileCount){

		String baseUrl = PropertyUtils.getString("resBaseUrl");
		String[] fpaths = new String[fileCount];
		for (int i = 0; i < fpaths.length; i++) {
			fpaths[i] = baseUrl+"apk/temp/"+i+"/temp.apk";
		}
		String destDirName = baseUrl+"apk/"+id+"/";
		File dir = new File(destDirName);
		// 判断目录是否存在
		if (!dir.exists()) {
			//不存在时，创建目录
			dir.mkdirs();
		}else{
			//存在时，删除原有文件
			File[] files = dir.listFiles();// 获得传入路径下的所有文件
			for (int i = 0; i < files.length; i++) {
				//对应文件不存在旧文件列表中时，删除
				files[i].delete();
			}
		}
		String newName = destDirName+fileName+".apk";
		mergeFiles(fpaths,newName);

		String showUrl = PropertyUtils.getString("showBaseUrl");

		return showUrl+"apk/"+id+"/"+fileName+".apk";
	}

	public static String SaveFileWithName(String imgBase64Str,String type,String id,String ext,String fileName){
		String[] imgBase64Strs = new String[1];
		imgBase64Strs[0] = imgBase64Str;
		String[] exts = new String[1];
		exts[0] = ext;
		String[] fileNames = new String[1];
		fileNames[0] = fileName;

		return SaveFile(imgBase64Strs,type,id,exts,fileNames)[0];
	}

	public static String[] SaveFile(String[] imgBase64Strs,String type,String id,String[] exts,String fileNames[]){
		logger.info(imgBase64Strs[0]);
		//将旧的文件取出，组成一个不用删除的列表
		String notDeleteFileStr = "";
		for (int j = 0; j < exts.length; j++) {
			//旧的文件不删除
			if(imgBase64Strs[j].equals(exts[j])){
				notDeleteFileStr += ","+imgBase64Strs[j];
			}
		}

		String baseUrl = PropertyUtils.getString("resBaseUrl");
		String destDirName = baseUrl+type+"/"+id+"/";
		File dir = new File(destDirName);
		// 判断目录是否存在
		if (!dir.exists()) {
			//不存在时，创建目录
			dir.mkdirs();
		}else{
			//存在时，删除原有文件
			File[] files = dir.listFiles();// 获得传入路径下的所有文件
			for (int i = 0; i < files.length; i++) {
				//对应文件不存在旧文件列表中时，删除
				if(notDeleteFileStr.indexOf(files[i].getName()) == -1){
					files[i].delete();
				}
			}
		}

		String[] result = new String[exts.length];

		for (int i = 0; i < exts.length; i++) {

			//旧的文件，不做处理
			if(imgBase64Strs[i].equals(exts[i])){
				result[i]=imgBase64Strs[i];
			}else{
				//新的文件上传
				String imgBase64Str = imgBase64Strs[i];
				String ext = exts[i];
				String fileName = "";
				if(fileNames==null){
					//获取UUID
					fileName = UUID.randomUUID().toString().replaceAll("-", "");
				}else{
					fileName = fileNames[i];
				}

				String base64Code = imgBase64Str;
				if(imgBase64Str.indexOf("base64,")!=-1){
					base64Code = imgBase64Str.substring(imgBase64Str.indexOf("base64,")
							+"base64,".length());
				}

				GenerateFile(base64Code,destDirName+fileName+ext);

				String showUrl = PropertyUtils.getString("showBaseUrl");
				result[i]= showUrl+type+"/"+id+"/"+fileName+ext;
			}
		}

		return result;
	}

	public static String[] SaveImage(String[] imgBase64Strs,String type,Long id,String[] exts){
		String strId = "";
		if(id!=null){
			strId = String.valueOf(id);
		}

		return SaveFile(imgBase64Strs,type,strId,exts,null);
	}

	public static String SaveImage(String imgBase64Str,String type,Long id,String ext){
		String[] imgBase64Strs = new String[1];
		imgBase64Strs[0] = imgBase64Str;
		String[] exts = new String[1];
		exts[0] = ext;

		return SaveImage(imgBase64Strs,type,id,exts)[0];
	}

	//base64字符串转化成文件
    public static boolean GenerateFile(String fileBase64Str,String filePath){
    	//对字节数组字符串进行Base64解码并生成文件
        if (fileBase64Str == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try{

            OutputStream out = new FileOutputStream(filePath);
        	//为解决内存溢出问题，分段解码写文件
            //base64字符串分段，必须为4的倍数
            int size = 4000;
    		for(int i=0;i<fileBase64Str.length();i=i+size){
    			String subStr = "";
    			if(i+size>fileBase64Str.length()){
    				subStr = fileBase64Str.substring(i,fileBase64Str.length());
    			}else {
    				subStr = fileBase64Str.substring(i,i+size);
    			}
    	        byte[] b = decoder.decodeBuffer(subStr);
                for(int j=0;j<b.length;++j){
                    if(b[j]<0)
                    {//调整异常数据
                        b[j]+=256;
                    }
                }
    	        out.write(b);
    	        out.flush();
    		}
            out.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static boolean mergeFiles(String[] fpaths, String resultPath) {
    	File[] files = new File[fpaths.length];
        File resultFile = new File(resultPath);

        for (int i = 0; i < fpaths.length; i ++) {
            files[i] = new File(fpaths[i]);
        }

        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
            for (int i = 0; i < fpaths.length; i ++) {
                FileChannel blk = new FileInputStream(files[i]).getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                blk.close();
            }
            resultFileChannel.close();
        }catch (Exception e) {
        	e.printStackTrace();
            return false;
        }

        for (int i = 0; i < fpaths.length; i ++) {
            files[i].delete();
        }

        return true;
    }
}

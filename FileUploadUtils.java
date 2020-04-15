package com.moon.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	
	//输入文件返回带文件名的文件存储路径
	public String uploadFile(MultipartFile file, String localPath, String projectPath) {
		if(file.isEmpty()) {
			System.out.println("文件为空");
		}
		//获取文件名
		String fileName=file.getOriginalFilename();
		//获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		//本地存储的绝对路径（虚拟路径） 示例：/Users/yanyue/ideaJavaWeb/upload/image/
		//String filePath = absolutePath;
		//新的文件名（随机文件名+文件后缀）
		fileName=UUID.randomUUID()+suffixName;
		//新的完整路径
		String pathName=localPath+fileName;
		//创建文件
		File dest = new File(pathName);
		//判断有无父路径，没有就创建
		if(!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);//把图片转换到现在的url
		}catch (Exception e) {
			e.printStackTrace();
		}
		//将文件显示到项目路径中 项目路径示例：/upload/image/
		String filename=projectPath+fileName;
		return filename;
	}
}

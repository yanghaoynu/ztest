package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileOutputStreamTest {

	public FileOutputStreamTest() {
	}

	public static void main(String[] args) {
     try {
		FileOutputStream fileOutputStream = new FileOutputStream("D:\\QLDownload\\fileOutputStream.txt");
		Long begin = System.currentTimeMillis();
		String lineString = "测试FileOutputStream\r\n";
		for(int i =0;i<1000000;i++){
			fileOutputStream.write(lineString.getBytes(), 0, lineString.getBytes().length);
		}
		fileOutputStream.flush();
		fileOutputStream.close();
		Long end   = System.currentTimeMillis();
		System.out.println("耗时"+(end-begin)/1000+"s");
		File file = new File("D:\\QLDownload\\\\fileOutputStream.txt");
		System.out.println(file.length()/1024+"KB");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileOutputStreamTestutf8 {

	public FileOutputStreamTestutf8() {
	}

	public static void main(String[] args) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("D:\\QLDownload\\20w测试预警.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
			for (int i = 1; i <= 200000; i++) {
				writer.write("姓名" + "\t" + RandomNum.genRandomNum(18) + "\t" + "身份证" + "\t" + (i % 2 == 0 ? "男" : "女")+"\r\n");
			}
			writer.flush();
			fileOutputStream.flush();
			writer.close();
			fileOutputStream.close();
			System.out.println("你好");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

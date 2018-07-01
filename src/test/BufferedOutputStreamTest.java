package test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author YANGHAO
 *
 */
public class BufferedOutputStreamTest {

	public BufferedOutputStreamTest()  {}

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\QLDownload\\BufferedOutputStreamTest.txt");
	    FileOutputStream fileOutputStream = new FileOutputStream(file,true);
	    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
	    String lineString = "测试BufferedOutputStream\r\n";
	    for (int i = 0; i <1000000; i++) {
			bufferedOutputStream.write(lineString.getBytes(), 0, lineString.getBytes().length);
		}
	    bufferedOutputStream.flush();
		bufferedOutputStream.close();
		fileOutputStream.close();
        System.out.println("this is a test out!");
        System.out.println("遗忘就是个");
        System.out.println("你好");
        System.out.println("你好，我是杨昊，请问你是谁啊");
	}

}

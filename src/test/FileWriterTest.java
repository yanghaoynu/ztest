package test;

import java.io.FileWriter;

public class FileWriterTest {

    public FileWriterTest() {
    }

    public static void main(String[] args) throws Exception {
        FileWriter fileWriter = new FileWriter("D:\\QLDownload\\fileWriterTest.txt");
        String lineString = "fileWriterTest测试\r\n";
        for(int i = 0; i<1000000; i++) {
            fileWriter.write(lineString);
        }
        fileWriter.flush();
        fileWriter.close();
        System.out.println("写入完毕");
    }

}

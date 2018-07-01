package test;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <li>当前位置:test</li>
 * <li>标题:FtpUtils</li>
 * <li>描述:</li>
 * <li>公司:</li>
 * <li>版本:1.0</li>
 *
 * @author yanghao
 * @date 2018年5月9日 下午3:44:58
 */
public class FtpUtils {
    private static FtpUtils instance = null;
    private static FTPClient ftpClient = null;
    private String cacheDir = "d:/cache/";
    private String server = "192.168.56.101";
    private int port = 21;
    private String userName = "administrator";
    private String userPassword = "yanghao";

    public static FtpUtils getInstance() {
        if (instance == null) {
            instance = new FtpUtils();
        }
        ftpClient = new FTPClient();
        return instance;
    }

    /**
     * 连接FTP服务器
     *
     * @return
     */
    private boolean connect() {
        boolean stat = false;
        try {
            if (ftpClient.isConnected()) {
                return true;
            }
            ftpClient.connect(server, port);
            stat = true;
        } catch (IOException e) {
            stat = false;
            e.printStackTrace();
        }
        return stat;
    }

    /**
     * 打开FTP服务器
     *
     * @return
     */
    public boolean open() {
        if (!connect()) {
            return false;
        }

        boolean stat = false;
        try {
            stat = ftpClient.login(userName, userPassword);
            // 检测连接是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                close();
                stat = false;
            }
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }


    /**
     * 关闭FTP服务器
     */
    public void close() {
        try {
            if (ftpClient != null) {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }

                ftpClient = null;
            }
        } catch (IOException e) {
        }
    }


    /**
     * 上传文件到FTP服务器
     *
     * @param filename
     * @param path
     * @param input
     * @return
     */
    public boolean upload(String filename, String path, InputStream input) {
        boolean stat = false;
        try {
            cd(path);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            stat = ftpClient.storeFile(filename, input);
            input.close();  //关闭输入流
        } catch (IOException e) {

        }
        return stat;
    }

    /**
     * 上传文件到FTP服务器
     *
     * @param filename 文件名
     * @param path 当前
     * @param filepath
     * @return
     */
    public boolean upload(String filename, String path, String filepath) {
        boolean stat = false;
        try {
            cd(path);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            FileInputStream input = new FileInputStream(new File(filepath));
            stat = ftpClient.storeFile(filename, input);

            input.close();  //关闭输入流
        } catch (IOException e) {
            e.printStackTrace();

        }
        return stat;
    }

    /**
     * 上传文件
     *
     * @param filename
     * @param path
     * @param file
     * @return
     */
    public boolean upload(String filename, String path, File file) {
        boolean stat = false;
        try {
            cd(path);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            FileInputStream input = new FileInputStream(file);
            stat = ftpClient.storeFile(filename, input);
            input.close();  //关闭输入流
        } catch (IOException e) {

        }
        return stat;
    }

    /**
     * @param path
     * @param filename
     * @param input
     * @param quality
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public boolean uploadImage(String path, String filename, InputStream input, float quality, int maxWidth, int maxHeight) {
        boolean stat = false;
        try {
            String suffex = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            System.out.println(suffex);
            File imagePath = new File(cacheDir + filename);
            Thumbnails.of(input).outputQuality(quality).size(maxWidth, maxHeight).toFile(imagePath);
            input.close();

            if (!imagePath.exists()) {
                return false;
            }

            cd(path);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            FileInputStream input2 = new FileInputStream(imagePath);
            stat = ftpClient.storeFile(filename, input2);
            input2.close();//关闭输入流
            imagePath.delete();
            stat = true;
        } catch (IOException e) {
            e.printStackTrace();
            stat = false;
        }
        return stat;
    }


    /**
     * 循环切换目录
     *
     * @param dir 目的路径
     * @return
     */
    private boolean cd(String dir) {
        boolean stat;
        try {
            //一级目录
            String[] dirs = dir.split("/");
            //打开一级目录后直接返回
            if (dirs.length == 0) {
                return ftpClient.changeWorkingDirectory(dir);
            }
            stat = ftpClient.changeToParentDirectory();
            for (String dirss : dirs) {
                stat = stat && ftpClient.changeWorkingDirectory(dirss);
            }

            stat = true;
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }

    /***
     * 创建目录
     * @param dir 目录名称
     * @return
     */
    public boolean mkdir(String dir) {
        boolean stat = false;
        try {
            ftpClient.changeToParentDirectory();
            ftpClient.makeDirectory(dir);
            stat = true;
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }

    /***
     * 创建多个层级目录
     * @param dir dong/zzz/ddd/ewv
     * @return
     */
    public boolean mkdirs(String dir) {
        String[] dirs = dir.split("/");
        if (dirs.length == 0) {
            return false;
        }
        boolean stat = false;
        try {
            ftpClient.changeToParentDirectory();
            for (String dirss : dirs) {
                ftpClient.makeDirectory(dirss);
                ftpClient.changeWorkingDirectory(dirss);
            }

            ftpClient.changeToParentDirectory();
            stat = true;
        } catch (IOException e) {
            stat = false;
        }
        return stat;
    }

    /**
     * 删除文件夹
     *
     * @param pathname
     * @return
     */
    public boolean rmdir(String pathname) {
        try {
            return ftpClient.removeDirectory(pathname);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     */
    public boolean remove(String pathname) {
        boolean stat = false;
        try {
            stat = ftpClient.deleteFile(pathname);
        } catch (Exception e) {
            stat = false;
        }
        return stat;
    }

    /**
     * 移动文件或文件夹
     *
     * @param pathname1
     * @param pathname2
     * @return
     */
    public boolean rename(String pathname1, String pathname2) {
        try {
            return ftpClient.rename(pathname1, pathname2);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
    }
}
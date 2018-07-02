package test;

import org.apache.commons.lang.StringUtils;

/**
* @ClassName:AppPathUtils
* @Description:TODO(获取类文件绝对路劲,获取中间件webapp绝对路径,获取任意文件属性路径,获取类文件所在工程绝对路径)
* @author YANGHAO
* @date:2004-4-7下午04:46:36
*
*
*/
public class AppPathUtils {

	/**
	 *<li>描述:relativePath</li>
	 *<li>方法名称: getWebFileAbsolutePath</li>
	 *<li>参数:@param relativePath
	 *<li>参数:@return</li>
	 *<li>返回类型:String</li>
	 *<li>最后更新作者:yanghao</li>
	 */
	public static String getWebFileAbsolutePath(String relativePath){
		String webAppRootPath=getWebAppRootPath();
		if(StringUtils.isNotBlank(webAppRootPath)){
			return webAppRootPath+(relativePath.indexOf("/")==0?relativePath:"/"+relativePath);
		}
		return "";
	}

	/**
	 *<li>描述:返回该类所在的中间件的webapp绝对路径C:/Program Files/Apache Software Foundation/apache-tomcat-6.0.16/webapps</li>
	 *<li>方法名称: getWebAppRootPath</li>
	 *<li>参数:@return</li>
	 *<li>返回类型:String</li>
	 *<li>最后更新作者:yanghao</li>
	 */
	public static String getWebAppRootPath(){
		String webAppPath=getWebAppPath();
		if(StringUtils.isNotBlank(webAppPath)){
			return webAppPath.substring(0,webAppPath.lastIndexOf("/"));
		}
		return "";
	}

	/**
	 *<li>描述:返回该类所在的project绝对路径C:/Program Files/Apache Software Foundation/apache-tomcat-6.0.16/webapps/ybqyyd</li>
	 *<li>方法名称: getWebAppPath</li>
	 *<li>参数:@return</li>
	 *<li>返回类型:String</li>
	 *<li>最后更新作者:yanghao</li>
	 */
	public static String getWebAppPath(){
		String classFilePath=getAppPath(AppPathUtils.class);
		if(classFilePath.indexOf("WEB-INF")!=-1){
			return classFilePath.substring(0,classFilePath.indexOf("/WEB-INF"));
		}else if(classFilePath.indexOf("APP-INF")!=-1)
		{
			return classFilePath.substring(0,classFilePath.indexOf("/APP-INF"));
		}
		return "";
	}
	
	
	  /**
	 *<li>描述:返回该class文件所在的位置C:/Program Files/Apache Software Foundation/apache-tomcat-6.0.16/webapps/ybqyyd/WEB-INF/classes</li>
	 *<li>方法名称: getAppPath</li>
	 *<li>参数:@param cls
	 *<li>参数:@return</li>
	 *<li>返回类型:String</li>
	 *<li>最后更新作者:yanghao</li>
	 */
	public static String getAppPath(Class cls) {
	      //检查用户传入的参数是否为空
	      if (cls == null)
	          throw new IllegalArgumentException("参数不能为空！");
	      ClassLoader loader = cls.getClassLoader();
	      //获得类的全名，包括包名
	      String clsName = cls.getName() + ".class";
	      //获得传入参数所在的包
	      Package pack = cls.getPackage();
	      String path = "";
	      //如果不是匿名包，将包名转化为路径
	      if (pack != null) {
	          String packName = pack.getName();
	          //此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
	          if (packName.startsWith("java.") || packName.startsWith("javax."))
	              throw new IllegalArgumentException("不要传送系统类！");
	          //在类的名称中，去掉包名的部分，获得类的文件名
	          clsName = clsName.substring(packName.length() + 1);
	          //判定包名是否是简单包名，如果是，则直接将包名转换为路径，
	          if (packName.indexOf(".") < 0)
	              path = packName + "/";
	          else {//否则按照包名的组成部分，将包名转换为路径
	              int start = 0, end = 0;
	              end = packName.indexOf(".");
	              while (end != -1) {
	                  path = path + packName.substring(start, end) + "/";
	                  start = end + 1;
	                  end = packName.indexOf(".", start);
	              }
	              path = path + packName.substring(start) + "/";
	          }
	      }
	     
	      //调用ClassLoader的getResource方法，传入包含路径信息的类文件名
	      java.net.URL url = loader.getResource(path + clsName);
	      //从URL对象中获取路径信息
	     
	      String realPath = url.getPath();
	      //去掉路径信息中的协议名"file:"
	      int pos = realPath.indexOf("file:");
	      if (pos > -1)
	          realPath = realPath.substring(pos + 5);
	     
	     
	      //去掉路径信息最后包含类文件信息的部分，得到类所在的路径
	      pos = realPath.indexOf(path + clsName);
	      realPath = realPath.substring(0, pos - 1);
	     
	      //如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
	      if (realPath.endsWith("!"))
	          realPath = realPath.substring(0, realPath.lastIndexOf("/"));
	     
	     
	      //结果字符串可能因平台默认编码不同而不同。因此，改用 decode(String,String) 方法指定编码。
	      try {
	          realPath = java.net.URLDecoder.decode(realPath, "utf-8");
	         if(realPath.indexOf(":")!=-1&&realPath.indexOf("/")==0){
	        	 realPath=realPath.substring(1,realPath.length());
	         }      
	      } catch (Exception e) {
	          throw new RuntimeException(e);
	      }
	      return realPath;
	  }
	//getAppPath定义结束
}

package com.ds.zk.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 简易服务器
 * @author ds
 */
public class SimpleServer {

    // 资源根目录
    public static String WEB_ROOT = System.getProperty("user.dir");
    // 请求的资源地址
    public static String url = "";
    // 读取web.properties，保存映射关系
    private static HashMap<String, String> map = new HashMap<>();

    // 静态代码块，加载时运行一次
    static {
        try {
            // 将映射地址存到map集合中
            Properties prop = new Properties();
            prop.load(new FileInputStream(WEB_ROOT + "/zookeeper/src/main/resources/WEB-INF/web.properties"));
            Set set = prop.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = prop.getProperty(key);
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 开启服务器
    public void start(int port) {
        try {
            System.out.println("port " + port + " server is starting... \n");

            // 监听8080端口
            ServerSocket serverSocket = new ServerSocket(port);
            // 后期改成NIO，Tomcat默认NIO模式,目前使用BIO (阻塞IO,并不使用多线程了)
            while (true) {

                // 监听客户端连接
                Socket socket = serverSocket.accept();

                // 由Tomcat服务器来创建请求响应对象
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                Request request = new Request(in);
                System.out.println("请求地址:" + request.getUrl());
                Response response = new Response(out);
                System.out.println("一个请求连接了");

                // 分派器
                dispatch(request, response);

                // 关闭各种资源
                in.close();
                out.close();
                socket.close();
                System.out.println("一个请求关闭连接了 \n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 负责指派去哪访问
    private void dispatch(Request request, Response response) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        int length = 0;
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();

        String url = request.getUrl();
        int i = url.indexOf("?");
        // 请求路径
        String path = url.substring(0, i).replace("/", "");
        // 请求参数
        String params = url.substring(i + 1);
        request.setParams(params);

        // 有Servlet映射
        if (map.containsKey(path)) {
            String servletClassName = map.get(path);

            // 反射
            Class clazz = Class.forName(servletClassName);
            Servlet servlet = (Servlet) clazz.newInstance();
            servlet.service(request, response);

            // 访问静态资源
        } else {
            File file = new File(WEB_ROOT, request.getUrl());

            // 静态资源存在
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                while ((length = fileInputStream.read(bytes)) != -1) {
                    stringBuffer.append(new String(bytes, 0, length));
                }
                response.write(stringBuffer.toString(), 200);

                // 静态资源不存在
            } else {
                file = new File(WEB_ROOT + "/zookeeper/src/main/resources/WEB-INF/", "/404.html");
                fileInputStream = new FileInputStream(file);
                while ((length = fileInputStream.read(bytes)) != -1) {
                    stringBuffer.append(new String(bytes, 0, length));
                }
                response.write(stringBuffer.toString(), 404);
            }
        }
    }
}

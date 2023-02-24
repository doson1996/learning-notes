package java.lang;

/**
 * @Author ds
 * @Date 2021/4/16 15:07
 * @Description     通过双亲委派，防止了核心类 (如String)被篡改
 *
 * 运行main方法时，会发生如下错误
 *      错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
 *          public static void main(String[] args)
 *      否则 JavaFX 应用程序类必须扩展javafx.application.Application
 * 因为在java.lang包下的类都已经被启动类加载器加载了，而真正的String里是没有main方法的
 */
//public class String {
//
//    public static void main(String[] args) {
//        System.out.println("1");
//    }
//}

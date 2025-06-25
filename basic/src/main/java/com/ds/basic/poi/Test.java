package com.ds.basic.poi;

import java.io.File;
import java.util.Scanner;

/**
 * @author ds
 * @date 2025/3/21
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        WordUtils.spilt("D://docx//input//");

        File file = new File("D://1.txt");
        Scanner scanner = new Scanner(file);
        StringBuilder contentBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine()).append(System.lineSeparator());
        }

        scanner.close();
        String str = contentBuilder.toString()
                .replaceAll("</html>", "")
                .replaceAll("<html>", "")
                .replaceAll("<head>.*?</head>", "")
                .replaceAll("</body>", "")
                .replaceAll("<body.*?>", "")
                .replaceAll("</div>", "</p>")
                .replaceAll("<div>", "<p>")
                .replaceAll("<div.*?>", "");

        System.out.println("str = " + str);

    }
}

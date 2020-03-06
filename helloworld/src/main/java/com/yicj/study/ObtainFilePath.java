package com.yicj.study;

import java.io.File;
import java.net.URL;

public class ObtainFilePath {

    public static void main(String[] args) {
        //obtainProjectPath() ;
        obtainByClass() ;
    }


    /**
     * 获取项目的根目录，class则,
     * 1.如果是jar则为存放jar的根目录
     * 2.如果是执行class文件，则为class文件所在的根目录
     * 3.main中执行为工程的根目录，本例为BestPractices
     */
    private static void obtainProjectPath(){
        File file = new File("" );
        File realPath = file.getAbsoluteFile();
        System.out.println("base path: " + realPath);
    }

    /**
     * 通过class对象获取资源
     */
    private static void obtainByClass(){
        // 返回 file:/root/target/classes
        URL r1 = ObtainFilePath.class.getResource("/");
        // 返回 file:/root/target/classes/com/yicj/study/
        URL r2 = ObtainFilePath.class.getResource("./");
        // 返回 file:/root/target/classes/com/yicj/study/
        URL r3 = ObtainFilePath.class.getResource("");
        // 返回 file:/root/target/classes/resource.png
        URL r4 = ObtainFilePath.class.getResource("/resource.png");
        // 图片不存在返回null
        URL r5 = ObtainFilePath.class.getResource("./resource.png");
        System.out.println("r1 : " + r1);
        System.out.println("r2 : " + r2);
        System.out.println("r3 : " + r3);
        System.out.println("r4 : " + r4);
        System.out.println("r5 : " + r5);

        //file
        String p3 = r3.getProtocol();
        String p4 = r4.getProtocol();
        System.out.println("p3: " + p3);
        System.out.println("p4: " + p4);
    }

}

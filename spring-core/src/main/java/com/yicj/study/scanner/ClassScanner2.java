package com.yicj.study.scanner;

import sun.reflect.Reflection;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
public class ClassScanner2 {

    /**
     * 从包package中获取所有的Class
     * @param packageName
     * @return
     */
    public static List<Class<?>> scanClasses(String packageName) {
        try {
            // 第一个class类的集合
            List<Class<?>> classes = new ArrayList<>();
            // 是否循环迭代
            boolean recursive = true;
            // 获取包的名字 并进行替换
            String packageDirName = packageName.replace('.', '/');
            //String packageDirName = packageName.replace('.', File.separatorChar);
            System.out.println("packageDirName : " + packageDirName);
            // 定义一个枚举的集合 并进行循环来处理这个目录下的things
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader()
                    .getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");
                    //System.out.println("file : " + url.getFile());
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    classes.addAll(findAndAddClassesInPackageByJar(url, packageDirName, recursive));
                }
            }
            return classes;
        }catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }

    private static Set<Class<?>> findAndAddClassesInPackageByJar(URL url,
             String packageDirName, boolean recursive) throws IOException {
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 如果是jar包文件
        // 定义一个JarFile
        System.err.println("jar类型的扫描");
        // 获取jar
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection() ;
        JarFile jarFile = jarURLConnection.getJarFile();
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entries = jarFile.entries();
        // 同样的进行循环迭代
        while (entries.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
            JarEntry entry = entries.nextElement();
            dealJarEntry(entry,packageDirName,recursive,classes) ;
        }
        return classes ;
    }


    /**
     *
     * @param entry
     * @param packageDirName
     * @param recursive
     * @param classes
     */
    private static void dealJarEntry(JarEntry entry, String packageDirName,
                boolean recursive ,Set<Class<?>> classes){
        String name = entry.getName();
        // 如果是以/开头的
        if (name.charAt(0) == '/') {
            // 获取后面的字符串
            name = name.substring(1);
        }
        // 如果前半部分和定义的包名相同
        if (name.startsWith(packageDirName)) {
            int idx = name.lastIndexOf('/');
            String packageName = null ;
            // 如果以"/"结尾，是一个包
            if (idx != -1) {
                // 获取包名 把"/"替换成"."
                packageName = name.substring(0, idx).replace('/', '.');
            }
            // 如果可以迭代下去,并且是一个包
            if ((idx != -1) || recursive) {
                // 如果是一个.class文件 而且不是目录
                if (name.endsWith(".class") && !entry.isDirectory()) {
                    // 去掉后面的".class" 获取真正的类名
                    String className = name.substring(
                            packageName.length() + 1, name.length() - 6);
                    try {
                        // 添加到classes
                        //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                        classes.add(Class.forName(packageName + '.' + className));
                        /*ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                        classes.add(contextClassLoader.loadClass(packageName + '.' + className));*/
                    } catch (ClassNotFoundException e) {
                        // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 以文件的形式来获取包下的所有Class
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(
            String packageName, String packagePath,
            final boolean recursive, List<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirFiles = dir.listFiles(file -> {
            return (recursive && file.isDirectory())||(file.getName().endsWith(".class"));
        });
        // 循环所有文件
        for (File file : dirFiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                String newPackageName = packageName + "." + file.getName() ;
                String newPackagePath = file.getAbsolutePath() ;
                findAndAddClassesInPackageByFile(newPackageName,newPackagePath,recursive,classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,file.getName().length()-6);
                try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                    classes.add(contextClassLoader.loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.yicj.study;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class ClassScanner {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = ClassScanner.scan(ClassScanner.class.getPackage().getName());
        classList.forEach(clazz -> log.info("className : " + clazz.getName()));
    }

    public static List<Class<?>> scan(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> retList = new ArrayList<>() ;
        String packagePath = packageName.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            String protocol = url.getProtocol();
            //如果是jar文件
            if("jar".equals(protocol)){
                JarURLConnection jarConnection = (JarURLConnection)url.openConnection();
                JarFile jarFile = jarConnection.getJarFile();
                List<Class<?>> classes = parseJarFile(jarFile, packagePath);
                retList.addAll(classes) ;
            }else if ("file".equals(protocol)){
                //如果是普通是classes文件
                //注意中文会被编码，这里需要使用URLDecode解码
                //D:/%e6%b5%8b%e8%af%95%e4%b8%ad%e6%96%87/target/classes/com/yicj/study
                String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
                File packageDir = new File(filePath) ;
                parseCommonPackage(packageName, packageDir, retList);
            }
        }
        return retList ;
    }

    /**
     * 获取package下的class文件
     * @param packageName
     * @param dir
     * @param classList
     * @throws ClassNotFoundException
     */
    private static void parseCommonPackage(String packageName, File dir, List<Class<?>> classList)
            throws ClassNotFoundException {
        //只保存目录和以class为后缀的文件
        File[] files = dir.listFiles(f -> f.isDirectory() || f.getName().endsWith(".class"));
        for (File curFile :files){
            if(curFile.isFile() && curFile.getName().endsWith(".class")){
                Class<?> aClass = parseClassByFile(curFile, packageName);
                classList.add(aClass) ;
            }else {
                parseCommonPackage(packageName + "." + curFile.getName(), curFile, classList);
            }
        }
    }

    /**
     * 通过一个class file获取一个Class对象
     * @param file
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    private static Class<?> parseClassByFile(File file, String packageName) throws ClassNotFoundException {
        String fname = file.getName();
        String className = fname.substring(0,fname.length() - 6) ;
        Class<?> clazz = Thread.currentThread().getContextClassLoader()
                .loadClass(packageName+"." + className) ;
        return  clazz ;
    }

    /**
     * 解析jar中的class文件
     * @param jarFile
     * @param packagePath
     * @return
     * @throws ClassNotFoundException
     */
    private static List<Class<?>>  parseJarFile(JarFile jarFile, String packagePath) throws ClassNotFoundException {
        List<Class<?>> retList = new ArrayList<>() ;
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()){
            String entryName = entries.nextElement().getName();
            if(entryName.endsWith(".class") && entryName.startsWith(packagePath)){
                String className = entryName.replace('/','.')
                        .substring(0,entryName.length() -6) ;
                Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(className);
                retList.add(aClass) ;
            }
        }
        return retList;
    }
}

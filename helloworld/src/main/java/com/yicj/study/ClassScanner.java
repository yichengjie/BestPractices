package com.yicj.study;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
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
                log.info("file : " +  url);
            }
        }
        return retList ;
    }


    private static void parseCommonFile(){

    }



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

package com.wei.util;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

public class ClassUtil {

    /**
     * 遍历指定目录下的类,获取所有类
     *
     * @param packageName 指定包名
     * @param isRecursion 是否遍历子包
     * @return
     */
    public static Set<String> getClassNames(String packageName, boolean isRecursion) {
        Set<String> classNameSet = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", File.separator);
        URL url = classLoader.getResource(packagePath);
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (filePath != null) {
            classNameSet = getClassNamesFromDir(filePath, packageName, isRecursion);
        }
        return classNameSet;
    }

    public static Set<String> getClassNames(String packageName) {
        return getClassNames(packageName, true);
    }

    private static Set<String> getClassNamesFromDir(String filePath, String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<String>();
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File childFile : files) {
            if (childFile.isDirectory()) {
                if (isRecursion) {
                    classNames.addAll(getClassNamesFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
                }
            } else {
                String filename = childFile.getName();
                // 只加载class文件，不加载内部类
                if (filename.endsWith(".class") && !filename.contains("$")) {
                    classNames.add(packageName + "." + filename.replace(".class", ""));
                }
            }

        }
        return classNames;
    }
}

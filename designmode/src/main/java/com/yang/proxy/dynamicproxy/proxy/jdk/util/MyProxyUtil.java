package com.yang.proxy.dynamicproxy.proxy.jdk.util;

import org.apache.commons.lang.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description : 手写动态代理生成类
 * @date : 2019/12/20 16:23
 */
public class MyProxyUtil {
    public static final String line = "\n";
    public static final String tab = "\t";
    public static AtomicInteger atomicInteger = new AtomicInteger();

    //手写的动态代理生成代理类方法
    public static Object getProxy(Object target, InvocationHandler handler) throws Exception {
        Object proxy = null;
        Class<?> targetClass = target.getClass();
        //目标类的全限定名
        String targetName = targetClass.getName();
        //目标类的包名
        String targetackageName = targetName.substring(0, targetName.lastIndexOf("."));
        //目标类实现的接口们
        Class[] targetInterfaces = targetClass.getInterfaces();
        String classContent = "";
        //和目标类同一个包，避免引得的非public的包无权限访问
        String packageContent = "package " + targetackageName + ";" + line;
        //导入InvocationHandler包，因为要用到导入InvocationHandler包
        String importContent = "import java.lang.reflect.InvocationHandler;" + line;
        //导入所有接口包
        for (Class targetInterface : targetInterfaces) {
            importContent += "import " + targetInterface.getName() + ";" + line;
        }
        //定义动态代理类类名$Proxy0123456789
        int index = atomicInteger.incrementAndGet();
        String clazzFirstLineContent = "public class $Proxy" + index + " implements ";
        //实现目标类的所有接口
        for (Class targetInterface : targetInterfaces) {
            clazzFirstLineContent += targetInterface.getSimpleName();
        }
        clazzFirstLineContent += "{" + line;
        //设置固定私有属性InvocationHandler handler
        String filedContent = tab + "private InvocationHandler handler;" + line;
        //构造函数
        String constructorContent = tab + "public $Proxy" + index + " (InvocationHandler handler){ this.handler = handler; }" + line;
        String methodContent = "";
        //遍历所有接口
        for (Class targetInterface : targetInterfaces) {
            Method[] methods = targetInterface.getDeclaredMethods();
            //实现所有接口的方法
            for (Method method : methods) {
                String returnTypeName = method.getReturnType().getSimpleName();
                String methodName = method.getName();
                Class args[] = method.getParameterTypes();
                String argsContent = null;
                String argsClassContent = null;
                String paramsContent = null;
                int flag = 0;
                for (Class arg : args) {
                    if (argsContent == null) {
                        argsContent = "";
                    }
                    if (argsClassContent == null) {
                        argsClassContent = "";
                    }
                    if (paramsContent == null) {
                        paramsContent = "";
                    }
                    String argTypeName = arg.getSimpleName();
                    argsContent += argTypeName + " p" + flag + ",";
                    paramsContent += "p" + flag + ",";
                    argsClassContent += "p" + flag + ".getClass(),";
                    flag++;
                }
                paramsContent = StringUtils.isEmpty(paramsContent) ? "null" : paramsContent;
                argsClassContent = StringUtils.isEmpty(argsClassContent) ? "null" : argsClassContent;
                String oldArgsContent = argsContent;
                argsContent = StringUtils.isEmpty(argsContent) ? "" : argsContent;
                if (paramsContent != null && paramsContent.endsWith(",")) {
                    paramsContent = paramsContent.substring(0, paramsContent.length() - 1);
                }
                if (argsClassContent != null && argsClassContent.endsWith(",")) {
                    argsClassContent = argsClassContent.substring(0, argsClassContent.length() - 1);
                }
                if (argsContent != null && argsContent.endsWith(",")) {
                    argsContent = argsContent.substring(0, argsContent.length() - 1);
                }
                if ("void".equals(returnTypeName)) {
                    if (oldArgsContent == null) {
                        methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ") {try{" + line
                                + tab + tab + "handler.invoke(this,Class.forName(\"" + targetClass.getName() + "\").getMethod(\"" + methodName + "\", " + paramsContent + ")," + paramsContent + ");" + line
                                + tab + "}catch (Throwable var3) {}" +
                                "}" + line;
                    } else {
                        methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ")  {try{" + line
                                + tab + tab + "handler.invoke(this,Class.forName(\"" + targetClass.getName() + "\").getMethod(\"" + methodName + "\", " + argsClassContent + "),new Object[]{" + paramsContent + "});" + line
                                + tab + "}catch (Throwable var3) {} " + line
                                + tab + "}" + line;
                    }
                } else {
                    if (oldArgsContent == null) {
                        methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ") {try{" + line
                                + tab + tab + "return  ( " + returnTypeName + ") handler.invoke(this,Class.forName(\"" + targetClass.getName() + "\").getMethod(\"" + methodName + "\", " + paramsContent + ")," + paramsContent + ");" + line
                                + tab + "}catch (Throwable var3) {}"
                                + tab + "return null;}" + line;
                    } else {
                        methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ")  {try{" + line
                                + tab + tab + "return  (" + returnTypeName + ")handler.invoke(this,Class.forName(\"" + targetClass.getName() + "\").getMethod(\"" + methodName + "\", " + argsClassContent + "),new Object[]{" + paramsContent + "});" + line
                                + tab + "}catch (Throwable var3) {} " + line
                                + tab + "return null;}" + line;
                    }
                }
            }
        }
        //拼装java类字符串
        classContent = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";
        //生成一个临时文件放到本地磁盘中,要先确保文件夹存在，不然会报错
        String diskName = targetackageName.replaceAll("\\.", "\\\\");
        File file = new File("d:\\" + diskName + "\\$Proxy" + index + ".java");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(classContent);
            fw.flush();
            fw.close();
            //编辑为.class
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();
            //类加载去加载到jvm中
            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass(targetackageName + ".$Proxy" + index);
            //调用构造函数
            Constructor constructor = clazz.getConstructor(InvocationHandler.class);
            //生成代理类
            proxy = constructor.newInstance(handler);
        } catch (Exception e) {
        }
        return proxy;
    }
}

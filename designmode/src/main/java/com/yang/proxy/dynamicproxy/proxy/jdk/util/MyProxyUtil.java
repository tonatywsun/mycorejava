package com.yang.proxy.dynamicproxy.proxy.jdk.util;

import com.yang.proxy.dynamicproxy.proxy.jdk.MyJdkInvocationHandler;
import org.apache.commons.lang.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author : tona.sun
 * @description : 手写动态代理生成类
 * @date : 2019/12/20 16:23
 */
public class MyProxyUtil {
    public static final String line = "\n";
    public static final String tab = "\t";

    public static Object getProxy(Object target, MyJdkInvocationHandler handler) throws Exception {
        Object proxy = null;
        Class<?> targetClass = target.getClass();
        String targetName = targetClass.getName();
        String targetackageName = targetName.substring(0, targetName.lastIndexOf("."));
        String targetClassName = targetName.substring(targetName.lastIndexOf(".") + 1, targetName.length() - 1);
        String handlerName = handler.getClass().getName();
        String handlerPackageName = handlerName.substring(0, handlerName.lastIndexOf("."));
        String handlerClassName = handlerName.substring(handlerName.lastIndexOf(".") + 1, handlerName.length());
        Class[] targetInterfaces = targetClass.getInterfaces();
        String classContent = "";

        String packageContent = "package " + targetackageName + ";" + line;

        String importContent = "import " + handlerName + ";" + line;
        for (Class targetInterface : targetInterfaces) {
            importContent += "import " + targetInterface.getName() + ";" + line;
        }

        String clazzFirstLineContent = "public class $Proxy implements ";
        for (Class targetInterface : targetInterfaces) {
            clazzFirstLineContent += targetInterface.getSimpleName();
        }
        clazzFirstLineContent += "{" + line;

        String filedContent = tab + "private " + handlerClassName + " handler;" + line;

        String constructorContent = tab + "public $Proxy (" + handlerClassName + " handler){" + line
                + tab + tab + "this.handler = handler;"
                + line + tab + "}" + line;

        String methodContent = "";
        for (Class targetInterface : targetInterfaces) {
            Method[] methods = targetInterface.getDeclaredMethods();
            for (Method method : methods) {
                String returnTypeName = method.getReturnType().getSimpleName();
                String methodName = method.getName();
                Class args[] = method.getParameterTypes();
                String argsContent = "";
                String paramsContent = "";
                int flag = 0;
                for (Class arg : args) {
                    String temp = arg.getSimpleName();
                    argsContent += temp + " p" + flag + ",";
                    paramsContent += "p" + flag + ",";
                    flag++;
                }
                if (argsContent != null && argsContent.length() > 0) {
                    argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                    paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
                }
                String a = StringUtils.isEmpty(paramsContent) ? "null" : paramsContent;
                if ("void".equals(returnTypeName)) {
                    methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ")  throws Exception {" + line
                            + tab + tab + "handler.invoke(this,Class.forName(\"" + targetClass.getName() + "\").getMethod(\"" + methodName + "\", " + a + ")," + a + ");" + line
                            + tab + "}" + line;
                } else {
                    methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ")  throws Exception {" + line
                            //这里a.getclass只适用与一个参数，多个要分开calss，数组也要   有参数和没有参数也要if else
                            + tab + tab + "return  (" + returnTypeName + ")handler.invoke(this,Class.forName(\"" + targetClass.getName() + "\").getMethod(\"" + methodName + "\", " + a + ".getClass()),new Object[]{" + a + "});" + line
                            + tab + "}" + line;
                }
            }
        }
        classContent = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";
        File file = new File("d:\\com\\yang\\proxy\\service\\impl\\$Proxy.java");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            fw.write(classContent);
            fw.flush();
            fw.close();


            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            URL[] urls = new URL[]{new URL("file:D:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.yang.proxy.service.impl.$Proxy");

            Constructor constructor = clazz.getConstructor(MyJdkInvocationHandler.class);

            proxy = constructor.newInstance(handler);

        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * public UserDaoLog(UserDao target){
         * 		this.target =target;
         *
         *        }
         */
        return proxy;
    }
}

package com.yang.fremark;

import freemarker.template.Template;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 测试邮件Freemarker模版
 * @Author: tona.sun
 * @Date: 2020/08/13 19:37
 */
public class FreemarkerTest {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader(new File("D://no_alert_fre.ftl"));
        Template template = new Template("test", reader, null, "utf-8");

        External external = new External();
        external.setAttr1("attr111");
        external.setAttr2("attr222");
        List<Interior> interiors = new ArrayList();
        interiors.add(new Interior("1","2","3","4"));
        interiors.add(new Interior("11","22","33","44"));
        external.setList(interiors);
        Writer writer = new PrintWriter(System.out);
        template.process(external, writer);
        writer.flush();
        writer.close();
        reader.close();
    }
}

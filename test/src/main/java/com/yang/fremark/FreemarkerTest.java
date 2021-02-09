package com.yang.fremark;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.Template;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

/**
 * @Description: 测试邮件Freemarker模版
 * @Author: tona.sun
 * @Date: 2020/08/13 19:37
 */
public class FreemarkerTest {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader(new File("E://daily_report.ftl"));
        Template template = new Template("test", reader, null, "utf-8");
        FileWriter writer = new FileWriter(new File("E://ftl_test.html"));
        //Writer writer = new PrintWriter(System.out);
        template.process(generateObject2(), writer);
        writer.flush();
        writer.close();
        reader.close();
    }


    private static JSONObject generateObject2() {
        JSONObject result = new JSONObject();
        result.put("isAgency", false);
        JSONArray tableContents = new JSONArray();
        JSONObject table = new JSONObject();
        table.put("violationTypeNames","啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        table.put("violationNote","啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        tableContents.add(table);
        result.put("tableContents", tableContents);
        return result;
    }

    private static JSONObject generateObject1() {
        JSONObject result = new JSONObject();
        result.put("level", 1);
        result.put("gcc", true);
        result.put("violationAdCount", null);
        result.put("nickname", "nickname");
        result.put("influence", "influence");
        result.put("nicknameRad28days", "nicknameRad28days");
        result.put("nicknameSpend28days", "nicknameSpend28days");
        result.put("nicknameRad7days", "nicknameRad7days");
        result.put("clientKind", 1);
        JSONArray tableContents = new JSONArray();
        JSONObject content1 = new JSONObject();
        content1.put("nickname", "nickname");
        content1.put("nicknameRad28days", "nicknameRad28days");
        content1.put("nicknameSpend28days", "nicknameSpend28days");
        content1.put("nicknameRad7days", "nicknameRad7days");
        JSONObject content2 = new JSONObject();
        content2.put("nickname", "nickname");
        content2.put("nicknameRad28days", "nicknameRad28days");
        content2.put("nicknameSpend28days", "nicknameSpend28days");
        content2.put("nicknameRad7days", "nicknameRad7days");
        tableContents.add(content1);
        tableContents.add(content2);
        result.put("tableContents", tableContents);
        JSONArray noticeAccount = new JSONArray();
        JSONObject account1 = new JSONObject();
        account1.put("accountName", "accountName");
        account1.put("accountId", "accountId");
        account1.put("spend28days", "accountSpend28days");
        noticeAccount.add(account1);
        JSONObject account2 = new JSONObject();
        account2.put("accountName", "accountNameaccountName");
        account2.put("accountId", "accountIdaccountName");
        account2.put("spend28days", "accountSpend28days");
        noticeAccount.add(account2);
        result.put("noticeAccount", noticeAccount);
        result.put("showClear", true);
        JSONArray illegalType = new JSONArray();
        illegalType.add("1111");
        illegalType.add("2222");
        illegalType.add("3333");
        result.put("illegalType", illegalType);
        return result;
    }
}

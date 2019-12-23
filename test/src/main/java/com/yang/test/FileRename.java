package com.yang.test;


import org.apache.commons.lang.StringUtils;

import java.io.File;

public class FileRename {

    public static void main(String[] args) throws Exception{
        changeFileName("F:\\学习\\鲁班\\03.框架应用和源码专题(三)");
    }

    /**
     * @param path 文件夹路径
     * @description: 通过文件路径，修改该路径下所有文件的名字
     * @return:
     * @author: William
     * @date 2019/8/8 14:52
     */
    public static void changeFileName(String path) throws Exception{
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        changeFileName(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        String file2Name = file2.getName();
                        if (StringUtils.isNotBlank(file2Name) && file2Name.contains("【瑞客论坛 www.ruike1.com】")) {
                            String filePath = file2.getAbsolutePath();
                            String canonicalPath = file2.getParent();
                            String[] split = file2Name.split("【瑞客论坛 www.ruike1.com】");
                            String newName = canonicalPath+"\\"+split[0]+split[1];
                            File oriFile = new File(filePath);
                            boolean b = oriFile.renameTo(new File(newName));
                            System.out.println(b);
                        }
                    }
                }
            }
        } else {
            System.out.println("该路径不存在");
        }

    }


}

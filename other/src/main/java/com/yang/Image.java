package com.yang;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class Image {
    private int w = 70;
    private int h = 35;
    private Random r = new Random();
    private String[] fontNames = { "宋体", "黑体", "微软雅黑" };
    //1l容易混淆  去掉
    private String codes = "234567890qwertyuiopasdfghjkzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private Color bgColor = new Color(255, 255, 255);
    private String text;

    /**
     * 随机得到一个颜色
     * 
     * @return Color
     */
    private Color randomColor() {
        return new Color(r.nextInt(150), r.nextInt(150), r.nextInt(150));
    }

    /**
     * 随机得到一个字体
     * 
     * @return Font
     */
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = r.nextInt(4);// 黑 粗 斜 粗斜
        int size = r.nextInt(5) + 24;// 随机字号24—28
        return new Font(fontName, style, size);
    }

    /**
     * 随即花num条干扰线
     */
    private void drawLine(BufferedImage image) {
        int num = 8;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            g2.setStroke(new BasicStroke(1.5F));
            // 干扰线的颜色
            g2.setColor(randomColor());
            // 随机起点坐标
            g2.drawLine(r.nextInt(w), r.nextInt(h), r.nextInt(w), r.nextInt(h));
        }
    }

    /**
     * 从codes中随机得到一个字符
     */
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, w, h);
        return image;
    }

    public BufferedImage getImage() {
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * w / 4;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x, h - 5);
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    public String getText() {
        return text;
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
    
    @Test
    public void MyTest(){
        Image img = new Image();
        OutputStream os;
        try {
            os = new FileOutputStream("E:\\testspace\\a.jpg");
            output(img.getImage(),os);
            System.out.println(img.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

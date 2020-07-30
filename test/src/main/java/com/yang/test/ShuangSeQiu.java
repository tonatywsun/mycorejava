package com.yang.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/07/10 15:49
 */
public class ShuangSeQiu {
    public static Integer[] redBall = new Integer[33];
    public static Integer[] blueBall = new Integer[16];
    public static Random random = new Random();

    static {
        for (int i = 1; i <= 33; i++) {
            redBall[i - 1] = i;
        }
        for (int i = 1; i <= 16; i++) {
            blueBall[i - 1] = i;
        }
    }
    private static final Pattern PATTERN = Pattern.compile("^(WHERE|where)\\s+(.*)\\s+(LIMIT|limit)\\s+1;$");
    public static void main(String[] args) {
        String a ="WHERE `google_store_url` IS NULL AND `apple_store_url` IS NULL AND `itunes_url` IS NULL AND `icon_url_small` IS NULL AND `icon_url_big` IS NULL AND `google_store_screenshot_urls` IS NULL AND `google_store_description` IS NULL AND `google_store_categorys` IS NULL AND `apple_store_screenshot_urls` IS NULL AND `apple_store_description` IS NULL AND `app_id` = '1653257488077721' LIMIT 1;";
        Matcher matcher = PATTERN.matcher(a);
        boolean b = matcher.matches();
        System.out.println(b);
       /* int n = 1;
        for (int i = 0; i < n; i++) {
            System.out.println(new ShuangSeQiu().getBall());
        }*/
    }

    public Ball getBall() {
        List<Integer> redBallSelect = new ArrayList();
        List<Integer> redBallList = new ArrayList<>(Arrays.asList(redBall));
        shuffleList(redBallList);
        List<Integer> blueBallList = new ArrayList<>(Arrays.asList(blueBall));
        shuffleList(blueBallList);
        for (int i = 1; i < 7; i++) {
            int randomInt = random.nextInt(redBallList.size());
            redBallSelect.add(redBallList.remove(randomInt));
        }
        redBallSelect.sort(Comparator.comparingInt(a -> a));
        Integer blueBallSelect = blueBallList.get(random.nextInt(blueBallList.size()));
        Ball ball = new Ball(redBallSelect.get(0), redBallSelect.get(1), redBallSelect.get(2), redBallSelect.get(3), redBallSelect.get(4), redBallSelect.get(5), blueBallSelect);
        return ball;
    }

    public void shuffleList(List<Integer> list) {
        Collections.shuffle(list);
        Collections.shuffle(list);
        Collections.shuffle(list);
    }

    static class Ball {
        private Integer red1;
        private Integer red2;
        private Integer red3;
        private Integer red4;
        private Integer red5;
        private Integer red6;
        private Integer blue;

        public Ball() {
        }

        public Ball(Integer red1, Integer red2, Integer red3, Integer red4, Integer red5, Integer red6, Integer blue) {
            this.red1 = red1;
            this.red2 = red2;
            this.red3 = red3;
            this.red4 = red4;
            this.red5 = red5;
            this.red6 = red6;
            this.blue = blue;
        }

        public int getRed1() {
            return red1;
        }

        public void setRed1(Integer red1) {
            this.red1 = red1;
        }

        public int getRed2() {
            return red2;
        }

        public void setRed2(Integer red2) {
            this.red2 = red2;
        }

        public int getRed3() {
            return red3;
        }

        public void setRed3(Integer red3) {
            this.red3 = red3;
        }

        public int getRed4() {
            return red4;
        }

        public void setRed4(Integer red4) {
            this.red4 = red4;
        }

        public int getRed5() {
            return red5;
        }

        public void setRed5(Integer red5) {
            this.red5 = red5;
        }

        public int getRed6() {
            return red6;
        }

        public void setRed6(Integer red6) {
            this.red6 = red6;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(Integer blue) {
            this.blue = blue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ball ball = (Ball) o;
            return Objects.equals(red1, ball.red1) &&
                    Objects.equals(red2, ball.red2) &&
                    Objects.equals(red3, ball.red3) &&
                    Objects.equals(red4, ball.red4) &&
                    Objects.equals(red5, ball.red5) &&
                    Objects.equals(red6, ball.red6) &&
                    Objects.equals(blue, ball.blue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(red1, red2, red3, red4, red5, red6, blue);
        }

        @Override
        public String toString() {
            return red1 + " " + red2 + " " + red3 + " " + red4 + " " + red5 + " " + red6 + " + " + blue;
        }
    }

}

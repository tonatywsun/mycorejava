package com.yang.fremark;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/08/13 19:41
 */
@Data
public class Interior {
    public Interior(String attr1, String attr2, String attr3, String attr4) {
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
    }

    String attr1;
    String attr2;
    String attr3;
    String attr4;
}

package com.yang.fremark;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/08/13 19:41
 */
@Data
public class External {
    String attr1;
    String attr2;
    List<Interior> list;
}

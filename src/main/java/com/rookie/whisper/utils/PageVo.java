package com.rookie.whisper.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WebRookie
 * @date 2023/11/29 19:27
 * @description
 **/
@Data
public class PageVo {
    /**
     * 页面大小
     */
    private Integer pageSize = 10;
    /**
     * 当前页数
     */
    private Integer pageNum = 1;
    /**
     * 查询条件
     */
    private Map<String,Object> param = new HashMap<>();
}

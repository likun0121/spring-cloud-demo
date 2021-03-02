package com.lk.order.util;

import com.lk.order.vo.ResultVO;

/**
 * @author LK
 */
public class ResultVOUtil {

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(data);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}

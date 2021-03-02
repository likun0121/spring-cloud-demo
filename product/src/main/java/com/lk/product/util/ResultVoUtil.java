package com.lk.product.util;

import com.lk.product.vo.ProductVO;
import com.lk.product.vo.ResultVO;

import java.util.List;

/**
 * @author LK
 */
public class ResultVoUtil {

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(data);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

}

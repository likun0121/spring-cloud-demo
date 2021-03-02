package com.lk.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author LK
 */
@Data
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编码 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}

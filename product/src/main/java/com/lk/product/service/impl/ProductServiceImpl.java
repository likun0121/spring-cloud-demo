package com.lk.product.service.impl;

import com.lk.product.dataobject.ProductInfo;
import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;
import com.lk.product.enums.ProductStatusEnum;
import com.lk.product.enums.ResultEnum;
import com.lk.product.exception.ProductException;
import com.lk.product.repository.ProductInfoRepository;
import com.lk.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author LK
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoDTO> listProductByIds(List<String> productIdList) {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductIdIn(productIdList);
        return productInfoList.stream().map(e -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(e, productInfoDTO);
            return productInfoDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(cartDTO.getProductId());
            ProductInfo productInfo = optionalProductInfo.orElseThrow(() -> new ProductException(ResultEnum.PRODUCT_NOT_EXIST));
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_LACKING);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}

package com.wmj.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wmj.mmall.entity.Product;
import com.wmj.mmall.entity.ProductCategory;
import com.wmj.mmall.entity.ProductCategoryVo;
import com.wmj.mmall.entity.ProductVo;
import com.wmj.mmall.mapper.ProductCategoryMapper;
import com.wmj.mmall.mapper.ProductMapper;
import com.wmj.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wmj.mmall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper mapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductCategoryVo> getAllProductCategoryVo() {

        QueryWrapper wrapper = new QueryWrapper();
        //查询一级
        wrapper.eq("type",1);
        List<ProductCategory> levelOne = mapper.selectList(wrapper);
        //把一级的东西转到Vo里  正经for方法
//        List<ProductCategoryVo> leveOneOv = new ArrayList<>();
//        for (ProductCategory productCategory:levelOne){
//            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
//            BeanUtils.copyProperties(productCategory,productCategoryVo);
//            leveOneOv.add(productCategoryVo);
//        }

        //把一级的东西转到Vo里  我没见识过的人家用的方法
        //ProductCategoryVo(e.getId(),e.getName()))  需要在实体类中定义对应的构造方法
        List<ProductCategoryVo> levelOneVo = levelOne.stream()
                .map(e -> new ProductCategoryVo(e.getId(),e.getName(),e.getBanner(),e.getType(),e.getTop())).collect(Collectors.toList());

        //根据一级查到的id 去product里查询价格等信息
        for (int i = 0;i<levelOneVo.size();i++){
            wrapper = new QueryWrapper();
            wrapper.eq("categorylevelone_id",levelOneVo.get(i).getId());
            List<Product> list = productMapper.selectList(wrapper);
            List<ProductVo> productVos = list.stream()
                    .map(e -> new ProductVo(e.getId(),e.getName(),e.getPrice(),e.getFileName())).collect(Collectors.toList());
            levelOneVo.get(i).setProductVoList(productVos);
        }


        //根据一级查询查到的id 查出二级的parent_id
        for (ProductCategoryVo productCategoryVo:levelOneVo){
            wrapper = new QueryWrapper();
            wrapper.eq("type",2);
            wrapper.eq("parent_id",productCategoryVo.getId());
            List<ProductCategory> levelTwo = mapper.selectList(wrapper);
            List<ProductCategoryVo> levelTwoVo = levelTwo.stream()
                    .map(e -> new ProductCategoryVo(e.getId(),e.getName(),e.getBanner(),e.getType(),e.getTop())).collect(Collectors.toList());
            productCategoryVo.setChildren(levelTwoVo);

            // 根据二级查询查到的id 查出三级的parent_id
            for (ProductCategoryVo productCategoryVo1:levelTwoVo){
                wrapper = new QueryWrapper();
                wrapper.eq("type",3);
                wrapper.eq("parent_id",productCategoryVo1.getId());
                List<ProductCategory> levelThere = mapper.selectList(wrapper);
                List<ProductCategoryVo> levelThereVo = levelThere.stream()
                        .map(e -> new ProductCategoryVo(e.getId(),e.getName(),e.getBanner(),e.getType(),e.getTop())).collect(Collectors.toList());
                productCategoryVo1.setChildren(levelThereVo);
            }
        }


        return levelOneVo;
    }
}

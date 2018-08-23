package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-05-07 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        //ProductCategory productCategory0 = repository.findOne(1);
        //ProductCategory productCategory = repository.getOne(1);
        ProductCategory productCategory1 = repository.findById(1).get();
        //System.out.println(productCategory.toString());
        System.out.println(productCategory1);
    }

    @Test
    //@Transactional  //测试时 完全回滚
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("女生最爱",4);
        ProductCategory result = repository.save(productCategory);
        //Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(10,11);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        System.out.println(result);
        Assert.assertNotEquals(0, result.size());
    }
}
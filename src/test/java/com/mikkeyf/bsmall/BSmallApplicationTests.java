package com.mikkeyf.bsmall;

import com.mikkeyf.bsmall.dao.CategoryMapper;
import com.mikkeyf.bsmall.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BSmallApplicationTests {

	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	void contextLoads() {
		Category category = (Category)categoryMapper.selectById(100001);
		System.out.println(category);
	}

}

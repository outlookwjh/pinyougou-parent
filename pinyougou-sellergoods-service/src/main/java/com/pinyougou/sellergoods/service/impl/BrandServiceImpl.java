package com.pinyougou.sellergoods.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TbBrandMapper brandMapper;
	
	@Override
	public List<TbBrand> findAll() {

		List<TbBrand> tbBrands = brandMapper.selectByExample(null);

		return tbBrands;
	}

	@Override
	public List<Map<String, String>> getBrandOptions() {
		return brandMapper.specOptions();
	}


	@Override
	public PageResult findPage(TbBrand brand,int pageNum, int pageSize) {

		PageHelper.startPage(pageNum,pageSize);

		TbBrandExample example =  new TbBrandExample();

		TbBrandExample.Criteria criteria = example.createCriteria();

		//模糊查询
		if (!Objects.isNull(brand))
		{

			if (!StringUtil.isEmpty(brand.getFirstChar())){

				criteria.andFirstCharLike(brand.getFirstChar());
			}
			if (!StringUtil.isEmpty(brand.getName())){

				criteria.andNameLike("%"+brand.getName()+"%");
			}
		}

		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);

		List<TbBrand> rows = page.getResult();

		long total = page.getTotal();

		return new PageResult(total,rows);
	}

	@Override
	public void addBrand(TbBrand brand) {

		brandMapper.insert(brand);
	}

	@Override
	@Transactional
	public void modifyBrand(TbBrand brand) {

		TbBrand tbBrand = brandMapper.selectByPrimaryKey(brand.getId());
		tbBrand.setName(null == brand.getName() ? tbBrand.getName() : brand.getName());
		tbBrand.setFirstChar(null == brand.getFirstChar() ? tbBrand.getFirstChar() : brand.getFirstChar());

		brandMapper.updateByPrimaryKey(tbBrand);

	}

	@Override
	public TbBrand findBrandById(String id) {
		TbBrand tbBrand = brandMapper.selectByPrimaryKey(Long.valueOf(id));
		return tbBrand;
	}

	@Override
	public void batchDel(String[] ids) {

		brandMapper.batchDel(Arrays.asList(ids));

	}

}

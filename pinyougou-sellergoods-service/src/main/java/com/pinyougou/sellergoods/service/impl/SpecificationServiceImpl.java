package com.pinyougou.sellergoods.service.impl;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Specification;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;



/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper optionMapper;

	private TbTypeTemplateMapper typeTemplateMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {

		TbSpecification spec = specification.getSpec();

		List<TbSpecificationOption> specList = specification.getSpecList();

		specificationMapper.insert(spec);

		specList.stream().forEach(e->e.setSpecId(spec.getId()));

		specList.stream().forEach(e->optionMapper.insert(e));

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){

		TbSpecification spec = specification.getSpec();

		specificationMapper.updateByPrimaryKey(spec);

		List<TbSpecificationOption> specList = specification.getSpecList();

		TbSpecificationOptionExample example = new TbSpecificationOptionExample();

		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();

		criteria.andSpecIdEqualTo(spec.getId());

		optionMapper.deleteByExample(example);

		specList.stream().forEach(e->e.setSpecId(spec.getId()));

		specList.stream().forEach(e->optionMapper.insert(e));
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		TbSpecification spec = specificationMapper.selectByPrimaryKey(id);

		TbSpecificationOptionExample example = new TbSpecificationOptionExample();

		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();

		criteria.andSpecIdEqualTo(spec.getId());


		List<TbSpecificationOption> options = optionMapper.selectByExample(example);


		return new Specification(spec,options);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {

		Arrays.stream(ids).forEach(e->specificationMapper.deleteByPrimaryKey(e));

		TbSpecificationOptionExample example = new TbSpecificationOptionExample();

		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();

		criteria.andSpecIdIn(Arrays.asList(ids));

		optionMapper.deleteByExample(example);
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map<String, String>> getSpecOptions() {
		return specificationMapper.specOptions();
	}



}

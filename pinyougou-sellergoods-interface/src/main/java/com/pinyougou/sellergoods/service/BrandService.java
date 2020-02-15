package com.pinyougou.sellergoods.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;

/**
 * 品牌接口
 * @author Administrator
 *
 */
public interface BrandService {

	/**
	 * 查询所有品牌信息
	 * @return
	 */
	public List<TbBrand> findAll();

	/**
	 * 获取品牌列表
	 * @return
	 */
	public List<Map<String,String>> getBrandOptions();

	/**
	 * 分页查询
	 * @param pageNum 当前页
	 * @param pageSize 当前页展示的大小
	 * @return
	 */
	public PageResult findPage(TbBrand brand,int pageNum,int pageSize);

	/**
	 * 增加品牌
	 */
	public void addBrand(TbBrand brand);

	/**
	 * 修改品牌
	 * @param brand
	 */
	public void modifyBrand(TbBrand brand);


	/**
	 * 根据ID查询品牌信息
	 * @param id
	 * @return
	 */
	public TbBrand findBrandById(String id);

	/**
	 * 批量删除品牌
	 * @param ids
	 */
	public void batchDel(String[] ids);


}

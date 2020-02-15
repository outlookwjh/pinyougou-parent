package com.pinyougou.entity;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

public class Specification implements Serializable{

    private TbSpecification spec;

    private List<TbSpecificationOption> specList;

    public Specification() {
    }

    public Specification(TbSpecification spec, List<TbSpecificationOption> specList) {
        this.spec = spec;
        this.specList = specList;
    }

    public TbSpecification getSpec() {
        return spec;
    }

    public void setSpec(TbSpecification spec) {
        this.spec = spec;
    }

    public List<TbSpecificationOption> getSpecList() {
        return specList;
    }

    public void setSpecList(List<TbSpecificationOption> specList) {
        this.specList = specList;
    }
}

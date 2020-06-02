package com.kyoborealco.kreps.model.dto;

import java.util.List;

import com.kyoborealco.kreps.zsite.criteria.Criteria;

import lombok.Data;

@Data 
public class DataTable<T> {

    private int draw;
    private int start;
    private long recordsTotal;
    private long recordsFiltered;
    private Criteria searchCriteria;
    private List<T> data;
}

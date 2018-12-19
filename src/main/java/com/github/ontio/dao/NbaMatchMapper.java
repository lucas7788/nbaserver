package com.github.ontio.dao;

import com.github.ontio.model.NbaMatch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Mapper
@Component(value = "NbaMatchMapper")
public interface NbaMatchMapper {

    int insertNbaMatch(NbaMatch nbaMatch);
    List<Map> selectNbaMatchByDate(String date);
    int updateMatchStateByDate(String state, String date);
}

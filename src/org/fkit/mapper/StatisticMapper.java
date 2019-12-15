package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.mapping.FetchType;

import othersPOJO.StatisCategoryInfo;

public interface StatisticMapper {
	
	@Select("select category, count(*) as num  from tb_music group by category")
	@Results({
		@Result(column="category", property="category"),
		@Result(column="num", property="musicNum")
	})
	List<StatisCategoryInfo> getStatisticCategoryAndNum();

}

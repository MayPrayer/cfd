package com.hbnu.dao;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Informs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: IInforms <br/>
 * Description: <br/>
 * date: 2020/3/14 12:07<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IInforms {

    List<Informs> selectInformSortBytime( int id);

    int insertOneInform(@Param("title")String title, @Param("content") String content, @Param("shopid") int shopid, @Param("important") int important);

}

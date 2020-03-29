package com.hbnu.dao;

import com.hbnu.entity.Shipaddress;

import java.util.List;

/**
 * ClassName: IShipaddress <br/>
 * Description: <br/>
 * date: 2020/3/28 9:17<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IShipaddress {

    List<Shipaddress> selectaddrById(int id);
}

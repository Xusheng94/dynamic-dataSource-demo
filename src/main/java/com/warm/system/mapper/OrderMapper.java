package com.warm.system.mapper;

import com.warm.common.DBTypeEnum;
import com.warm.common.DataSourceSwitch;
import com.warm.system.entity.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dgd123
 * @since 2018-02-10
 */

@DataSourceSwitch(DBTypeEnum.db2)
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT price from t_order where user_id =#{userId}")
    BigDecimal getPriceByUserId(Integer userId);
}

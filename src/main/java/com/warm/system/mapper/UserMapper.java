package com.warm.system.mapper;

import com.warm.common.DBTypeEnum;
import com.warm.common.DataSourceSwitch;
import com.warm.system.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dgd123
 * @since 2018-02-10
 */
@DataSourceSwitch(DBTypeEnum.db1)
public interface UserMapper extends BaseMapper<User> {

}

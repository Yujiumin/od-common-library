package cn.fullstack.od.common.db.service;

import cn.fullstack.od.common.db.entity.RoleUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @date 2024/12/4
 */
public interface RoleUserService extends IService<RoleUserEntity> {

    void updateRoleUser(String roleId, List<String> userIds);

}

package cn.fullstack.od.common.db.service;

import cn.fullstack.od.common.db.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @date 2024/12/4
 */
public interface RoleService extends IService<RoleEntity> {

    boolean checkRoleCodeExists(String roleCode);

    void createRole(String roleCode, String roleName);

}

package cn.fullstack.od.common.db.service;

import cn.fullstack.od.common.db.entity.RoleAuthorityEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @date 2024/12/5
 */
public interface RoleAuthorityService extends IService<RoleAuthorityEntity> {

    void updateRoleAuthority(String roleId, List<String> authorities);

}

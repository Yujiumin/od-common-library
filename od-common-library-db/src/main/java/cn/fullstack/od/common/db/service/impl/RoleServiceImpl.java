package cn.fullstack.od.common.db.service.impl;

import cn.fullstack.od.common.core.exception.OperationFailedException;
import cn.fullstack.od.common.db.entity.RoleEntity;
import cn.fullstack.od.common.db.mapper.RoleMapper;
import cn.fullstack.od.common.db.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date 2024/12/4
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    private static final String CREATE_ROLE_FAILED_MESSAGE_TEMPLATE = "save role data to db failed for role: [%s:%s]";

    @Override
    public boolean checkRoleCodeExists(String roleCode) {
        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleEntity::getRoleCode, roleCode);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public void createRole(String roleCode, String roleName) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleCode(roleCode);
        roleEntity.setRoleName(roleName);
        if (save(roleEntity)) {
            log.debug("save role data to db successfully for role: [{}:{}]", roleCode, roleName);
        } else {
            throw new OperationFailedException(String.format(CREATE_ROLE_FAILED_MESSAGE_TEMPLATE, roleCode, roleName));
        }
    }
}

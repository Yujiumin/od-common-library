package cn.fullstack.od.common.db.service.impl;

import cn.fullstack.od.common.core.exception.OperationFailedException;
import cn.fullstack.od.common.db.entity.RoleUserEntity;
import cn.fullstack.od.common.db.mapper.RoleUserMapper;
import cn.fullstack.od.common.db.service.RoleUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2024/12/4
 */
@Slf4j
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUserEntity> implements RoleUserService {

    private static final String DELETE_ROLE_USER_FAILED_MESSAGE_TEMPLATE = "delete all users failed for role id: [%s]";
    private static final String UPDATE_ROLE_USER_FAILED_MESSAGE_TEMPLATE = "update role user data to db failed for the roleId: [%s], userIds: [%s]";

    @Override
    @Transactional
    public void updateRoleUser(String roleId, List<String> userIds) {
        deleteAllUserForRole(roleId);
        List<RoleUserEntity> roleUserEntityList = userIds.parallelStream()
                .map(userId -> {
                    RoleUserEntity roleUserEntity = new RoleUserEntity();
                    roleUserEntity.setRoleId(roleId);
                    roleUserEntity.setUserId(userId);
                    return roleUserEntity;
                })
                .collect(Collectors.toList());
        if (saveBatch(roleUserEntityList)) {
            log.debug("update role user data to db successfully for the roleId: [{}], userIds: [{}]", roleId, userIds);
        } else {
            throw new OperationFailedException(String.format(UPDATE_ROLE_USER_FAILED_MESSAGE_TEMPLATE, roleId, userIds));
        }
    }

    private void deleteAllUserForRole(String roleId) {
        LambdaQueryWrapper<RoleUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleUserEntity::getRoleId, roleId);
        if (remove(queryWrapper)) {
            log.debug("delete all user successfully for role id: [{}]", roleId);
        } else {
            throw new OperationFailedException(String.format(DELETE_ROLE_USER_FAILED_MESSAGE_TEMPLATE, roleId));
        }
    }
}

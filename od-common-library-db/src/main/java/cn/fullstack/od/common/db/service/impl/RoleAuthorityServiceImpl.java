package cn.fullstack.od.common.db.service.impl;

import cn.fullstack.od.common.core.exception.OperationFailedException;
import cn.fullstack.od.common.db.entity.RoleAuthorityEntity;
import cn.fullstack.od.common.db.mapper.RoleAuthorityMapper;
import cn.fullstack.od.common.db.service.RoleAuthorityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2024/12/5
 */
@Slf4j
@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthorityEntity> implements RoleAuthorityService {

    private static final String DELETE_ROLE_AUTHORITY_FAILED_MESSAGE_TEMPLATE = "delete all authorities failed for role id: [%s]";
    private static final String UPDATE_ROLE_AUTHORITY_FAILED_MESSAGE_TEMPLATE = "update role authorities data to db failed for the role id:%s, authority ids:%s";

    @Override
    @Transactional
    public void updateRoleAuthority(String roleId, List<String> authorityIds) {
        deleteAllAuthoritiesForRole(roleId);
        List<RoleAuthorityEntity> roleAuthorityEntityList = authorityIds.parallelStream()
                .map(authorityId -> {
                    RoleAuthorityEntity roleAuthorityEntity = new RoleAuthorityEntity();
                    roleAuthorityEntity.setRoleId(roleId);
                    roleAuthorityEntity.setAuthorityId(authorityId);
                    return roleAuthorityEntity;
                }).collect(Collectors.toList());
        if (saveBatch(roleAuthorityEntityList)) {
            log.debug("update role authorities to db successfully for the roleId: {}, authorityIds: {}", roleId, authorityIds);
        } else {
            throw new OperationFailedException(String.format(UPDATE_ROLE_AUTHORITY_FAILED_MESSAGE_TEMPLATE, roleId, authorityIds));
        }
    }

    private void deleteAllAuthoritiesForRole(String roleId) {
        LambdaQueryWrapper<RoleAuthorityEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleAuthorityEntity::getRoleId, roleId);
        if (remove(queryWrapper)) {
            log.debug("delete all authorities successfully for role id: {}", roleId);
        } else {
            throw new OperationFailedException(String.format(DELETE_ROLE_AUTHORITY_FAILED_MESSAGE_TEMPLATE, roleId));
        }
    }
}

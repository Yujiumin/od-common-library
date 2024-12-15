package cn.fullstack.od.common.db.service.impl;

import cn.fullstack.od.common.core.exception.BadOperationException;
import cn.fullstack.od.common.core.exception.OperationFailedException;
import cn.fullstack.od.common.db.entity.AuthorityEntity;
import cn.fullstack.od.common.db.mapper.AuthorityMapper;
import cn.fullstack.od.common.db.service.AuthorityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date 2024/12/5
 */
@Slf4j
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AuthorityEntity> implements AuthorityService {

    private static final String AUTHORITY_KEY_EXISTED_MESSAGE_TEMPLATE = "the given authority key has been existed: [%s]";
    private static final String CREATE_AUTHORITY_FAILED_MESSAGE_TEMPLATE = "save authority data to db failed for the authority: [%s:%s]";

    @Override
    public void createAuthority(String authorityKey, String authorityName) {
        if (checkAuthorityKeyExists(authorityKey)) {
            throw new BadOperationException(String.format(AUTHORITY_KEY_EXISTED_MESSAGE_TEMPLATE, authorityKey));
        }
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthorityKey(authorityKey);
        authorityEntity.setAuthorityName(authorityName);
        if (save(authorityEntity)) {
            log.debug("save authority data to db successfully for the authority: [{}:{}]", authorityKey, authorityName);
        } else {
            throw new OperationFailedException(String.format(CREATE_AUTHORITY_FAILED_MESSAGE_TEMPLATE, authorityKey, authorityName));
        }
    }

    @Override
    public boolean checkAuthorityKeyExists(String authorityKey) {
        LambdaQueryWrapper<AuthorityEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthorityEntity::getAuthorityKey, authorityKey);
        return baseMapper.exists(queryWrapper);
    }
}

package cn.fullstack.od.common.db.service.impl;

import cn.fullstack.od.common.core.exception.BadOperationException;
import cn.fullstack.od.common.core.exception.OperationFailedException;
import cn.fullstack.od.common.db.entity.UserAuthEntity;
import cn.fullstack.od.common.db.mapper.UserAuthMapper;
import cn.fullstack.od.common.db.model.UserState;
import cn.fullstack.od.common.db.service.UserAuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @date 2024/11/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuthEntity> implements UserAuthService {

    private static final String CREATE_USER_AUTH_FAILED_MESSAGE_TEMPLATE = "save user auth data to db failed for username: [%s]";
    private static final String UPDATE_PASSWORD_FAILED_MESSAGE_TEMPLATE = "update login password failed for user id: [%s]";
    private static final String USERNAME_EXISTED_MESSAGE_TEMPLATE = "the username has been existed: [%s]";

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkUsernameExists(String username) {
        LambdaQueryWrapper<UserAuthEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAuthEntity::getUsername, username);
        return exists(queryWrapper);
    }

    @Override
    public UserAuthEntity checkUserAuth(String username, String password) {
        String encryptPassword = encryptPassword(password);
        LambdaQueryWrapper<UserAuthEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAuthEntity::getUsername, username);
        queryWrapper.eq(UserAuthEntity::getPassword, encryptPassword);
        return getOne(queryWrapper);
    }

    @Override
    public String createUserAuthEntity(String username) {
        if (checkUsernameExists(username)) {
            throw new BadOperationException(String.format(USERNAME_EXISTED_MESSAGE_TEMPLATE, username));
        }
        String randomPassword = RandomStringUtils.randomAlphabetic(16);
        String encryptPassword = encryptPassword(randomPassword);
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setUsername(username);
        userAuthEntity.setPassword(encryptPassword);
        userAuthEntity.setUserState(UserState.ENABLED);
        if (save(userAuthEntity)) {
            log.debug("save user auth data to db successfully for username: [{}]", username);
            return randomPassword;
        } else {
            throw new OperationFailedException(String.format(CREATE_USER_AUTH_FAILED_MESSAGE_TEMPLATE, username));
        }
    }

    @Override
    public void updateLoginPassword(String id, String password) {
        LambdaUpdateWrapper<UserAuthEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(UserAuthEntity::getPassword, encryptPassword(password));
        updateWrapper.eq(UserAuthEntity::getId, id);
        if (update(updateWrapper)) {
            log.debug("update login password successfully for user id: [{}]", id);
        } else {
            throw new OperationFailedException(String.format(UPDATE_PASSWORD_FAILED_MESSAGE_TEMPLATE, id));
        }
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

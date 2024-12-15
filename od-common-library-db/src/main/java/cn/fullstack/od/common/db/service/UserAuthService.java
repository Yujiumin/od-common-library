package cn.fullstack.od.common.db.service;

import cn.fullstack.od.common.db.entity.UserAuthEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @date 2024/11/26
 */
public interface UserAuthService extends IService<UserAuthEntity> {

    /**
     * check if the given username exists
     *
     * @param username given username for login
     * @return if exists then true, otherwise false
     */
    boolean checkUsernameExists(String username);

    /**
     * check if the password is correct for the given username
     *
     * @param username given username for login
     * @param password given password for login
     * @return if correct then return the user auth info, otherwise return null
     */
    UserAuthEntity checkUserAuth(String username, String password);

    String createUserAuthEntity(String username);

    void updateLoginPassword(String id, String password);

}

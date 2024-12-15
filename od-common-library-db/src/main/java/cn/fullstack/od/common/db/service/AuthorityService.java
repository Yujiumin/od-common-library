package cn.fullstack.od.common.db.service;

import cn.fullstack.od.common.db.entity.AuthorityEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @date 2024/12/5
 */
public interface AuthorityService extends IService<AuthorityEntity> {

    void createAuthority(String authorityKey, String authorityName);

    boolean checkAuthorityKeyExists(String authorityKey);

}

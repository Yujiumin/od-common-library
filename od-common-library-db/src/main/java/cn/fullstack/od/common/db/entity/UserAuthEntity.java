package cn.fullstack.od.common.db.entity;

import cn.fullstack.od.common.db.entity.base.BaseEntity;
import cn.fullstack.od.common.db.model.UserState;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2024/11/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_auth")
public class UserAuthEntity extends BaseEntity {

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("state")
    private UserState userState;


}

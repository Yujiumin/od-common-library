package cn.fullstack.od.common.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @date 2024/12/4
 */
@Data
@TableName("sys_role_user")
public class RoleUserEntity {

    @TableField("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;
}

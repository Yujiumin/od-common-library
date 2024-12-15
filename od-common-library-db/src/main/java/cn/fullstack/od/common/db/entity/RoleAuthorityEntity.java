package cn.fullstack.od.common.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @date 2024/12/5
 */
@Data
@TableName("sys_role_authority")
public class RoleAuthorityEntity {

    @TableField("role_id")
    private String roleId;

    @TableField("authority_id")
    private String authorityId;

}

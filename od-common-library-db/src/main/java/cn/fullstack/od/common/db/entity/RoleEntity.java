package cn.fullstack.od.common.db.entity;

import cn.fullstack.od.common.db.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2024/12/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class RoleEntity extends BaseEntity {

    @TableField("role_code")
    private String roleCode;

    @TableField("role_name")
    private String roleName;

}

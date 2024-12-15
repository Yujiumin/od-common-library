package cn.fullstack.od.common.db.entity;

import cn.fullstack.od.common.db.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date 2024/12/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_authority")
public class AuthorityEntity extends BaseEntity {

    @TableField("authority_key")
    private String authorityKey;

    @TableField("authority_name")
    private String authorityName;

}

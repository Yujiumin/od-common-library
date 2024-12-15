package cn.fullstack.od.common.db.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * @date 2024/11/26
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.DATE)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.DATE)
    private LocalDateTime updateTime;
}

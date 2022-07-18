package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * postgresql table 相关信息 数据传输模型
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
@Data
@Accessors(chain = true)
public class PostgresqlTableDTO {

    /**
     * 表名称
     */
    @TableField(value = "table_name")
    private String tableName;

    /**
     * 表注释
     */
    @TableField(value = "table_comment")
    private String tableComment;

}

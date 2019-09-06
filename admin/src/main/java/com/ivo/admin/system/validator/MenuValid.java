package com.ivo.admin.system.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 菜单数据效验
 * @Author: wj
 * @Date: 2019-07-16 13:41
 * @Version 1.0
 */
@Data
public class MenuValid {

    @NotNull(message = "上级菜单不能不选择")
    private Long pid;

    @NotEmpty(message = "菜单名称不能为空")
    private String title;

    @NotEmpty(message = "url不能为空")
    private String url;

    @NotEmpty(message = "权限标识不能为空")
    private String perms;

    @NotEmpty(message = "图标不能为空")
    private String icon;

    @NotNull(message = "需要选择类型")
    private Byte type;

    @NotNull(message = "需要选择排序")
    private Integer sort;
}

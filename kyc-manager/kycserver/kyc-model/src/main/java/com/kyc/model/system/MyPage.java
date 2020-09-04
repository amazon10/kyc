package com.kyc.model.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {

    private static final long serialVersionUID = -7058815254297832910L;

    private Integer selectInt;
    private String selectStr;
    private String name;

    public MyPage(long count, long size) {
        super(count, size);
    }
}

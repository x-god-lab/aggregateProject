package com.xin.gateway.component;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.xin.vo.AggregateAdminVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xin
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        AggregateAdminVO admin = (AggregateAdminVO) StpUtil.getSession().get("admin");
        return admin.getPermissionList();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        AggregateAdminVO admin = (AggregateAdminVO) StpUtil.getSession().get("admin");
        return admin.getMenuIdList();
    }
}

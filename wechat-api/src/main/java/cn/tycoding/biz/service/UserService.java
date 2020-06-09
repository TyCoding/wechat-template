package cn.tycoding.biz.service;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.biz.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/9
 */
public interface UserService extends IService<SysUser> {

    SysUser findByName(String username);

    IPage<SysUser> list(SysUser sysUser, QueryPage queryPage);

    List<SysUser> list(SysUser sysUser);

    void add(SysUser sysUser);

    void update(SysUser sysUser);

    void delete(Long id);
}

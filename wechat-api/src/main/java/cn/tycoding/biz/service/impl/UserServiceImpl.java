package cn.tycoding.biz.service.impl;

import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.common.utils.MD5Util;
import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.biz.entity.SysUser;
import cn.tycoding.biz.mapper.UserMapper;
import cn.tycoding.biz.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void add(SysUser sysUser) {
        SysUser user = this.findByName(sysUser.getUsername());
        if (user != null) {
            throw new GlobalException("该用户名已存在");
        }
        // 加密
        String encrypt_password = MD5Util.encryptPassword(sysUser.getUsername(), sysUser.getPassword());
        sysUser.setPassword(encrypt_password);
        userMapper.insert(sysUser);
    }

    @Override
    public IPage<SysUser> list(SysUser sysUser, QueryPage queryPage) {
        IPage<SysUser> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysUser.getUsername()), SysUser::getUsername, sysUser.getUsername());
        queryWrapper.orderByDesc(SysUser::getId);
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(sysUser.getUsername()), SysUser::getUsername, sysUser.getUsername());
        queryWrapper.orderByDesc(SysUser::getId);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        if (sysUser.getPassword() != null && sysUser.getUsername() != null) {
            // 加密
            String encrypt_password = MD5Util.encryptPassword(sysUser.getUsername(), sysUser.getPassword());
            sysUser.setPassword(encrypt_password);
        } else {
            sysUser.setPassword(null);
        }
        userMapper.updateById(sysUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        List<SysUser> list = userMapper.selectList(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }
}

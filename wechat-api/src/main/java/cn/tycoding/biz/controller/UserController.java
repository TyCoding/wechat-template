package cn.tycoding.biz.controller;

import cn.tycoding.common.controller.BaseController;
import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.common.utils.R;
import cn.tycoding.biz.entity.SysUser;
import cn.tycoding.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tycoding
 * @date 2020/6/9
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public R getInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", this.getSession().getId());
        map.put("user", this.getCurrentUser());
        return new R<>(map);
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        return new R<>(userService.getById(id));
    }

    @PostMapping("/list")
    public R findByPage(@RequestBody SysUser sysUser, QueryPage queryPage) {
        return new R<>(super.getData(userService.list(sysUser, queryPage)));
    }

    @GetMapping("/findByName")
    public R findByName(String name) {
        return new R<>(userService.findByName(name));
    }

    @PostMapping("/filter/list")
    public R list(@RequestBody SysUser sysUser) {
        return new R<>(userService.list(sysUser));
    }

    @PostMapping
    public R save(@RequestBody SysUser sysUser) {
        try {
            userService.add(sysUser);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    public R update(@RequestBody SysUser sysUser) {
        try {
            userService.update(sysUser);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}

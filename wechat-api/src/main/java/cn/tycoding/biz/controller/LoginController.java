package cn.tycoding.biz.controller;

import cn.tycoding.common.controller.BaseController;
import cn.tycoding.common.utils.MD5Util;
import cn.tycoding.common.utils.R;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tycoding
 * @date 2020/6/9
 */
@RestController
public class LoginController extends BaseController {

    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public R login(@RequestParam(value = "username", required = false) String username,
                   @RequestParam(value = "password", required = false) String password) {
        Subject subject = getSubject();
        String encrypt_password = MD5Util.encryptPassword(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, encrypt_password);
        try {
            subject.login(token);

            Map<String, Object> map = new HashMap<>();
            map.put("token", subject.getSession().getId());
            map.put("user", this.getCurrentUser());
            return new R<>(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new R<>(e);
        }
    }

    /**
     * 注销接口
     *
     * @return
     */
    @DeleteMapping(value = "/logout")
    public R logout() {
        Subject subject = getSubject();
        subject.logout();
        return new R();
    }
}

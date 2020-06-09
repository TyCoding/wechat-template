package cn.tycoding.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tycoding
 * @date 2020/6/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5UtilTest {

    @Test
    public void encryptPassword() {
        String encryptPassword = MD5Util.encryptPassword("tycoding", "123456");
        System.out.println(encryptPassword); // 250b8460be2cf5c3d93938074b63f8e0
    }
}

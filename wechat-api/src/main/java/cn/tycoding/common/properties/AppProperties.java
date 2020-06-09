package cn.tycoding.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tycoding
 * @date 2020/6/9
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:tycoding.properties"})
@ConfigurationProperties(prefix = "tycoding")
public class AppProperties {

    private ShiroProperties shiro = new ShiroProperties();
}

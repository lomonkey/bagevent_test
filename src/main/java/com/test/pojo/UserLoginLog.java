package com.test.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * description       ：
 *   `login_id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `user_id` bigint(20) DEFAULT NULL,
 *   `login_time` datetime DEFAULT NULL,
 *   `login_ip` varchar(29) DEFAULT NULL,
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 0:58
 */
@Data
@Table(name = "user_login_log")
public class UserLoginLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;

    private Long userId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    private String loginIp;

}

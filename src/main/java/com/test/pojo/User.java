package com.test.pojo;

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
 *
         user_id bigint auto_increment primary key,
         user_name varchar(18),
         email varchar(50),
         cellphone varchar(20),
         password varchar(32),
         salt varchar(32),
         state char(1),
         create_time datetime
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 0:58
 */
@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Length(min = 4, max = 18, message = "用户名只能在4~18位之间")
    private String userName;

    @NotBlank
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String cellphone;

    @NotBlank
    private String password;

    private String salt;
    private int state;
    private Date createTime;
}

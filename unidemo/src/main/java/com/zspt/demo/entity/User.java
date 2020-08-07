package com.zspt.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: demo
 * @description
 * @author: chenshuofang
 * @create: 2020-08-07 09:28
 **/
@Entity
@Data
@Table(name = "t_user")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "open_id")
    private String openId;
    private String skey;
    @JoinColumn(name = "create_time")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JoinColumn(name = "last_visit_time")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastVisitTime;
    @JoinColumn(name = "session_key")
    private String sessionKey;
    private String city;
    private String province;
    private String country;
    @JoinColumn(name = "avatar_url")
    private String avatarUrl;
    private Integer gender;
    @JoinColumn(name = "nick_name")
    private String nickName;

}

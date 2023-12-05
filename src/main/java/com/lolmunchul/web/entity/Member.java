package com.lolmunchul.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Member {

    private Integer id;
    private String email;
    private String password;
//    private Integer roleId;
    private String role;
    private String username;
    private String nickname;


}

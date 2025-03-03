package com.nexblue.nbms.web.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
    private String compCd;
    private String userId;
    private String userNm;
    private String passId;
    private String hqCd;
    private String armCd;
    private String deptCd;
    private String positionCd;
    private String rankCd;
    private String empId;
    private String email;
    private String hpNo;
    private String workSts;
    private String useYn;
    private String authLv;
    private LocalDateTime createDate;
    private String createUser;
    private LocalDateTime updateDate;
    private String updateUser;
}

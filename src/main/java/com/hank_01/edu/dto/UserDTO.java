package com.hank_01.edu.dto;

import com.hank_01.edu.Entity.UserEntity;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.enums.UserStatus;

import java.util.Date;

public class UserDTO {
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String sex;
    private UserStatus status;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    public  UserEntity convert2Entity(){
        UserEntity entity = new UserEntity();
        if (this.getId() != null){
            entity.setId(this.getId());
        }
        if (this.getUserName() != null){
            entity.setUserName(this.getUserName());
        }
        if (this.getNickName()!= null){
            entity.setNickName(this.getNickName());
        }
        if (this.getPassword() != null){
            entity.setPassword(this.getPassword());
        }
        if (this.getPhone() != null){
            entity.setPhone(this.getPhone());
        }
        if (this.getSex() != null){
            entity.setSex(this.getSex());
        }
        if (this.getStatus() != null){
            entity.setStatus(this.getStatus());
        }
        if (this.getCreateTime()!= null){
            entity.setCreateTime(this.getCreateTime());
        }
        if (this.getUpdateTime() != null){
            entity.setUpdateTime(this.getUpdateTime());
        }
        if (this.getDeleted() == null){
            entity.setDeleted(this.getDeleted());
        }
        return entity;
    }
    public UserDTO(){
        super();
    }

    public void convertfrom(UserEntity entity){
        if (entity == null){
            return ;
        }
        BeanUtil.copyProperties(entity,this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}

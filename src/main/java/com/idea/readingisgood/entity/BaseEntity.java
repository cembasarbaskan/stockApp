package com.idea.readingisgood.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -5061884889428067176L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonIgnore
    protected String id;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    protected Date changeTime;

    @JsonIgnore
    protected String changeUser;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;

    @JsonIgnore
    protected String createUser;

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * On pre update set change user/time.
     */
    @PreUpdate
    protected void onPreUpdate() {
        String user = getCurrentUsername();
        setChangeUser(user);
        Date date = new Date();
        setChangeTime(date);
    }

    /**
     * On pre persist set change/create user/time.
     */
    @PrePersist
    protected void onPrePersist() {
        String user = getCurrentUsername();
        Date date = new Date();
        setChangeTime(date);
        setCreateTime(date);
        setChangeUser(user);
        setCreateUser(user);
    }

    protected String getCurrentUsername() {
        return "user";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

package com.regr.web.database.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by maratische on 22.03.16.
 */
@Entity
@Table(name = "APP_USER")
public class User {
    /** ID - первичный ключ */
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    /** Имя юзера */
    @Column(name = "first_name", length = 25)
    private String firstName;
    /** Фамилия юзера */
    @Column(name = "second_name", length = 25)
    private String secondName;
    /** Никнейм юзера */
    @Column(name = "nick_name", length = 25)
    private String nickName;


    /** Пароль юзера для входа в систему */
    @Column(nullable = false, length = 255)
    private String password;

    /** E-Mail юзера */
    @Column(nullable = false, length = 255, unique = true)
    private String email;
    /** Номер телефона юзера */
    @Column(length = 25)
    private String phone;
    /** Ссылка на изображение аватара юзера */
    @Column(length = 255)
    private String avatar;

    /** Подтвержден ли юзером введенный им E-Mail */
    @Column(name = "email_validated", nullable = false)
    private Boolean emailValidated;
    /** Подтвержден ли юзером введенный им номер телефона */
    @Column(name = "phone_validated")
    private Boolean phoneValidated;

    /** Удален ли данный аккаунт */
    @Column(nullable = false)
    private Boolean deleted;
    /** Дата и время создания юзера */
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    /** Дата и время обновления данных юзера */
    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
    @Column(insertable=false, updatable=false,
            columnDefinition="BigSerial not null")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long identifier;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public long getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(Boolean emailValidated) {
        this.emailValidated = emailValidated;
    }

    public Boolean getPhoneValidated() {
        return phoneValidated;
    }

    public void setPhoneValidated(Boolean phoneValidated) {
        this.phoneValidated = phoneValidated;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}

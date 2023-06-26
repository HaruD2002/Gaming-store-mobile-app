package com.example.prm_project.data.dao.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Date;


@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "username")@NotNull
    private String username;
    @ColumnInfo(name = "first_name")
    private String first_name;
    @ColumnInfo(name = "last_name")
    private String last_name;
    @ColumnInfo(name = "password")@NotNull
    private String password;
    @ColumnInfo(name = "gender")
    private boolean gender;
    @ColumnInfo(name = "phone_number") @NotNull
    private String phone_number;
    @ColumnInfo(name = "DOB")
    private String DOB;
    @ColumnInfo(name = "created_dt")
    private String created_dt;
    @ColumnInfo(defaultValue = "0")
    private int online_status;

    public User() {
    }

    public User(int ID, @NotNull String username, String first_name, String last_name, @NotNull String password, boolean gender, @NotNull String phone_number, String DOB, String created_dt, int online_status) {
        this.ID = ID;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.gender = gender;
        this.phone_number = phone_number;
        this.DOB = DOB;
        this.created_dt = created_dt;
        this.online_status = online_status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @NotNull
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(@NotNull String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }
}

package org.jdbc.model;

import java.util.Date;

public class Student {
    private Integer student_id;
    private String name;
    private String identity_number;

    private Integer portrait_path;

    private Date created_at;
    private Date updated_at;
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }


    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public Integer getPortrait_path() {
        return portrait_path;
    }

    public void setPortrait_path(Integer portrait_path) {
        this.portrait_path = portrait_path;
    }


}

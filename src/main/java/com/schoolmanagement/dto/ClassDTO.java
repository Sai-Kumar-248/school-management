package com.schoolmanagement.dto;

import com.schoolmanagement.entity.Class;

public class ClassDTO {

    private Long id;
    private String name;
    private String section;


    public ClassDTO(Class aClass) {
        this.id = aClass.getId();
        this.name = aClass.getName();
        this.section = aClass.getSection();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

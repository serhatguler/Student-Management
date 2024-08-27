package com.example.entity.enums;

public enum RoleType {
    ADMIN("Admin"), //parantez icini yazmamizin sebebi string ile ulasmka istiyorsak
    TEACHER("Teacher"),
    STUDENT("Student"),
    MANAGER("Dean"),
    ASSISTANT_MANAGER("ViceDean");

    //String ifadelere enum uzerimnden ulasmak istiyorsak degisken olusturalim

    public final String name;
    RoleType(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

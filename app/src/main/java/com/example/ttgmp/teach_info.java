package com.example.ttgmp;

public class teach_info {
    private String teach_name;
    private static String subs;

    public  teach_info(String teach_name ,String subs) {
        this.teach_name = teach_name;
        this.subs = subs;
    }

    public static String getSubs() {
        return subs;
    }

    public String getTeach_name() {
        return teach_name;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }

    public void setTeach_name(String teach_name) {
        this.teach_name = teach_name;
    }
}

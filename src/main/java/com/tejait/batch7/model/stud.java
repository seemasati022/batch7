package com.tejait.batch7.model;

import lombok.Data;

@Data
class Stud{
    String name;

    public static void main(String[] args) {
        Stud s = new Stud();
        s.getName();
                //.add();
    }
}

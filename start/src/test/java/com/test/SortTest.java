package com.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student stu = new Student();
        stu.setAge(20);

        Student stu1 = new Student();
        stu1.setAge(19);

        Student stu2 = new Student();
        stu2.setAge(21);

        list.add(stu);
        list.add(stu1);
        list.add(stu2);
        System.out.println("排序前："+list);

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });

        System.out.println("排序后："+list);
    }
}


@Data
class Student{
    private Integer age;
}

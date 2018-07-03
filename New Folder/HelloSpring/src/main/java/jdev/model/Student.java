/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.model;
/**
 *
 * @author cuongvm
 */
public class Student {

    private String name;
    private int id;

    public Student(int i, String name) {
        this.name = name;
        this.id = i;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return this.id;
    }
}

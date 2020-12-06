package main.entities;

import java.util.ArrayList;
// Menu has a Composition('Has a') relationship with Restaurant
public class Menu {
    private String menuName;
    private ArrayList<Course> listOfCourses;

    // TODO : Delete, this is for testing
    public Menu() {
        
    }

    Menu(String menuName, ArrayList<Course> listOfCourses){
        this.menuName = menuName;
        this.listOfCourses = listOfCourses;
    }

    public String getMenuName() {
        return menuName;
    }
    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }
    public void addCourse(Course course) {
        listOfCourses.add(course);
    }

}

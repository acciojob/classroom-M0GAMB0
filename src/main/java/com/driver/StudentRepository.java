package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentDb = new HashMap<>();
    HashMap<String, Teacher> teacherDb = new HashMap<>();
    HashMap<Student, Teacher> studentTeacherPair = new HashMap<>();
    HashMap<String, List<String>> classroom = new HashMap<>();

    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName) {
        if(classroom.containsKey(teacherName)){
            classroom.get(teacherName).add(studentName);
        }
        else{
            List<String> list=new ArrayList<>();
            list.add(studentName);
            classroom.put(teacherName,list);
        }
        studentTeacherPair.put(studentDb.get(studentName), teacherDb.get(teacherName));
    }

    public Student getStudentByName(String studentName) {
        return studentDb.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName) {
        return teacherDb.get(teacherName);
    }
    public List<String> getStudentsByTeacherName(String teacherName){
        return classroom.get(teacherName);
    }
    public List<String> getAllStudents(){
        return new ArrayList<>(studentDb.keySet());
    }
    public void deleteTeacherByName(String teacherName){
        //deleted from teacherdb
        teacherDb.remove(teacherName);
        List<String> students=classroom.get(teacherName);
        //deleted from classroom
        classroom.remove(teacherName);
        for(String s:students){
            studentTeacherPair.remove(studentDb.get(s));
            studentDb.remove(s);
        }
    }
    public void deleteAllTeachers(){
        for(Student s:studentTeacherPair.keySet()) {
            studentDb.remove(s.getName());
        }
        teacherDb.clear();
        studentTeacherPair.clear();
        classroom.clear();
    }

}

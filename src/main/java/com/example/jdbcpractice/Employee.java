package com.example.jdbcpractice;

import java.io.Serializable;

public class Employee implements Serializable {
        private static final long serialVersionUID = 1L;
        private String id;
        private String firstName;
        private String lastName;
        private int managerId;
        private int departmentId;
        private int salary;
    public String getId()
        {
            return id;
        }
        public String getFirstName()
        {
            return firstName;
        }
        public String getLastName()
        {
            return lastName;
        }
        public int getManagerId()
        {
            return managerId;
        }
        public int getDepartmentId()
        {
            return departmentId;
        }
        public int getSalary(String age)
        {
            return salary;
        }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }
}
package com.advance.java8.ems.service;

import com.advance.java8.ems.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EMSservice {

    public static void main(String[] args) {
        EMSservice ems = new EMSservice();
        List<Employee> employees = ems.loadEmployees();

        //1.How many male and female employees are there in the organization?
        ems.maleAndFemaleEmployees(employees);

        //2.Print the name of all departments in the organization
        ems.printDepartments(employees);

        //3.What is the average age of male and female employees?
        ems.avgAgeOfEmployees(employees);
    }

    private void avgAgeOfEmployees(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    int avg = (entry.getValue().stream()
                            .map(Employee::getAge)
                            .reduce(0, (acc, x) -> acc + x))/entry.getValue().size();
                    System.out.print("3."+entry.getKey() + " employees average age : " + avg+" , ");
                });

        //optimized
        Map<String, Double> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println("\n3.Employees average age: "+map);
    }

    private void printDepartments(List<Employee> employees) {
        Set<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
        System.out.println("2.Departments: " + departments);

        List<String> optimizedDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
        System.out.println("2.Optimized Departments: " + optimizedDepartments);
    }

    private void maleAndFemaleEmployees(List<Employee> employees) {
        Map<String, Long> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("1.No. of male and female employees: " + map);
    }

    private List<Employee> loadEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        return employeeList;
    }
}

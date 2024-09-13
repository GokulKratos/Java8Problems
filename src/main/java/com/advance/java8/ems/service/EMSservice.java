package com.advance.java8.ems.service;

import com.advance.java8.ems.model.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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

        //4.Get the details of highest paid employee in the organization?
        ems.highestPaidEmployee(employees);

        //5.Get the names of all employees who have joined after 2015?
        ems.employeesAfterYear(employees);

        //6.Count the number of employees in each department?
        ems.countEmpDept(employees);

        //7.What is the average salary of each department?
        ems.avgSalByDept(employees);

        //8.Get the details of youngest male employee in the product development department?
        ems.youngestMaleEmpByDept(employees);

        //9.Who has the most working experience in the organization?
        ems.maxWorkExp(employees);

        //10.How many male and female employees are there in the sales and marketing team?
        ems.empByDept(employees);

        //11.What is the average salary of male and female employees
        ems.avgSalOfMaleAndFemaleEmployees(employees);

        //13.List down the names of all employees in each department?
        ems.nameOfEmpByDept(employees);

        //14.What is the average salary and total salary of the whole organization?
        ems.avgAndTotalSal(employees);

        //15.Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years?
        ems.separateByAge(employees);

        //16.Who is the oldest employee in the organization? What is his age and which department he belongs to?
        ems.oldestEmp(employees);
    }

    private void oldestEmp(List<Employee> employees) {
        Employee emp = employees.stream()
                .max(Comparator.comparingInt(Employee::getAge))
                .get();
        System.out.println("16.oldest employee in the organization age and department: "+emp);
    }

    private void separateByAge(List<Employee> employees) {
        Map<String, List<Employee>> emp = employees.stream()
                .collect(Collectors.groupingBy(employee -> {
                    if (employee.getAge() <= 25) {
                        return "<=25 years";
                    } else {
                        return ">25years";
                    }
                }));
        System.out.println("15.Employees who are younger or equal to 25 years from those employees who are older than 25 years" + emp);

        //optimized
        Map<Boolean, List<Employee>> empOpt = employees.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getAge() <=25 ));
        System.out.println("15.Employees who are younger or equal to 25 years from those employees who are older than 25 years" + empOpt);
    }

    private void avgAndTotalSal(List<Employee> employees) {
        Double avgSal = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        Double totalSal = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("14.Average salary and total salary of the whole organization: " + avgSal + " | " + totalSal);

        //optimized
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("14.Optimized Average salary and total salary of the whole organization: " + dss.getAverage() + " | " + dss.getSum());
    }

    private void nameOfEmpByDept(List<Employee> employees) {
        Map<String, List<String>> emp = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().map(Employee::getName).toList()));
        System.out.println("13.Names of all employees in each department: " + emp);
    }

    private void avgSalOfMaleAndFemaleEmployees(List<Employee> employees) {
        Map<String, Double> emp = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("11.Average salary of male and female employees: " + emp);
    }

    private void empByDept(List<Employee> employees) {
        Map<String, Long> emp = employees.stream()
                .filter(employee -> "Sales And Marketing".equalsIgnoreCase(employee.getDepartment()))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("10.No. male and female employees are there in the sales and marketing team: " + emp);
    }

    private void maxWorkExp(List<Employee> employees) {
        Employee emp = employees.stream()
                .max(Comparator.comparingLong(employee -> {
                    return ChronoUnit.YEARS.between(LocalDate.of(employee.getYearOfJoining(), 1, 1), LocalDate.now());
                })).get();
        System.out.println("9.Most working experience in the organization: " + emp);

        Employee empOpt = employees.stream()
                .min(Comparator.comparingInt(Employee::getYearOfJoining))
                .get();
        System.out.println("9.Most working experience in the organization empOpt: " + empOpt);
    }

    private void youngestMaleEmpByDept(List<Employee> employees) {
        Employee emp = employees.stream()
                .filter(employee -> "Product Development".equalsIgnoreCase(employee.getDepartment()))
                .filter(employee -> "Male".equalsIgnoreCase(employee.getGender()))
                .min(Comparator.comparingInt(Employee::getAge))
                .get();
        System.out.println("8.Details of youngest male employee in the product development department: " + emp);
    }

    private void avgSalByDept(List<Employee> employees) {
        Map<String, Double> emp = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("7.Average salary of each department: " + emp);
    }

    private void countEmpDept(List<Employee> employees) {
        Map<String, Long> emp = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("6.Number of employees in each department: " + emp);
    }

    private void employeesAfterYear(List<Employee> employees) {
        List<String> emp = employees.stream()
                .filter(employee -> employee.getYearOfJoining() > 2015)
                .map(Employee::getName)
                .toList();
        System.out.println("5.Names of all employees who have joined after 2015: " + emp);
    }

    private void highestPaidEmployee(List<Employee> employees) {
        Employee employee = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get();
        System.out.println("4.Highest paid employee in the organization: " + employee);

        //optimized
        Employee employeeOptimized = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))
                .get();
        System.out.println("4.Highest paid employeeOptimized in the organization: " + employeeOptimized);
    }

    private void avgAgeOfEmployees(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    int avg = (entry.getValue().stream()
                            .map(Employee::getAge)
                            .reduce(0, (acc, x) -> acc + x)) / entry.getValue().size();
                    System.out.print("3." + entry.getKey() + " employees average age : " + avg + " , ");
                });

        //optimized
        Map<String, Double> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println("\n3.Employees average age: " + map);
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

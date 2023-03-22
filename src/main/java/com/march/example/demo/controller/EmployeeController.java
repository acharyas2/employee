package com.march.example.demo.controller;

import com.march.example.demo.entity.Employee;
import com.march.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

        @Autowired
        EmployeeRepository em;
        @GetMapping("/all")
        public List<Employee> getAll(){

            //1.Using size and get to iterate the list
            var allFields = em.findAll();
            for(int i=0; i<allFields.size(); i++){
                String name = allFields.get(i).getFirst_name();
                String reversedString = "";
                for(int j=name.length()-1; j>=0; j--){
                    reversedString+=name.charAt(j);
                }
                System.out.println(reversedString);
                allFields.get(i).setFirst_name( allFields.get(i).getFirst_name());
            }
            return allFields;


            //2.Using forEach to iterate the List
            /*List<Employee> allFields = em.findAll();
            for(Employee e: allFields){
                   String firstName =e.getFirst_name();
                   String reversedString = "";
                   for(int i=firstName.length()-1; i>=0; i--){
                        reversedString+=firstName.charAt(i);
                   }
                   e.setFirst_name(reversedString);
                System.out.println(reversedString);
            }
            return allFields;*/
        }

        @GetMapping("/names")
        public String name(){

            List<Employee> all = em.findAll();
            String[] employeeName = new String[all.size()];
            for(int i=0; i<all.size(); i++){
                String name = all.get(i).getFirst_name();
                employeeName[i] = name;
            }
            return Arrays.toString(employeeName);

        }

        @PostMapping("/save")
        public Employee save(@RequestBody Employee emp){
            return em.save(emp);
        }

        @GetMapping("/hashmap")
        public HashMap<String, Integer> returnHashMap(){
            List<Employee> allEmployee = em.findAll();
            HashMap<String, Integer> hashName = new HashMap<>();
            for(int i=0; i<allEmployee.size(); i++){
                String value = allEmployee.get(i).getFirst_name();
                hashName.put(value, value.length());
            }

            /*for(Employee e: allEmployee){
                String name = e.getFirst_name();
                hashName.put(name,name.length());
            }
            return hashName;*/
            return hashName;
        }

        @GetMapping("/{id}")
        public Employee getId(@PathVariable("id") Long id){
            Optional<Employee> employee = em.findById(id);
            if(!employee.isEmpty()){
                return employee.get();
            }
           return null;
        }

        @GetMapping("/orderByAge")
        public List<Employee> employeeOrderByAge(){
            List<Employee> allEmployees = em.findAll();

            for (int i = 0; i < allEmployees.size(); i++) {
                for (int j = 0; j < allEmployees.size() - i - 1; j++) {
                    if (allEmployees.get(j).getAge() < allEmployees.get(j + 1).getAge()) {
                        // Swap the elements
                        Employee temp = allEmployees.get(j);
                        allEmployees.set(j, allEmployees.get(j + 1));
                        allEmployees.set(j + 1, temp);
                    }
                }
            }
            return allEmployees;
        }

        @GetMapping("/orderByAge2")
        public List<Employee> employeeOrderByAge2(){
            List<Employee> allEmployees = em.getEmployeesByAgeDesc();
            return allEmployees;
        }

        @GetMapping("/orderByGivenAge/{age}")
        public List<Employee> employeeOrderByGivenAge(@PathVariable("age") int age){
            List<Employee> allEmployees = em.getEmployeesByAgeGreaterThan(age);
            return allEmployees;
        }
}

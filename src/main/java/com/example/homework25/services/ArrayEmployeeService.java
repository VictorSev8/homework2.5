package com.example.homework25.services;

import com.example.homework25.domain.Employee;
import com.example.homework25.exceptions.EmployeeAlreadyAddedException;
import com.example.homework25.exceptions.EmployeeNotFoundException;
import com.example.homework25.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ArrayEmployeeService implements EmployeeService {
    private static final int CAPACITY = 10;
    private Employee[] staff = new Employee[CAPACITY];
    private int currentSize = 0;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (currentSize >= CAPACITY) {
            throw new EmployeeStorageIsFullException();
        }
        Employee temp = new Employee(firstName, lastName);
        for (Employee emp : staff) {
            if (Objects.equals(emp, temp)) {
                throw new EmployeeAlreadyAddedException();
            }
        }

        staff[currentSize] = temp;
        currentSize++;
        return temp;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee temp = new Employee(firstName, lastName);
        int i;
        for (i = 0; i < currentSize; ++i) {
            if (staff[i].equals(temp)) break;
        }
        if (i==currentSize){
            throw new EmployeeNotFoundException();
        }
//        for (int j = i; j < currentSize - 1; j++) {
//            staff[j] = staff[j+1];
//        }
//        currentSize--;
        staff[i] = staff[currentSize-1];
        currentSize--;
        return temp;

    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee temp = new Employee(firstName, lastName);
        for (var emp : staff) {
            if(Objects.equals(emp, temp)) return emp;
        }
        throw new EmployeeNotFoundException();
    }
}

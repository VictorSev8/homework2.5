package com.example.homework25.services;

import com.example.homework25.domain.Employee;
import com.example.homework25.exceptions.EmployeeAlreadyAddedException;
import com.example.homework25.exceptions.EmployeeNotFoundException;
import com.example.homework25.exceptions.EmployeeStorageIsFullException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ListEmployeeService implements EmployeeService {
    private static final int CAPACITY = 10;
    List<Employee> staff = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee temp = new Employee(firstName, lastName);
        if (staff.size() >= CAPACITY) {
            throw new EmployeeStorageIsFullException();
        }
        if (staff.contains(temp)) {
            throw new EmployeeAlreadyAddedException();
        }
        staff.add(temp);
        return temp;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        int index = staff.indexOf(new Employee(firstName, lastName));
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }

        return staff.remove(index);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        int index = staff.indexOf(new Employee(firstName, lastName));
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        return staff.get(index);
    }
}

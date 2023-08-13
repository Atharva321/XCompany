package com.crio.xcompany.company.DataBase;

import java.util.HashMap;
import com.crio.xcompany.company.IDataBase.IDatabase;
import com.crio.xcompany.company.Employee;

/**
 *
 */
public class CompanyDatabase implements IDatabase {

    private static HashMap<String,Employee> database = new HashMap<>();

    /**
     * Saving Employee data
     * @param employee
     */
    @Override
    public void save(Employee employee)
    {
        database.put(employee.getName(), employee);
    }

    /**
     * Deleting Employee data
     * @param employeeName
     */
    @Override
    public void delete(String employeeName)
    {
        if(database.containsKey(employeeName))
        {
            database.remove(employeeName);
        }
    }

    /**
     *
     * @param name
     * @return employee
     */
    @Override
    public Employee get(String name)
    {
        return database.get(name);
    }

}
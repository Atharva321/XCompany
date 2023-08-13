package com.crio.xcompany.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;
import com.crio.xcompany.company.IDataBase.IDatabase;
import com.crio.xcompany.company.DataBase.CompanyDatabase;

public class Company{
    private String companyName;
    private Employee founder;
    private Map<String,Employee> employeeBook;

    /**
     *
     * @param companyName
     * @param founder
     */
    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<String,Employee>();
        employeeBook.put(founder.getName(), founder);
    }

    /**
     *
     * @param companyName
     * @param founder
     * @return
     */
    public static Company create(String companyName, Employee founder){
        return new Company(companyName,founder);
    }

    /**
     *
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.

    /**
     * Registering the employee in company
     * This function saves employeed information to database.
     * @param employeeName
     * @param gender
     */
    public void registerEmployee(String employeeName, Gender gender)
    {
       Employee employee = new Employee(employeeName,gender);
       IDatabase companyDatabase = new CompanyDatabase();
       companyDatabase.save(employee);
    }

    /**
     *
     * @param employeeName
     * @return the employee
     */
    public Employee getEmployee(String employeeName)
    {
        IDatabase companyDatabase = new CompanyDatabase();
        return companyDatabase.get(employeeName);
    }

    /**
     * This function deletes the employee from the Database.
     * @param employeeName
     *
     */
    public void deleteEmployee(String employeeName)
    {
        IDatabase companyDatabase = new CompanyDatabase();
        Employee employee =  companyDatabase.get(employeeName);
        companyDatabase.delete(employeeName);
        employee=null;
    }

    /**
     * This function assigns manager to employee
     * @param employeeName
     * @param managerName
     */
    public void assignManager(String employeeName,String managerName)
    {
        IDatabase companyDatabase = new CompanyDatabase();
        Employee employee =   companyDatabase.get(employeeName);
        Employee manager = companyDatabase.get(managerName);
        employee.assignManager(manager);

    }

    /**
     * The logic behind this function:
     *      We are storing respective reportees to the employee in employee class.
     * @param managerName
     * @return List of DirectReports
     */
    public List getDirectReports(String managerName)
    {
        IDatabase companyDatabase = new CompanyDatabase();
        Employee employee = companyDatabase.get(managerName);
        return employee.getDirectReports();
    }

    /**
     * Method to get teammates of thee employee.
     * @param employeeName
     * @return List of Teammates
     */
    public List getTeamMates(String employeeName)
    {
        IDatabase companyDatabase = new CompanyDatabase();
        Employee employee = companyDatabase.get(employeeName);
        return employee.getTeamMates();
    }

    /**
     * Here, we are basically traversing N-ary tree.
     * In which each node of datatype Employee(i.e. Lisst of Employee)
     * @param managerName
     * @return nested list of employees representing
     * Employee Hierarchy.
     */
    public List<List<Employee>> getEmployeeHierarchy(String managerName)
    {
        Queue<Employee> queue = new LinkedList<>();
        List<List<Employee>> hierarchy = new LinkedList<>();
        List<Employee> reportees;
        IDatabase companyDatabase = new CompanyDatabase();
        Employee employee = companyDatabase.get(managerName);
        queue.add(employee);

        while(queue.size()>0)
        {
            int current=queue.size();
            reportees=new LinkedList<>();
            while(current>0)
            {
                Employee temp = queue.poll();
                reportees.add(temp);
                if(temp!=null)
                {
                queue.addAll(temp.getDirectReports());
                }
                --current;                
            }
            hierarchy.add(reportees);
        }

        return hierarchy;
    }



}

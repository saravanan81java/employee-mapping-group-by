package com.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
 

public class EmployeeMappingGroupBySample {

	public static void main(String[] args) {
		List<Employee> listOfEmployee = Arrays.asList(new Employee(1,"Mannu",30),
				new Employee(2,"Atul",30),
				new Employee(3,"Asif",32),
				new Employee(4,"Heera",32),
				new Employee(5,"Heera",32),
				new Employee(6,"Atul",31));
		
		
		
		Comparator<Employee> employeeComparator = Comparator.comparingDouble(Employee::getAge);
		Map<Employee,String> mapOfEmployeeTree = new TreeMap<>(employeeComparator);
				mapOfEmployeeTree.put(new Employee(1,"Mannu",30),"Dep1");
				mapOfEmployeeTree.put(new Employee(2,"Atul",33),"Dep3");
				mapOfEmployeeTree.put(new Employee(3,"Asif",32),"Dep2");
				mapOfEmployeeTree.put(new Employee(4,"Heera",34),"Dep3");
				mapOfEmployeeTree.put(new Employee(5,"Heera",32),"Dep2");
				mapOfEmployeeTree.put(new Employee(6,"Atul",31),"Dep1");
				
				
		mapOfEmployeeTree.entrySet().stream().forEach(System.out::println);
		
		
		
		Map<Integer, Set<Employee>> countEmployee = listOfEmployee.stream()
				.collect( Collectors.groupingBy(Employee::getAge, Collectors.toSet()));
		
		countEmployee.forEach((age, empList)-> 
			{
				System.out.println("Age :" + age +" "); 
				empList.forEach(System.out::println);
			});
		
		
		
		List<String> employeeNames = listOfEmployee.stream()
				.map(emp->emp.getName()).collect(Collectors.toList());
		
		employeeNames.forEach(System.out::println);
		
		Map<String, Long> countEmployeeNames = employeeNames.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(countEmployeeNames);
		
		Map<String, Long> employeeNamelist = countEmployeeNames.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
		 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
				 (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		System.out.println("Employee sort by value: "+employeeNamelist);
		
		countEmployeeNames.entrySet().stream().sorted(Map.Entry.comparingByValue()) 
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue)-> oldValue, LinkedHashMap::new))
		.forEach((k,v)->System.out.println(" Names : "+k + " - " +v));
		
		 String text = "COmmon";
	        
	       Map<Character, Long> result= text.toLowerCase().chars().mapToObj( c -> (char) c)
	    		   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	       
	        result
	        .entrySet()
	        .stream()
	        .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
	    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	    		   (oldValue, newValue) -> oldValue, LinkedHashMap::new))
	    	.forEach((k, v )-> {System.out.println(k + " - " + v);});
	       
	       result
	       .entrySet()
	       .stream()
	       .sorted(Map.Entry.<Character, Long>comparingByValue())
	       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	    		   (oldValue, newValue) -> oldValue, LinkedHashMap::new))
	       .forEach((k, v )-> {System.out.println(k + " - " + v);});
	       
	       	
		
		Map<Employee, String> countEmployees = listOfEmployee.stream().
				collect(Collectors.toMap(emp-> emp, (emp -> emp.getName())));
		
		countEmployees.entrySet().stream().forEach(emp -> System.out.println( emp.getKey().hashCode() + "--" + emp.getValue())); 
		
		
		Predicate<Employee> ageCheck = (Employee employee)-> employee.getAge()== 30;
		
		List<Employee> empList = new ArrayList<>();
		for (Employee employee : listOfEmployee) {
			if(ageCheck.test(employee)) {
				empList.add(employee);	
			}
		}
		
		empList.forEach(System.out::println);
		
		Supplier<Employee> supplierBuild = () -> new Employee(5, "Johan", 20);
		
		empList.add(supplierBuild.get());
		
		Consumer<Employee> consumerBuild = (Employee employee) -> {System.out.println(employee.getName() +"-"+ employee.getAge());};
		for (Employee employee : empList) {
			consumerBuild.accept(employee);
		}
	}

}

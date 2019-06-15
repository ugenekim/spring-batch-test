package test.ujkim.spring.batch.job;

import org.springframework.batch.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;
import test.ujkim.spring.batch.model.Employee;

@Slf4j
public class ValidationProcessor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee employee) throws Exception {
		log.info(">>>>>>>>> processor start..");
		if (employee.getId() == null) {
			log.info("Missing employee id : {}", employee.getId());
			return null;
		}
		
		try {
			if (Integer.valueOf(employee.getId()) <= 0) {
				log.info("Invalid employee id : {}", employee.getId());
				return null;
			}
		} catch (NumberFormatException e) {
			log.info("Invalid employee id : {}", employee.getId());
			return null;
		}
		log.info(">>>>>>>>> processor done..");
		
 		return employee;
	}
	
}

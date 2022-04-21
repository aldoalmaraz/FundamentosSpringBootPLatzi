package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.jni.Local;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
private final Log LOGGER= LogFactory.getLog(FundamentosApplication.class);

	// En esta clase se realiza la inyeccion de dependecias.
	private ComponentDependency componentDependency; // agregamos la interfaz del componente o bean
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;



	//por medio del constructor agregamos de igual maneta la interfaz.
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency ,MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
		this.userRepository=userRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    //ejemplosAnteriores();
    saveUsersInDataBase();
		getInformationJpqlFromUser();


	}
	private void getInformationJpqlFromUser(){
	/*LOGGER.info("USUARIO CON EL METODO FINDBYUSEREMAIL: " +
			userRepository.findByUserEmail("john@domain.com")
					.orElseThrow(()-> new RuntimeException("NO SE ENCONTRO EL USUARIO")));

	userRepository.findAndSort("Lui", Sort.by("id").descending()).stream()
			.forEach(user -> LOGGER.info("User con metodo sort: "+user));

	userRepository.findByName("Marco").stream()
			.forEach(user -> LOGGER.info("USUARIO CON QUERY METHOD "+ user));

	LOGGER.info("Usuario con query method FindMyEmailAndName "+ userRepository.findByEmailAndName("daniela@domain.com", "Daniela").orElseThrow(()-> new RuntimeException("USUARIO NO ENCONTRADO")));

	userRepository.findByNameLike("%M%").stream()
				.forEach(user -> LOGGER.info("USUARIO FINDMYNAMELIKE:  "+ user));

		userRepository.findByNameOrEmail(null,"marisol@domain.com").stream()
				.forEach(user -> LOGGER.info("USUARIO FINDMYNAMELIKEOREMAIL:  "+ user));
*/
	userRepository.findBybirthdateBetween(LocalDate.of(2021,3,2),LocalDate.of(2021,10,2)).stream()
			.forEach(user -> LOGGER.info("USUARIO ENTRE FECHAS: "+user));

		userRepository.findByNameLikeOrderByIdDesc("%Marco%").stream()
				.forEach(user -> LOGGER.info("USUARIO ENCONTRADO CON LIKE Y ORDENADO: "+user));



	}



	private void saveUsersInDataBase(){
		User user1 = new User("Marco", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user6,user7,user8,user9);
		list.stream().forEach(userRepository::save); // lo guarda en el repository y guarda practicamente la informacion.

	}

	public void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.Function());
		System.out.println(userPojo.getEmail()+" - "+userPojo.getPassword());
		try{
			int value=10/0;
			LOGGER.debug( "Mi valor " + value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por 0!!"+  e.getMessage());
		}
	}
}

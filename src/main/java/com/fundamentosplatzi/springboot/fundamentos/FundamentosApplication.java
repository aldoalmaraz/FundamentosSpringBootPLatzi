package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
private final Log LOGGER= LogFactory.getLog(FundamentosApplication.class);

	// En esta clase se realiza la inyeccion de dependecias.
	private ComponentDependency componentDependency; // agregamos la interfaz del componente o bean
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;



	//por medio del constructor agregamos de igual maneta la interfaz.
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency ,MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}
 // Este metodo hace llamar a la dependencia.
	@Override
	public void run(String... args) throws Exception {
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

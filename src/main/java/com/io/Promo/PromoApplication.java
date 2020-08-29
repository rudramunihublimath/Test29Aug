package com.io.Promo;

import com.io.Promo.model.Discount;
import com.io.Promo.model.Product;
import com.io.Promo.repo.Discountrepo;
import com.io.Promo.repo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class PromoApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PromoApplication.class, args);
	}


	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.io.Promo")).build();
	}

	@Autowired
	private Productrepo productrepo;
	@Autowired
	private Discountrepo discountrepo;

	@Override
	public void run(String...args) throws Exception {
		ArrayList<Product> list =new ArrayList<>();
		list.add(new Product(1,"A",50,1));
		list.add(new Product(2,"B",30,1));
		list.add(new Product(3,"C",20,1));
		list.add(new Product(4,"D",15,1));

		list.forEach(i-> productrepo.save(i));

		ArrayList<Discount> list2 =new ArrayList<>();
		list2.add(new Discount(1,"A",3,130));
		list2.add(new Discount(2,"B",2,45));
		list2.add(new Discount(3,"C",1,0));
		list2.add(new Discount(3,"D",1,30));
		list2.forEach(i-> discountrepo.save(i));
	}
}

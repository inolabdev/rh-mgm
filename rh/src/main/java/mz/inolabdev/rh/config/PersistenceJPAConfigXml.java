package mz.inolabdev.rh.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "mz.inolabdev.rh.dao",
		"mz.inolabdev.rh.dao.impl", "mz.inolabdev.rh.services",
		"mz.inolabdev.rh.services.impl" })
@ImportResource({ "classpath:jpaConfig.xml" })
public class PersistenceJPAConfigXml {

    public PersistenceJPAConfigXml() {
        super();
    }

}
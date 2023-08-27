package com.sachin.employeeregister.util;


import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Component
public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration = new FactoryConfiguration();
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/hibernate");

            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("hibernate.properties"));

            Configuration configuration = new Configuration()
                    .addAnnotatedClass(Department.class)
                    .addAnnotatedClass(Employee.class)
                    .addProperties(properties);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(properties)
                    .applySetting("hibernate.connection.datasource", dataSource)
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (NamingException | IOException ex) {
            throw new RuntimeException("Error configuring Hibernate with JNDI DataSource.", ex);
        }
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

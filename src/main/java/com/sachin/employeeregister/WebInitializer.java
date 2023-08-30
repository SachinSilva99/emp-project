package com.sachin.employeeregister;

import com.sachin.employeeregister.config.WebAppConfig;
import com.sachin.employeeregister.config.WebRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String tempUploadDirectory = "D:\\Working Directory\\EmployeeRegister\\temp"; // Update this with the actual path


        registration.setMultipartConfig(new MultipartConfigElement(tempUploadDirectory));
    }
}

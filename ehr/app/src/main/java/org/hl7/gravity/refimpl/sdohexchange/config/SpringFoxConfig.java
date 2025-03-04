package org.hl7.gravity.refimpl.sdohexchange.config;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

  public static final String CONTEXT_API_TAG = "Context Controller";
  public static final String SUPPORT_API_TAG = "Support Controller";
  public static final String TASK_API_TAG = "Task Controller";
  public static final String MAPPINGS_API_TAG = "Mappings Controller";
  public static final String ADMINISTRATION_API_TAG = "Administration Controller";

  @Bean
  public Docket api(ApiInfo apiInfo) {
    return new Docket(DocumentationType.SWAGGER_2).consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
        .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
        .select()
        .apis(RequestHandlerSelectors.basePackage("org.hl7.gravity.refimpl.sdohexchange"))
        .paths(PathSelectors.any())
        .build()
        .tags(new Tag(CONTEXT_API_TAG, "Get context details of a currently logged in user."), new Tag(SUPPORT_API_TAG,
                "Fetch lists of available FHIR resources to reference from Task/ServiceRequest instances being "
                    + "created."), new Tag(TASK_API_TAG,
                "Perform operations on Task resources. This includes creation of tasks in CBRO organizations and "
                    + "triggering an automatic polling mechanism for Task status synchronization."),
            new Tag(MAPPINGS_API_TAG, "Get details of SDOH categories and codes."), new Tag(ADMINISTRATION_API_TAG,
                "Perform operations and manipulations with FHIR resources, for example converts resources from one to"
                    + " another."))
        .apiInfo(apiInfo)
        .useDefaultResponseMessages(false);
  }

  @Bean
  public ApiInfo apiInfo(@Value("${swagger.title}") String title, Contact contact) {
    return new ApiInfoBuilder().title(title)
        .contact(contact)
        .build();
  }

  @Bean
  public Contact swaggerContact(@Value("${swagger.contact.name}") String name,
      @Value("${swagger.contact.url}") String url) {
    return new Contact(name, url, null);
  }
}
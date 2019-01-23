package com.codingdojo.beltboilerplateone.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.codingdojo.beltboilerplateone.models.User;

//4 @Component. Below is an explanation of this annotating from the Spring Documentation.
//Spring provides further stereotype annotations: @Component, @Service, and @Controller. @Component is a generic stereotype for any Spring-managed component. @Repository, @Service, and @Controller are specializations of @Component for more specific use cases, for example, in the persistence, service, and presentation layers, respectively. Therefore, you can annotate your component classes with @Component, but by annotating them with @Repository, @Service, or @Controller instead, your classes are more properly suited for processing by tools or associating with aspects.
@Component
public class UserValidator implements Validator {
    // 1 supports(Class<?>): Specifies that a instance of the User Domain Model can be validated with this custom validator
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2 validate(Object, Errors): Creating our custom validation. We can add errors via .rejectValue(String, String).
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3 .rejectValue(String, String): the first argument is the member variable of our Domain model that we are validating. The second argument is a code for us to use to set an error message.
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}




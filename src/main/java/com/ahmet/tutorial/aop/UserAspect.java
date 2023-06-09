package com.ahmet.tutorial.aop;

import com.ahmet.tutorial.dto.UserDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class UserAspect {

    @Before("execution(* com.ahmet.tutorial.controller.UserController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Request to " + joinPoint.getSignature() + " started at " + new Date());
    }

    @After("execution(* com.ahmet.tutorial.controller.UserController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("Request to " + joinPoint.getSignature() + " ended at " + new Date());
    }

    @AfterReturning(value = "execution(* com.ahmet.tutorial.service.impl.UserServiceImpl.createUser(..))",
                    returning = "userDto")
    public void afterReturningCreateUser(JoinPoint joinPoint, UserDto userDto) {
        System.out.println("Service saved user with id: " + userDto.getId() + " " + new Date());
    }

    @AfterThrowing(value = "execution(* com.ahmet.tutorial.service.impl.UserServiceImpl.createUser(..))",
                   throwing = "ex")
    public void afterThrowingCreateUser(JoinPoint joinPoint, Exception ex) {
        System.out.println("Service threw exception: " + ex.getMessage() + " " + new Date());
    }

    @Around(("execution(* com.ahmet.tutorial.service.impl.UserServiceImpl.createUser(..))"))
    public UserDto aroundCreateUser(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Inside around advice for create user");
        try {
            UserDto userDto = (UserDto) proceedingJoinPoint.proceed();
            System.out.println("Successful in around advice for create user: " + userDto.getEmail());
            return userDto;
        } catch (Throwable e) {
            System.out.println("Exception in around advice for create user: " + e.getMessage());
            throw e;
        }
    }
}

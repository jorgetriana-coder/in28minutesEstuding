package com.in28minutes.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static {
        users.add(new User(++ usersCount,"Adan", LocalDate.now()));
        users.add(new User(++ usersCount,"Eve", LocalDate.now()));
        users.add(new User(++ usersCount,"Jim", LocalDate.now()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++ usersCount);
        users.add(user);
        return user;
    }


    public User findByID(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteByID(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}

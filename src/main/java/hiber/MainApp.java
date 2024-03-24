package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User rixio = new User("Rixio", "Morales", "rixio@mail.ru");
      User tianet = new User("Tianet", "Torres", "tianet@mail.ru");
      User maitte = new User("Maitte", "Prieto", "maitte@mail.ru");
      User ivana = new User("Ivana", "Fernandez", "ivana@mail.ru");

      Car lamborghini = new Car("Lamborghini", 2017);
      Car hummer = new Car("Hummer", 2008);
      Car nissan = new Car("Nissan", 2014);
      Car porsche = new Car("Porsche", 2024);

      rixio.setCar(lamborghini);
      tianet.setCar(hummer);
      maitte.setCar(nissan);
      ivana.setCar(porsche);

      userService.add(rixio);
      userService.add(tianet);
      userService.add(maitte);
      userService.add(ivana);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Model = "+user.getCar().getModel());
            System.out.println("Series = "+user.getCar().getSeries());
         }
         System.out.println();
      }

      String model = "Lamborghini";
      int series = 2017;
      User owner = userService.getOwner(model, series);
      if (owner != null) {
         System.out.println("Owner found! Id:" + owner.getId() + " Name: " + owner.getFirstName());
      }

      context.close();
   }
}

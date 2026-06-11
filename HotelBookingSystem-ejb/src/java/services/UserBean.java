package services;

import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.PersistenceContext;

@DataSourceDefinition(
    name = "java:app/jdbc/hotel_booking_pool", // This registers the JNDI name automatically
    className = "com.mysql.cj.jdbc.MysqlDataSource", // Works for MySQL 8+ drivers
    url = "jdbc:mysql://localhost:3306/hotel_booking_db?useSSL=false&allowPublicKeyRetrieval=true",
    user = "root",
    password = "" // Put your team's local MySQL password here if you use one
)
@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    // == THE SMOKE TEST METHOD FOR TESTCONNSERVLET ==
    public boolean testDatabaseConnection() {
        try {
            // Runs a primitive query checking if the persistence context can talk to MySQL
            em.createNativeQuery("SELECT 1").getSingleResult();
            return true; // If no exception was thrown, connection is healthy!
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // == YOUR LOGIN AND REGISTRATION FUNCTIONS ==
    public void registerUser(Users user) {
        em.persist(user);
    }

    public Users loginUser(String username, String password) {
        try {
            List<Users> list = em.createQuery(
                "SELECT u FROM Users u WHERE u.username = :uname", Users.class)
                .setParameter("uname", username)
                .getResultList();

            if (!list.isEmpty()) {
                Users foundUser = list.get(0);
                if (foundUser.getPassword().equals(password)) {
                    return foundUser; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
}
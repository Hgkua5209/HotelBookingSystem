package entities;

import entities.Users;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-02T18:30:38")
@StaticMetamodel(Bookings.class)
public class Bookings_ { 

    public static volatile SingularAttribute<Bookings, Date> checkOutDate;
    public static volatile SingularAttribute<Bookings, BigDecimal> totalPrice;
    public static volatile SingularAttribute<Bookings, Integer> id;
    public static volatile SingularAttribute<Bookings, Date> checkInDate;
    public static volatile SingularAttribute<Bookings, Users> userId;
    public static volatile SingularAttribute<Bookings, String> roomType;

}
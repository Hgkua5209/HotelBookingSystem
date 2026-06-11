package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-06-11T22:24:24")
@StaticMetamodel(Cart.class)
public class Cart_ { 

    public static volatile SingularAttribute<Cart, Date> createdAt;
    public static volatile SingularAttribute<Cart, Double> total;
    public static volatile SingularAttribute<Cart, Double> price;
    public static volatile SingularAttribute<Cart, Integer> nights;
    public static volatile SingularAttribute<Cart, Integer> cartId;
    public static volatile SingularAttribute<Cart, Integer> roomId;
    public static volatile SingularAttribute<Cart, String> username;

}
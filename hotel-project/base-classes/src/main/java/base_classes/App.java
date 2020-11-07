package base_classes;
import base_classes.classes.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        //System.out.println((ttt.te1 == ttt.te1)? true: false);
        //System.out.println((ttt.te2 == ttt.te1)? true: false);
        UserRoles ur = new UserRoles("meh");
        //System.out.println(ur.getFields());

        System.out.println(ur);
    }
}

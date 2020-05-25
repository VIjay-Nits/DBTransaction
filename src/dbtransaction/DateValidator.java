
package dbtransaction;
import java.io.*;
import java.time.LocalDate;
/**
 *
 * @author VIjay
 */
public class DateValidator {
  
  
    static int MAX_VALID_YR = 9999; 
    static int MIN_VALID_YR = 2019;
    int day;
    int month;
    int year;

    public DateValidator(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
  
    
    private static boolean isLeap(int year) 
    { 
       
        return (((year % 4 == 0) &&  
                 (year % 100 != 0)) ||  
                 (year % 400 == 0)); 
    } 
  
     static boolean isValidDate(int d,int m,int y) {
        
        if (y > MAX_VALID_YR ||y < MIN_VALID_YR) 
            return false; 
        if (m < 1 || m > 12) 
            return false; 
        if (d < 1 || d > 31) 
            return false; 
  
        if (m == 2)  
        { 
            if (isLeap(y)) 
                return (d <= 29); 
            else
                return (d <= 28); 
        } 
  
       
        if (m == 4 || m == 6 ||  
            m == 9 || m == 11) 
            return (d <= 30); 
  
        return true; 
    }
    
     public static LocalDate daily(LocalDate date) {
    return date.plusDays(1);
     }
     
     

   
}

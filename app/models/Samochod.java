package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Computer entity managed by Ebean
 */
@Entity 
public class Samochod extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String marka;
    
    public String model;
    
    @Constraints.Required
    public String typ;
    
    @Constraints.Required
    public String numRej;
    
    @Formats.DateTime(pattern="yyyy")
    public Date dataProdukcji;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date przeglad;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date ubezpieczenie;
    
    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,Samochod> find = new Finder<Long,Samochod>(Long.class, Samochod.class); 
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Samochod> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("marka", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
    
}
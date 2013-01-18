package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage a database
 */
public class Application extends Controller {
    
    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "marka", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Samochod.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing.
     *
     * @param id Id to edit
     */
    public static Result edit(Long id) {
        Form<Samochod> samochodForm = form(Samochod.class).fill(
        		Samochod.find.byId(id)
        );
        return ok(
            editForm.render(id, samochodForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id to edit
     */
    public static Result update(Long id) {
        Form<Samochod> samochodForm = form(Samochod.class).bindFromRequest();
        if(samochodForm.hasErrors()) {
            return badRequest(editForm.render(id, samochodForm));
        }
        samochodForm.get().update(id);
        flash("success", "Samochod " + samochodForm.get().marka + " został zaktualizowany");
        return GO_HOME;
    }
    
    /**
     * Display the 'new form'.
     */
    public static Result create() {
        Form<Samochod> samochodForm = form(Samochod.class);
        return ok(
            createForm.render(samochodForm)
        );
    }
    
    /**
     * Handle the 'new form' submission 
     */
    public static Result save() {
        Form<Samochod> samochodForm = form(Samochod.class).bindFromRequest();
        if(samochodForm.hasErrors()) {
            return badRequest(createForm.render(samochodForm));
        }
        samochodForm.get().save();
        flash("success", "Samochod " + samochodForm.get().marka + " został dodany");
        return GO_HOME;
    }
    
    /**
     * Handle deletion
     */
    public static Result delete(Long id) {
    	Samochod.find.ref(id).delete();
        flash("success", "Samochod został usunięty");
        return GO_HOME;
    }
    

}
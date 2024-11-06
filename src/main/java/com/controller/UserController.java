package com.controller;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.facade.UserFacade;
import com.model.security.Users;

@Named
@SessionScoped
public class UserController implements Serializable {

 
    private Users user;

    @EJB
    protected UserFacade userFacade;

    private static MessageDigest digestor;

    private String type;
    private List<String> types = new ArrayList<>(Arrays.asList(new String[]{"Customer", "Employee"}));

    public UserController(){
        try {
            digestor = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }


    public String encrypt(String password) {

        byte[] encodedHash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder encrypted = new StringBuilder(2 * encodedHash.length);

        for (int i=0; i<encodedHash.length; i++){
            String haxVal = Integer.toHexString(0xff & encodedHash[i]);
            if(haxVal.length() == 1){
                encrypted.append('0');
            }
            encrypted.append(haxVal);
        }
        return encrypted.toString();

    }

    public boolean validate(String password, String passwordEntered) {
        byte[] hashed1 = password.getBytes(StandardCharsets.UTF_8);
        byte[] hashed2 = encrypt(passwordEntered).getBytes(StandardCharsets.UTF_8);
        return digestor.isEqual(hashed1,hashed2);
    }


    public void openNew() {
        this.user = new Users();
        
        this.type = null;
    }

//    public void saveUser() {
//        if(this.user.getUsername()!=null) {
//            this.user.setUserPassword(encrypt(this.user.getUserPassword()));
//            if (type.equals("Customer")) {
////                this.customer.setUsers(this.user);
//                userFacade.saveCustomer(this.user, this.customer);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added"));
//                PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
//
//                this.user = null;
//              
//                this.type = null;
//
//            } else if (type.equals("Employee")) {
//
////                this.employee.setUsers(this.user);
//                userFacade.saveEmployee(this.user, this.employee);
//
//                this.user = null;
//                this.type = null;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added"));
//                PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
//
//            }
//        }
  //  }
}

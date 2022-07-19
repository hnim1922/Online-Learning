/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author ihtng
 */
public class User implements Serializable {

    private int userID;
    private String roleID;
    private int settingID;
    private String email;
    private String password;
    private String gender;
    private String fullname;
    private String phone;
    private String hash;
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public User() {
    }

    public User(String email, String fullname) {
        this.email = email;
        this.fullname = fullname;
    }

    public User(int userID, String roleID, int settingID, String email, String password, String gender, String fullname, String phone, String hash) {
        this.userID = userID;
        this.roleID = roleID;
        this.settingID = settingID;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.fullname = fullname;
        this.phone = phone;
        this.hash = hash;
    }


    public User(int userID, String roleID, String email, String password, String gender, String fullname, String phone) {
        this.userID = userID;
        this.roleID = roleID;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.fullname = fullname;
        this.phone = phone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User: " + fullname + " " + email + " " + gender + " " + phone + " " + hash ; //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Comparator<User> byuserID=new Comparator<User>()
    {
        @Override
        public int compare(User u1, User u2) {
            if(u1.getUserID() > u2.getUserID())
                return 1;
            else if(u1.getUserID() < u2.getUserID())
                return -1;
            return 0;
        }        
    };


    public static Comparator<User> byName=new Comparator<User>()
    {
        @Override
        public int compare(User u1, User u2) {
            if(u1.getFullname().compareTo(u2.getFullname())>0)
                return 1;
            else if(u1.getFullname().compareTo(u2.getFullname())<0)
                return -1;
            else{
                if(u1.getUserID() > u2.getUserID()) return 1;
                else if(u1.getUserID() < u2.getUserID()) return -1;
                else return 0;
            }
        }        
    };
    
    public static Comparator<User> byPhone=new Comparator<User>()
    {
        @Override
        public int compare(User u1, User u2) {
            if(u1.getPhone().compareTo(u2.getPhone())>0)
                return 1;
            else if(u1.getPhone().compareTo(u2.getPhone())<0)
                return -1;
            return 0;
        }        
    };
    
    public static Comparator<User> byEmail=new Comparator<User>()
    {
        @Override
        public int compare(User u1, User u2) {
            if(u1.getEmail().compareTo(u2.getEmail())>0)
                return 1;
            else if(u1.getEmail().compareTo(u2.getEmail())<0)
                return -1;
            return 0;
        }        
    };
    
    public static Comparator<User> byRole=new Comparator<User>()
    {
        @Override
        public int compare(User u1, User u2) {
            if(u1.getRoleID().compareTo(u2.getRoleID())>0)
                return 1;
            else if(u1.getRoleID().compareTo(u2.getRoleID())<0)
                return -1;
            else{
                if(u1.getUserID() > u2.getUserID()) return 1;
                else if(u1.getUserID() < u2.getUserID()) return -1;
                else return 0;
            }
        }        
    };
    
    public static Comparator<User> byGender=new Comparator<User>()
    {
        @Override
        public int compare(User u1, User u2) {
            if(u1.getGender().compareTo(u2.getGender())>0)
                return 1;
            else if(u1.getGender().compareTo(u2.getGender())<0)
                return -1;
            else{
                if(u1.getUserID() > u2.getUserID()) return 1;
                else if(u1.getUserID() < u2.getUserID()) return -1;
                else return 0;
            }
        }        
    };
    
    

}

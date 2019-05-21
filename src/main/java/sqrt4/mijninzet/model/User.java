package sqrt4.mijninzet.model;

import sqrt4.mijninzet.model.Beschikbaarheid.Semester;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Semester> semesters;

//    @ManyToMany
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//
public User(String username, String password, String roles, String permissions){
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.permissions = permissions;
    this.active = 1;

}

    public User(String username, String password, String roles, String permissions, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.active = 1;
        this.roles = roles;
        this.permissions = permissions;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected User(){}

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }



    //private Collection<Role> roles;


//    public User(String userName, String password, String firstName, String lastName) {
//        this.username = userName;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        semesters = new ArrayList<>();
//        roles = new ArrayList<>();
//    }

//    public User() {
//    }
//
//    public void addRole(Role role) {
//        roles.add(role);
//    }
//    public String getUserName() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public Collection<Role> getRoles() {
//        return roles;
//    }
//
//    public void setUserName(String userName) {
//        this.username = userName;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setRoles(Collection<Role> roles) {
//        this.roles = roles;
//    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
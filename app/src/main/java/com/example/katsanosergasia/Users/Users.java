package com.example.katsanosergasia.Users;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Lampros Giotis
 * Σχετική περιγραφή:Αυτή η κλάση αναπριστά έναν χρήστη με κάποια χαρακτηριστικά όπως username και password,καθώς και διάφορες μεθόδους για την ορθή λειτουργία
 * των χρηστών.
 */
public class Users implements Serializable {
    private String username;//username του χρήστη
    private String password;//password του χρήστη
    private static HashMap<String, Users> usersList = new HashMap<>();//Λίστα με τους χρήστες

    public Users() {
        //Default Constructor,δεν κάνει κάτι το ιδιαίτερο.
    }

    /**
     * Constructor,Αρχικοποιούμαι έναν χρήστη με το username και password του.
     * @param username το username του χρήστη
     * @param password το password του χρήστη
     */
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Μέθοδος που προσθέτει τους χρήστες στην λίστα
     * @param user ο νέος χρήστης
     */
    public static void addUser(Users user) {
        usersList.put(user.getUsername(), user);
        saveUserToFirebase(user);
    }

    /**
     * Μέθοδος που μας δίνει έναν συγκεκριμένο χρήστη
     * @param username Τον χρήστη που θέλουμε βάση του username του
     * @return το username του.
     */
    public static Users getUser(String username) {
        return usersList.get(username);
    }

    /**
     * Μέθοδος που επιστρέφει το username του χρήστη
     * @return το username του.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Μέθοδος που επιστρέφει το password του χρήστη
     * @return το password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Μέθοδος που επιστρέφει την λίστα με τους χρήστες
     * @return την λίστα των χρηστών
     */
    public static HashMap<String, Users> getUsersList() {
        return usersList;
    }

    /**
     * Μέθοδος που αποθηκεύει τους χρήστες στην βάση δεδομένων
     * @param user τον Χρήστη που θέλουμε να αποθηκεύσουμε
     */
    private static void saveUserToFirebase(Users user) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(user.getUsername()).setValue(user);
    }

    /**
     * Μέθοδος που φορτώνει τους χρήστες απο την βάση στην λίστα με τους χρήστες
     */
    public static void loadUsersFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usersList.clear(); // Κάνουμε clear για να αποφύγουμε τους διπλότυπους λογαριασμούς
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = snapshot.getValue(Users.class);
                    if (user != null) {
                        usersList.put(user.getUsername(), user);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getCode();//Παίρνουμε τον κωδικό του error
            }
        });
    }

    /**
     * Αυτή η μέθοδος ενσωματώνει στην νέα λίστα τους χρήστες
     * @param newUsersList η updated λίστα με τους χρήστες
     */
    public static void setUsersList(HashMap<String, Users> newUsersList) {
        usersList = newUsersList;
    }

    /**
     * Απλή μέθοδος ToString
     * @return τα credentials των users σε μορφη String
     */
    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

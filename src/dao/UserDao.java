package dao;

import beans.User;

import java.util.List;

/**
 * Dao : User
 *
 * @see beans.User
 */
public interface UserDao {

    public void addUser(User user);
    public User getUserByEmail(User user);
    public User getUserByLoginAndPassword(User user);
    public List<User> getUsers();
    public User getUserById(User user);
    public int getLastId();
    public void activateUser(User user);
}

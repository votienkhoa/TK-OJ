package model.bo;

import model.bean.User;
import model.dao.LoginDAO;

public class LoginBO {
    LoginDAO loginDAO = new LoginDAO();
    public User validateUser(String username, String password){
        User user = loginDAO.getUser(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }
}

package model.bo;

import model.bean.Language;
import model.dao.LanguageDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class LanguageBO {
    LanguageDAO languageDAO = new LanguageDAO();
    public HashMap<Integer, String> getLanguages() {
        return languageDAO.getLanguages();
    }
    public String getLanguageByID(int id) {
        return languageDAO.getLanguageByID(id);
    }
}

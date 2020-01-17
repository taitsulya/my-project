package com.julat.myproject;

import com.julat.myproject.domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Service {


    public List<String> loginList (List<User> userList) {
        List<String> resultList = new LinkedList<>();
        for (int i = 0; i < userList.size(); i++) {
            resultList.add(userList.get(i).getLogin());
        }
        return resultList;
    }

    public List<String> passwordList (List<User> userList) {
        List<String> resultList = new LinkedList<>();
        for (int i = 0; i < userList.size(); i++) {
            resultList.add(userList.get(i).getPassword());
        }
        return resultList;
    }

    public List<Integer> idList (List<User> userList) {
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < userList.size(); i++) {
            resultList.add(userList.get(i).getId());
        }
        return resultList;
    }

    public static void execute(String sql) {
        try {
            Connection connection = ConnectionSource.instance().createConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException ignored) {}
    }

    public static ResultSet getResultSet(String sql) {
        try {
            Connection connection = ConnectionSource.instance().createConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    public static List<User> getUserList(ResultSet resultSet) {
        try {
            List<User> userList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    userList.add(getUser(resultSet));
                }
            }
            return userList;
        } catch (NullPointerException | SQLException e) {
            return null;
        }
    }

    public static User getUser(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer role = resultSet.getInt("role");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        Integer gender = resultSet.getInt("gender");
        LocalDate birthdate = LocalDate.parse(resultSet.getString("birthdate"));
        Integer firstLanguage = resultSet.getInt("first_language");
        Integer learningLanguage = resultSet.getInt("learning_language");
        Integer languageLevel = resultSet.getInt("language_level");
        String image = resultSet.getString("image");
        String sessionUUID = resultSet.getString("SESSION_UUID");
        String description = resultSet.getString("description");
        return new User(
                id,
                role,
                login,
                password,
                name,
                gender,
                birthdate,
                firstLanguage,
                learningLanguage,
                languageLevel,
                image,
                sessionUUID,
                description);
    }

    public static Message getMessage(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer sender = resultSet.getInt("sender");
        Integer receiver = resultSet.getInt("receiver");
        String text = resultSet.getString("text");
        LocalDateTime time = resultSet.getTimestamp("created_at").toLocalDateTime();
        return new Message(
                id,
                sender,
                receiver,
                text,
                time);
    }

    public static List<Message> getMessageList(ResultSet resultSet) {
        try {
            List<Message> messageList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    messageList.add(getMessage(resultSet));
                }
            }
            return messageList;
        } catch (NullPointerException | SQLException e) {
            return null;
        }
    }

    public static FullMessage getFullMessage(ResultSet resultSet) throws SQLException {
        Integer sender = resultSet.getInt("sender");
        Integer receiver = resultSet.getInt("receiver");
        String text = resultSet.getString("text");
        LocalDateTime time = resultSet.getTimestamp("created_at").toLocalDateTime();
        String name = resultSet.getString("name");
        String image = resultSet.getString("image");
        return new FullMessage(
                sender,
                receiver,
                text,
                time,
                name,
                image);
    }

    public static List<FullMessage> getFullMessageList(ResultSet resultSet) {
        try {
            List<FullMessage> messageList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    messageList.add(getFullMessage(resultSet));
                }
            }
            return messageList;
        } catch (NullPointerException | SQLException e) {
            return null;
        }
    }
    public static Language getLanguage(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Language(
                id,
                name);
    }

    public static List<Language> getLanguageList(ResultSet resultSet) {
        try {
            List<Language> languageList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    languageList.add(getLanguage(resultSet));
                }
            }
            return languageList;
        } catch (NullPointerException | SQLException e) {
            return null;
        }
    }

    public static String getLanguageById(Integer id) {
        ResultSet resultSet = getResultSet("select * from language where id = " + id);
        return getLanguageList(resultSet).get(0).getName();
    }

    public static LanguageLevel getLanguageLevel(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String level = resultSet.getString("level");
        return new LanguageLevel(
                id,
                level);
    }

    public static List<LanguageLevel> getLanguageLevelList(ResultSet resultSet) {
        try {
            List<LanguageLevel> languageLevelList = new LinkedList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    languageLevelList.add(getLanguageLevel(resultSet));
                }
            }
            return languageLevelList;
        } catch (NullPointerException | SQLException e) {
            return null;
        }
    }

    public static String getLanguageLevelById(Integer id) {
        ResultSet resultSet = getResultSet("select * from language_level where id = " + id);
        return getLanguageLevelList(resultSet).get(0).getLevel();
    }

}

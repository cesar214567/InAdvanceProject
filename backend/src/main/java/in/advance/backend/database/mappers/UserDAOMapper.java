package in.advance.backend.database.mappers;

import in.advance.backend.database.entitites.PhoneDao;
import in.advance.backend.database.entitites.UserDao;
import in.advance.backend.domain.models.Phone;
import in.advance.backend.domain.models.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.stream.Collectors;

@UtilityClass
public class UserDAOMapper {
    public UserDao mapUserDAO(User user){
        return UserDao.builder()
                .id(user.getId())
                .name(user.getName())
                .created(user.getCreated())
                .modified(user.getModified())
                .password(user.getPassword())
                .token(user.getToken())
                .phones(user.getPhones() ==null ? new ArrayList<>() :
                        user.getPhones().stream()
                        .map(phone -> PhoneDao.builder()
                                .cityCode(phone.getCityCode())
                                .number(phone.getNumber())
                                .countryCode(phone.getCountryCode())
                                .build()).toList())
                .email(user.getEmail())
                .lastLogin(user.getLastLogin())
                .isActive(user.getIsActive())
                .build();
    }
    public User mapDAOToUser(UserDao userDao){
        return User.builder()
                .id(userDao.getId())
                .name(userDao.getName())
                .created(userDao.getCreated())
                .modified(userDao.getModified())
                .token(userDao.getToken())
                .phones(userDao.getPhones().stream()
                        .map(phone -> Phone.builder()
                                .cityCode(phone.getCityCode())
                                .number(phone.getNumber())
                                .countryCode(phone.getCountryCode())
                                .build()).toList())
                .email(userDao.getEmail())
                .lastLogin(userDao.getLastLogin())
                .isActive(userDao.getIsActive())
                .build();
    }
}

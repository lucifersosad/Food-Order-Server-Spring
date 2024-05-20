package spring.api.uteating.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.api.uteating.entity.User;
import spring.api.uteating.model.UserModel;
import spring.api.uteating.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsernameOrEmail(usernameOrEmail);

        if (user == null) {
            throw new UsernameNotFoundException("Cound not find user");
        }

        return new MyUserService(user);
    }

    public User getUser(String username) {
        return userRepository.getUserByUsernameOrEmail(username);
    }

    public UserModel getUserModel(String idUser) {
        if (!existsById(idUser)) {
            return null;
        }
        User user = getById(idUser);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        return userModel;
    }

    public UserModel updateUser(UserModel model) {
        String userId = model.getUserId();
        if (userId != null) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (model.getPhone() != null && !model.getPhone().isEmpty()) {
                    user.setPhone(model.getPhone());
                }
                if (model.getAvatarURL() != null && !model.getAvatarURL().isEmpty()) {
                    user.setAvatarURL(model.getAvatarURL());
                }
                userRepository.save(user);
                UserModel userModel = new UserModel();
                BeanUtils.copyProperties(user, userModel);
                return userModel;
            }
        }
        return null;
    }

    @Deprecated
    public User getById(String s) {
        return userRepository.getById(s);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllById(Iterable<String> strings) {
        return userRepository.findAllById(strings);
    }

    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    public long count() {
        return userRepository.count();
    }

    public void deleteById(String s) {
        userRepository.deleteById(s);
    }

    public <S extends User> Optional<S> findOne(Example<S> example) {
        return userRepository.findOne(example);
    }

    public boolean existsById(String s) {
        return userRepository.existsById(s);
    }
}

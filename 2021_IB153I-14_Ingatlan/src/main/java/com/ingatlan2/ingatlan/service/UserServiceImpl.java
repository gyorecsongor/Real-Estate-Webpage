package com.ingatlan2.ingatlan.service;

import com.ingatlan2.ingatlan.entities.Advert;
import com.ingatlan2.ingatlan.entities.Role;
import com.ingatlan2.ingatlan.entities.User;
import com.ingatlan2.ingatlan.repository.RoleRepository;
import com.ingatlan2.ingatlan.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl extends JdbcDaoSupport implements UserService, UserDetailsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private final String USER_ROLE = "USER";

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }



    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String registerUser(User userToRegister) {
        User userCheck = userRepository.findByEmail(userToRegister.getEmail());

        if (userCheck != null)
            return "alreadyExists";

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            userToRegister.getRoles().add(userRole);
        } else {
            userToRegister.addRoles(USER_ROLE);
        }

        userToRegister.setEnabled(false);
        userToRegister.setActivation(generateKey());
        userRepository.save(userToRegister);

        return "ok";
    }

    public String generateKey()
    {
        String key = "";
        Random random = new Random();
        char[] word = new char[16];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        String toReturn = new String(word);
        log.debug("random code: " + toReturn);
        return new String(word);
    }

    @Override
    public String userActivation(String code) {
        User user = userRepository.findByActivation(code);
        if (user == null)
            return "noresult";

        user.setEnabled(true);
        user.setActivation("");
        userRepository.save(user);
        return "ok";
    }

    public User getUserById(long id){
        String sql = "SELECT * FROM users WHERE userid="+id+";";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<User> result = new ArrayList<User>();
        for(Map<String, Object> row:rows){
            User user = new User();
            user.setFullname((String)row.get("fullname"));
            user.setEmail((String)row.get("email"));
            user.setPhonenumber((String) row.get("phonenumber"));
            user.setBirthdate((String) row.get("birthdate"));
            user.setUserid((long) row.get("userid"));
            result.add(user);
        }
        if (result.size()==0)
            return null;
        return result.get(0);
    }
}

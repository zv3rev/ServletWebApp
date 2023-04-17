package services;

import entity.Profile;
import repository.ConnectionFactory;
import repository.ProfileRepository;

import java.sql.SQLException;

public class ProfileService {
    private ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public boolean createProfile(Profile profile){
        profile.setPassword(String.valueOf(profile.getPassword().hashCode()));
        return repository.insert(profile);
    }

    public Profile validateProfile(String username, String password) {
        Profile result;
        try {
            result = repository.getByUsername(username);
            if (result != null && String.valueOf(password.hashCode()).equals(result.getPassword())){
                return result;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}

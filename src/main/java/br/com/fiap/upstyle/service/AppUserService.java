package br.com.fiap.upstyle.service;


import br.com.fiap.upstyle.model.AppUser;
import br.com.fiap.upstyle.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser registerUser(AppUser user) throws Exception {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            HashSet<String> roleUser = new HashSet<>(Collections.singletonList("ROLE_USER"));
            user.setRoles(roleUser);

            return appUserRepository.save(user);
        }catch (Exception e){
            throw new Exception("Erro ao cadastrar usuário " + e.getMessage());
        }

    }

    public Optional<AppUser> findByUsername(String username) {
        return Optional.ofNullable(appUserRepository.findByUsername(username));
    }

    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }

    public void edit(Long id, AppUser user) {
        AppUser appUser = appUserRepository.findById(id).get();
        appUser.edit(user);
        appUserRepository.save(appUser);
    }

    public void delete(Long id) {
        AppUser referenceById = appUserRepository.getReferenceById(id);
        appUserRepository.delete(referenceById);
    }

    public Integer totalCount() {
        return appUserRepository.totalAppUsersCount();
    }
}
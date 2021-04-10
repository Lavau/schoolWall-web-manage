package top.leeti.service;

import top.leeti.entity.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator findAdministratorByUsername(String username);

    Administrator findAdministratorById(String id);

    List<Administrator> findAdministrators();

    void updateAdministrator(Administrator administrator);

    void insertAdministrator(Administrator administrator);

    void deleteAdministratorById(String id);
}

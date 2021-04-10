package top.leeti.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.mapper.AdministratorMapper;
import top.leeti.service.AdministratorService;
import top.leeti.entity.Administrator;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Resource
    private AdministratorMapper administratorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Administrator findAdministratorByUsername(String username) {
        return administratorMapper.findAdministratorByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Administrator findAdministratorById(String id) {
        return administratorMapper.findAdministratorById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Administrator> findAdministrators() {
        return administratorMapper.findAdministrators();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAdministrator(Administrator administrator) {
        administratorMapper.updateAdministrator(administrator);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAdministrator(Administrator administrator) {
        administratorMapper.insertAdministrator(administrator);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdministratorById(String id) {
        administratorMapper.deleteAdministratorById(id);
    }
}

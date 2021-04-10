package top.leeti.service;

import top.leeti.entity.College;

import java.util.List;

public interface CollegeService {
    List<College> findColleges();

    College findCollegeById(Integer id);

    void insertCollege(College college);

    void updateCollege(College college);
}

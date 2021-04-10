package top.leeti.service.impl;

import lombok.extern.slf4j.Slf4j;
import top.leeti.mapper.CollegeMapper;
import top.leeti.service.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.entity.College;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CollegeServiceImpl implements CollegeService {

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<College> findColleges() {
        return collegeMapper.findColleges();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public College findCollegeById(Integer id) {
        return collegeMapper.findCollegeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCollege(College college) {
        collegeMapper.insertCollege(college);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCollege(College college) {
        collegeMapper.updateCollege(college);
    }
}

package com.imooc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.mapper.StuMapper;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Stu;
import com.imooc.pojo.Users;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Stu> implements StuService {


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return getBaseMapper().selectById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu() {

        Stu stu = new Stu();
        stu.setName("jack");
        stu.setAge(19);
        this.getBaseMapper().insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(Long id) {

        Stu stu = new Stu();
        stu.setId(id);
        stu.setName("lucy");
        stu.setAge(20);
        this.getBaseMapper().updateById(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {
        this.getBaseMapper().deleteById(id);
    }



    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        this.getBaseMapper().insert(stu);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveChildren() {
        saveChild1();
        int a = 1 / 0;
        saveChild2();
    }

    public void saveChild1() {
        Stu stu1 = new Stu();
        stu1.setName("child-1");
        stu1.setAge(11);
        this.getBaseMapper().insert(stu1);
    }
    public void saveChild2() {
        Stu stu2 = new Stu();
        stu2.setName("child-2");
        stu2.setAge(22);
        this.getBaseMapper().insert(stu2);
    }
}

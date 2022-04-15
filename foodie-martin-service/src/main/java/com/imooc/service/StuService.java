package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pojo.Stu;

public interface StuService extends IService<Stu> {

    public Stu getStuInfo(int id);

    public void saveStu();

    public void updateStu(Long id);

    public void deleteStu(int id);

    public void saveParent();
    public void saveChildren();
}

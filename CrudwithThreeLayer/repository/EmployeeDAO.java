package com.hb.crud.CrudwithThreeLayer.repository;

import com.hb.crud.CrudwithThreeLayer.model.EmployeeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface EmployeeDAO{
    public EmployeeBO save(EmployeeBO employeeBO);
    public EmployeeBO update(EmployeeBO employeeBO);

    public EmployeeBO findById(Long aLong);

    public List<EmployeeBO> findAll();

    public void deleteById(Long id);

    public void deleteAll();
    List<EmployeeBO> searchAndGetAllEmp(String s);

}

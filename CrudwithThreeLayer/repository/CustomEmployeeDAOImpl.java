package com.hb.crud.CrudwithThreeLayer.repository;

import com.hb.crud.CrudwithThreeLayer.model.AddressBO;
import com.hb.crud.CrudwithThreeLayer.model.EmployeeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomEmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private EntityManager entityManager;

    public EmployeeBO save(EmployeeBO employeeBO) {

        entityManager.persist(employeeBO);
        return employeeBO;
    }

    public EmployeeBO update(EmployeeBO employeeBO){
        return entityManager.merge(employeeBO);
    }

    public EmployeeBO findById(Long id) {
         return entityManager.find(EmployeeBO.class, id);
    }

    public List<EmployeeBO> findAll() {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery cq= cb.createQuery();

        Root<EmployeeBO> employeeBORoot=cq.from(EmployeeBO.class);

        cq.select(employeeBORoot);
        TypedQuery<EmployeeBO> query= entityManager.createQuery(cq);

        return query.getResultList();
    }

    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(EmployeeBO.class,id));

    }
    public void deleteAll() {

    }

    public List<EmployeeBO> searchAndGetAllEmp(String s) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery cq= cb.createQuery();

        Root<EmployeeBO> employeeBORoot=cq.from(EmployeeBO.class);

        Predicate predicate1= cb.like(employeeBORoot.get("firstName"),"%"+s+"%");
        Predicate predicate2= cb.like(employeeBORoot.get("lastName"),"%"+s+"%");
        Predicate predicate3= cb.like(employeeBORoot.get("address").get("street"),"%"+s+"%");

        Predicate predicate=cb.or(predicate1,predicate2,predicate3);

        cq.where(predicate);

        cq.select(employeeBORoot);
        TypedQuery<EmployeeBO> query= entityManager.createQuery(cq);

        return query.getResultList();
    }

}

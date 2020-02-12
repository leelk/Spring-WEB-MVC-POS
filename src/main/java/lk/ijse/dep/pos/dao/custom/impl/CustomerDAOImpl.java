package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.entity.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {


    @Override
    public String getLastCustomerId() throws Exception {

        return (String) getSession().createNativeQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1").uniqueResult();
    }
}

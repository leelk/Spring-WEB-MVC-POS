package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.QueryDAO;
import lk.ijse.dep.pos.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryDAOImpl implements QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public CustomEntity getOrderInfo(int orderId) throws Exception {
        return (CustomEntity) getSession().createQuery("SELECT NEW lk.ijse.dep.pos.entity.CustomEntity(o.id, c.id,c.name,o.date) FROM Customer c INNER JOIN c.orders o WHERE o.id=?1").setParameter(1, orderId).uniqueResult();
    }


    @Override
    public CustomEntity getOrderInfo2(int orderId) throws Exception {/*
        ResultSet rst = CrudUtil.execute("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId " +
                "INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id=? GROUP BY orderId", orderId);
        if (rst.next()){
            return new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5));
        }else{
            return null;
        }*/
        CustomEntity o = null;
        return o;
    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {

        NativeQuery nativeQuery = getSession().createNativeQuery("SELECT O.id AS orderId, C.customerId AS customerId, C.name AS customerName, O.date AS orderDate, SUM(OD.qty * OD.unitPrice) AS orderTotal  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customer_id " +
                "INNER JOIN OrderDetail OD on O.id = OD.order_id WHERE O.id LIKE ?1 OR C.customerId LIKE ?2 OR C.name LIKE ?3 OR O.date LIKE ?4 GROUP BY O.id")
                .setParameter(1, query).setParameter(2, query).setParameter(3, query).setParameter(4, query);

        Query<CustomEntity> query1 = nativeQuery.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));
        List<CustomEntity> list = query1.list();
        return list;
    }
}

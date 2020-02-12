package lk.ijse.dep.pos.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements SuperEntity{

    @Id
    private String customerId;
    private String name;
    private String address;
//    private Gender gender;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }

//    public Customer(String customerId, String name, String address, Gender gender) {
//        this.customerId = customerId;
//        this.name = name;
//        this.address = address;
//        this.setGender(gender);
//    }

    //    public Customer(String customerId, String name, String address) {
//        this.customerId = customerId;
//        this.name = name;
//        this.address = address;
//    }



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
//                ", gender=" + gender +
                '}';
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        order.setCustomer(this);
        this.orders.add(order);
    }

    public void removeOrder(Order order){
        if (order.getCustomer() != this){
            throw  new RuntimeException("Invalid Order");

        }
        order.setCustomer(null);
        this.orders.remove(order);
    }
}

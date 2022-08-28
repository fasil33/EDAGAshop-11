package Impl;

import bean.Order;
import bean.User;
import Dao.OrderRepository;
import Dao.UserRepository;
import service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service layer class implements the accessor methods of {@link Order} objects
 * in the {@link OrderService} interface database.
 * The class is marked with the @Service annotation - an annotation announcing that this class
 * is a service - a component of the service layer. Service is a subtype of @Component class.
 * Using this annotation will automatically search for service beans.
 *
 * @author Miroslav Khotinskiy (merikbest2015@gmail.com)
 * @version 1.0
 * @see Order
 * @see OrderService
 * @see OrderRepository
 */
@Service
public class OrderServiceImpl implements OrderService {
    /**
     * Implementation of the {@link OrderRepository} interface
     * for working with orders with a database.
     */
    private final OrderRepository orderRepository;

    /**
     * Implementation of the {@link UserRepository} interface
     * for working with user with a database.
     */
    private final UserRepository userRepository;

    /**
     * Implementation of the {@link MailSender} class
     * for working with email.
     */
    private final MailSender mailSender;

    /**
     * Constructor for initializing the main variables of the order service.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param orderRepository implementation of the {@link OrderRepository} interface
     *                        for working with orders with a database.
     * @param userRepository  implementation of the {@link UserRepository} interface
     *                        for working with user with a database.
     * @param mailSender      implementation of the {@link MailSender} class
     *                        for working with email.
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, MailSender mailSender) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    /**
     * Return list of all user orders.
     *
     * @return list of user {@link Order}.
     */
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Save order info.
     *
     * @param order order object to return.
     * @return The {@link Order} class object which will be saved in the database.
     */
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Returns list of orders authenticated user.
     *
     * @param user name of authenticated user.
     * @return An object of type {@link List} is a list of orders of authenticated user.
     */
    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }

    /**
     * Saves the user order and send message with order params to email address.
     *
     * @param validOrder  requested valid order.
     * @param userSession requested authenticated user.
     * @return The {@link Order} class object which will be saved in the database.
     */
    @Override
    public Order postOrder(Order validOrder, User userSession) {
        User user = userRepository.findByEmail(userSession.getEmail());
        Order order = new Order(user);

        order.getShoeList().addAll(user.getShoeList());
        order.setTotalPrice(validOrder.getTotalPrice());
        order.setFirstName(validOrder.getFirstName());
        order.setLastName(validOrder.getLastName());
        order.setCity(validOrder.getCity());
        order.setAddress(validOrder.getAddress());
        order.setPostIndex(validOrder.getPostIndex());
        order.setEmail(validOrder.getEmail());
        order.setPhoneNumber(validOrder.getPhoneNumber());
        user.getShoeList().clear();

        orderRepository.save(order);

        StringBuilder shoes = new StringBuilder();
        order.getShoeList().forEach((shoe) ->
        {
            shoes.append(shoe.getShoer());
            shoes.append(" ");
            shoes.append(perfume.getShoeTitle());
            shoes.append(" — $");
            shoes.append(shoe.getPrice());
            shoes.append(".00");
            shoes.append("\n");
        });

        String subject = "Order #" + order.getId();
        String message = "Hello " + order.getFirstName() + "!\n" +
                "Thank you for your order in Perfume online store.\n" +
                "Your order number is " + order.getId() + "\n" +
                "Date: " + order.getDate() + "\n" +
                "Name: " + order.getFirstName() + " " + order.getLastName() + "\n" +
                "Address: " + order.getCity() + ", " + order.getAddress() + "\n" +
                "Post index: " + order.getPostIndex() + "\n" +
                "Phone: " + order.getPhoneNumber() + "\n" +
                "Perfumes: " + "\n" + shoe + "\n" +
                "Total price: $" + order.getTotalPrice();

        mailSender.send(order.getEmail(), subject, message);

        return order;
    }
}

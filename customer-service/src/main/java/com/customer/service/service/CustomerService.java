package com.customer.service.service;

import com.customer.service.configuration.KafkaTopicConfig;
import com.customer.service.entity.Customer;
import com.customer.service.kafka.KafkaSender;
import com.customer.service.model.NotificationDTO;
import com.customer.service.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final SequenceGeneratorService sequenceGenerator;

    private final KafkaSender kafkaSender;

    public CustomerService(CustomerRepository customerRepository, SequenceGeneratorService sequenceGenerator, KafkaSender kafkaSender) {
        this.customerRepository = customerRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.kafkaSender = kafkaSender;
    }

    public List<Customer> getAll() {
        log.info("Have been called the getAll method on the CustomerService class");
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        log.info("Have been called the getCustomerById method on the CustomerService class");
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.isPresent() ? customer.get() : null;
    }

    public Customer save(Customer customer) {
        log.info("Have been called the save method on the CustomerService class");
        customer.setId(sequenceGenerator.generateSequence(Customer.SEQUENCE_NAME));

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDescription("The customer " + customer.getName() + " " + customer.getSurname() + " was saved successfully");
        notificationDTO.setNotificationDate(LocalDateTime.now());
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, notificationDTO);

        return customerRepository.save(customer);
    }

    public Customer update(int id, Customer customer) {
        log.info("Have been called the update method on the CustomerService class");
        Customer toUpdate = getCustomerById(id);
        toUpdate.setName(customer.getName());
        toUpdate.setSurname(customer.getSurname());
        toUpdate.setBudget(customer.getBudget());

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDescription("The customer " + toUpdate.getName() + " " + toUpdate.getSurname() + " was updated successfully");
        notificationDTO.setNotificationDate(LocalDateTime.now());
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, notificationDTO);

        return customerRepository.save(toUpdate);
    }

    public void delete(int id) {
        log.info("Have been called the delete method on the CustomerService class");
        Customer toDelete = getCustomerById(id);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDescription("The customer " + toDelete.getName() + " " + toDelete.getSurname() + " was deleted successfully");
        notificationDTO.setNotificationDate(LocalDateTime.now());
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, notificationDTO);

        customerRepository.deleteById(id);
    }

}

package com.customer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.service.entity.Customer;
import com.customer.service.repository.CustomerRepository;
import com.customer.service.service.CustomerService;
import com.customer.service.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceApplicationTests {

	// There are created the mocks object that are used inside the service
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;

	// There is created an instance of the service and injected the mocks declared
	// before
	@InjectMocks
	private CustomerService customerService;

	private static List<Customer> customerListMock;

	@BeforeAll
	static void init() {
		customerListMock = new ArrayList<>();
		customerListMock.add(Customer.builder().id(1).name("Pedro").surname("Perez").budget(100).build());
	}

	@Test
	public void shouldListAllCustomers() {
		// GIVEN
		when(customerRepository.findAll()).thenReturn(customerListMock);
		// WHEN
		List<Customer> customerList = customerService.getAll();
		// THEN
		assertThat(customerList).isNotNull() 
								.isNotEmpty()
								.hasSize(1);
	}

	@Test
	public void shouldGetCustomerById() {
		// GIVEN
		Optional<Customer> customerMock = Optional.of(customerListMock.get(0));
		when(customerRepository.findById(1)).thenReturn(customerMock);
		// WHEN
		Customer customer = customerService.getCustomerById(1);
		// THEN
		assertThat(customer).isNotNull();
		assertThat(customer.getName()).isEqualTo("Pedro");
	}

	@Test
	public void shouldSaveCustomer() {
		// GIVEN
		Customer customerMock = Customer.builder().name("Maria").surname("Rodriguez").budget(50).build();
		when(customerRepository.save(customerMock)).thenReturn(customerMock);
		when(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME)).thenReturn(2L);
		// WHEN
		Customer customer = customerService.save(customerMock);
		// THEN
		assertThat(customer).isNotNull();
		assertThat(customer.getId()).isEqualTo(2);
	}

	@Test
	public void shouldUpdateCustomer() {
		// GIVEN
		Customer customerMock = Customer.builder().name("Maria").surname("Rodriguez").budget(50).build();
		Optional<Customer> customerMockOp = Optional.of(customerMock);
		Customer updateCustomer = Customer.builder().name("Maria Mod").surname("Rodriguez").budget(50).build();

		when(customerRepository.findById(1)).thenReturn(customerMockOp);
		when(customerRepository.save(customerMock)).thenReturn(updateCustomer);

		// WHEN
		Customer customerUpdated = customerService.update(1, updateCustomer);
		// THEN
		assertThat(customerUpdated.getName()).isEqualTo("Maria Mod");
	}

}

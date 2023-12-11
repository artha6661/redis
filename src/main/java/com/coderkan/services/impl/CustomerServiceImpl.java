package com.coderkan.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.Cache;

import com.coderkan.models.Customer;
import com.coderkan.repositories.CustomerRepository;
import com.coderkan.services.CustomerService;

@Service
@CacheConfig(cacheNames = "customerCache")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Cacheable(cacheNames = "customers", key = "'all_customer'", sync = true)
	@Override
	public List<Customer> getAll() {
		waitSomeTime();
		return this.customerRepository.findAll();
	}

	@Caching(
		//delete cache all_customer biar klo ada perubahan data nya tidak pakai cache lama saat getAll
		evict = {@CacheEvict(value = "customers", key = "'all_customer'")},
		//put cache
		put = {@CachePut(value = "customers", key = "'customer_' + #result.id")}
	)
	@Override
	public Customer add(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Caching(
		//delete cache all_customer biar klo ada perubahan data nya tidak pakai cache lama saat getAll
		evict = {@CacheEvict(value = "customers", key = "'all_customer'")},
		//put cache
     	put = {@CachePut(value = "customers", key = "'customer_' + #result.id")}
	)
	@Override
	public Customer update(Customer customer) {
		Optional<Customer> optCustomer = this.customerRepository.findById(customer.getId());
		if (!optCustomer.isPresent())
			return null;
		Customer repCustomer = optCustomer.get();
		repCustomer.setName(customer.getName());
		repCustomer.setContactName(customer.getContactName());
		repCustomer.setAddress(customer.getAddress());
		repCustomer.setCity(customer.getCity());
		repCustomer.setPostalCode(customer.getPostalCode());
		repCustomer.setCountry(customer.getCountry());
		return this.customerRepository.save(repCustomer);
	}

	@Caching(
		//delete cache all_customer biar klo ada perubahan data nya tidak pakai cache lama saat getAll
		evict = {@CacheEvict(value = "customers", key = "'all_customer'")}
		)
	//delete cache khusus untuk id yang diinput untuk dihapus
	@CacheEvict(value = "customers", key = "'customer_' + #id")
	@Override
	public void delete(long id) {
		this.customerRepository.deleteById(id);
	}

	@Cacheable(cacheNames = "customers", key = " 'customer_' + #id ", unless = "#result == null")
	@Override
	public Customer getCustomerById(long id) {
		waitSomeTime();
		return this.customerRepository.findById(id).orElse(null);
	}

	//ini buat nandain kalau tidak ada cache pas dijalanin
	private void waitSomeTime() {
		System.out.println("Long Wait Begin");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Long Wait End");
	}

}
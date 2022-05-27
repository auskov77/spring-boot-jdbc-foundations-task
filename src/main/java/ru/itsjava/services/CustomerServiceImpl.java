package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.CustomerDao;
import ru.itsjava.domain.Customer;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerDao customerDao;

    @Override
    public void insert(Customer customer) {
        long id = customerDao.insert(customer);
        System.out.println("ID нового владельца животного = " + id);
    }
}

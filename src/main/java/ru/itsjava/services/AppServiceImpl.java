package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService{
    private final CustomerService customerService;
    private final IOService ioService;
    private final PetDao petDao;

    @Override
    public void start() {
        System.out.println("Добро пожаловать в наш Клуб животных!");
        while (true) {
            System.out.println("Введите номер меню");
            System.out.println("1 -- напечатать всех владельцев животных, " +
                    "2 -- добавить владельца животного, " +
                    "остальное выход");
            int menuNum = ioService.inputInt();

            if (menuNum == 1) {
                printAllCustomers();
            } else if (menuNum == 2) {
                insertCustomer();
            } else {
                System.exit(0);
            }
        }

    }

    @Override
    public void printAllCustomers() {
        List<Customer> customerList = customerService.findAll();
        System.out.println("Список всех владельцев животных " + customerList);
    }

    @Override
    public void insertCustomer() {
        System.out.println("Введите владельца");
        System.out.println("Введите ФИО");
        String name = ioService.input();
        System.out.println("Введите электронный адрес");
        String email = ioService.input();
        System.out.println("Введите животное");
        String animal = ioService.input();
        System.out.println("Введите породу животного");
        String breed = ioService.input();
        Pet customerBreed = petDao.findByBreed(breed);

        Customer newCustomer = new Customer(name, email, animal, customerBreed);

        customerService.insert(newCustomer);
    }
}

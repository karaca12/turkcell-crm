package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.client.InvoiceServiceClient;
import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.client.ProductServiceClient;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;
    private final ProductServiceClient productServiceClient;
    private final InvoiceServiceClient invoiceServiceClient;

    private static final Random random = new Random();

    public void customerWithSameNationalityIdCannotExist(String nationalityId) {
        if (customerRepository.existsByNationalityId(nationalityId)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
        }
    }

    public String getCustomerIdFromOrderNumberOrAccountNumber(String orderNumber, String accountNumber) {
        String customerIdFromOrder = getCustomerIdFromOrderNumber(orderNumber);
        String customerIdFromAccount = getCustomerIdFromAccountNumber(accountNumber);
        if (customerIdFromOrder == null && customerIdFromAccount == null) {
            return null;
        } else if (customerIdFromOrder != null && customerIdFromAccount == null) {
            return customerIdFromOrder;
        } else if (customerIdFromOrder == null && customerIdFromAccount != null) {
            return customerIdFromAccount;
        } else {
            if (customerIdFromOrder.equals(customerIdFromAccount)) {
                return customerIdFromOrder;
            }else{
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND));
            }
        }
    }

    public String getCustomerIdFromOrderNumber(String orderNumber) {
        if (orderNumber != null) {
            return orderServiceClient.getCustomerIdByOrderId(orderNumber);
        } else return null;
    }

    public String getCustomerIdFromAccountNumber(String accountNumber) {
        if (accountNumber != null) {
            return invoiceServiceClient.getCustomerIdByAccountNumber(accountNumber);
        } else return null;
    }

    public Customer getCustomerFromOptional(Optional<Customer> optionalCustomer) {
        return optionalCustomer.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND)));
    }

    public void ensureCustomerHasNoActiveProducts(Customer customer) {
        //TODO: implement after product service is implemented
    }

    public void checkIfSearchIsEmpty(List<SearchCustomerResponse> response) {
        if (response.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND));
        }
    }

    public String generateCustomerId() {
        String customerId;
        customerId = generateUniqueCustomerId();
        if (!isUniqueId(customerId)) {
            return customerId;
        } else {
            return generateCustomerId();
        }
    }

    private String generateUniqueCustomerId() {
        StringBuilder customerIdBuilder = new StringBuilder();
        customerIdBuilder.append(random.nextInt(5) + 1);
        for (int i = 0; i < 6; i++) {
            customerIdBuilder.append(random.nextInt(10));
        }
        return customerIdBuilder.toString();
    }

    private boolean isUniqueId(String customerId) {
        return customerRepository.existsByCustomerId(customerId);
    }


}

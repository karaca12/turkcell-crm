package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.client.InvoiceServiceClient;
import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.IndividualCustomerRepository;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateIndividualCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.SearchIndividualCustomerResponse;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;
    private final InvoiceServiceClient invoiceServiceClient;

    @Value("${foreignNatId}")
    private String foreignNatId;

    public boolean individualCustomerWithSameNationalityIdCannotExistUnlessForeign(String nationalityId) {
        if (nationalityId.equals(foreignNatId)) {
            return true;
        } else {
            if (individualCustomerRepository.existsByNationalityId(nationalityId)) {
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
            } else {
                return true;
            }
        }
    }

    public String getCustomerIdFromOrderNumberOrAccountNumber(String orderNumber, String accountNumber) {
        String accountNumberFromOrder = getAccountNumberFromOrderNumber(orderNumber);
        String customerIdFromOrder = getCustomerIdFromAccountNumber(accountNumberFromOrder);
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
            } else {
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND));
            }
        }
    }

    public String getAccountNumberFromOrderNumber(String orderNumber) {
        if (orderNumber != null) {
            return orderServiceClient.getAccountNumberByOrderId(orderNumber);
        } else return null;
    }

    public String getCustomerIdFromAccountNumber(String accountNumber) {
        if (accountNumber != null) {
            return invoiceServiceClient.getCustomerIdByAccountNumber(accountNumber);
        } else return null;
    }

    public void ensureCustomerHasNoActiveProducts(Customer customer) {
        if (orderServiceClient.customerHasActiveProducts(customer.getCustomerId()).isHasActiveProducts()){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CUSTOMER_HAS_ACTIVE_PRODUCT));
        }
    }

    public void checkIfSearchIsEmpty(List<SearchIndividualCustomerResponse> response) {
        if (response.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND));
        }
    }

    public void checkIfNationalityIdAlreadyExists(Customer customer, UpdateIndividualCustomerInfoRequest request) {
        if (!request.getNationalityId().equals(customer.getIndividualCustomer().getNationalityId())) {
            individualCustomerWithSameNationalityIdCannotExistUnlessForeign(request.getNationalityId());
        }
    }

    public List<SearchIndividualCustomerResponse> sortSearchResponse(List<SearchIndividualCustomerResponse> response) {
        response.sort(Comparator.comparing(SearchIndividualCustomerResponse::getFirstName)
                .thenComparing(SearchIndividualCustomerResponse::getLastName)
                .thenComparing(SearchIndividualCustomerResponse::getCustomerId));

        return response;
    }
}
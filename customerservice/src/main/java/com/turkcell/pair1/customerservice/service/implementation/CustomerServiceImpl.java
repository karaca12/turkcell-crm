package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import com.turkcell.pair1.customerservice.service.rules.CustomerBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;
    private final AddressService addressService;
    private final CustomerBusinessRules businessRules;


    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request) {

        if (request.getOrderNumber() != null) {
            int customerId = orderServiceClient.getCustomerIdByOrderId(request.getOrderNumber());
            List<SearchCustomerResponse> response = new ArrayList<>();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                    new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR)));
            SearchCustomerResponse searchCustomerResponse =
                    CustomerMapper.INSTANCE.getSearchCustomerResponseFromCustomer(customer);
            response.add(searchCustomerResponse);
            return response;
        } else {
            List<SearchCustomerResponse> response = customerRepository.search(request);
            if (response.isEmpty()) {
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR));
            }
            return response;
        }
    }

    @Override
    public GetCustomerInfoResponse getByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findByCustomerId(String.valueOf(customerId))
                .orElseThrow(() ->
                        new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR)));
        return CustomerMapper.INSTANCE.getCustomerInfoResponseFromCustomer(customer);
    }

    @Override
    public CreateCustomerResponse create(CreateCustomerRequest request) {
        businessRules.customerWithSameNationalityIdCannotExist(request.getNationalityId());
        Customer customer = CustomerMapper.INSTANCE.getCustomerFromCreateCustomerRequest(request);
        Customer savedCustomer = customerRepository.save(customer);

        CreateCustomerResponse response=CustomerMapper.INSTANCE.getCreateCustomerResponseFromCustomer(savedCustomer);
        response.setAddressList(addressService.addAddressesForCustomer(request.getAddressList(), savedCustomer));
        return response;
    }

    @Override
    public void checkNationalityId(Integer nationalityId) {
        businessRules.customerWithSameNationalityIdCannotExist(nationalityId);
    }

    @Override
    public void updateInfo(UpdateCustomerInfoRequest request) {
        customerRepository.updateCustomerInfoById(request.getFirstName(),
                request.getMiddleName(),
                request.getLastName(),
                request.getBirthDate(),
                request.getGender(),
                request.getFatherName(),
                request.getMotherName(),
                request.getNationalityId(),
                request.getUpdatedId());
    }

    @Override
    public void createAddress(Integer id, List<AddAddressToCustomerRequest> request) {
        addressService.addAddressesForCustomer(request, customerRepository.findById(id).orElseThrow());
    }

    @Override
    public String lbTest() {
        return orderServiceClient.lbTest();
    }

}

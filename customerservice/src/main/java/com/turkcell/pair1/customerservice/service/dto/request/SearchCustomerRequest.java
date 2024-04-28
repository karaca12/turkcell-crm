package com.turkcell.pair1.customerservice.service.dto.request;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.customerservice.service.validation.annotation.SearchMobilePhone;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerRequest {
    @Length(min = 1, max = 11,message = Messages.ValidationErrors.LENGTH)
    @Pattern(regexp = "^[0-9]+$",message = Messages.ValidationErrors.NOT_WHOLE_NUMBER)
    private String nationalityId;
    @Length(min = 1, max = 7,message = Messages.ValidationErrors.LENGTH)
    @Pattern(regexp = "^[0-9]+$",message = Messages.ValidationErrors.NOT_WHOLE_NUMBER)
    private String customerId;
    @Pattern(regexp = "^[0-9]+$",message = Messages.ValidationErrors.NOT_WHOLE_NUMBER)
    private String accountNumber;
    @SearchMobilePhone(message = Messages.ValidationErrors.INVALID_MOBILE_PHONE)
    private String mobilePhone;
    @Length(min = 1, max = 50,message = Messages.ValidationErrors.LENGTH)
    @Pattern(regexp = "^\\p{L}+$",message = Messages.ValidationErrors.NOT_ALPHABETICAL)
    private String firstName;
    @Length(min = 1, max = 50,message = Messages.ValidationErrors.LENGTH)
    @Pattern(regexp = "^\\p{L}+$",message = Messages.ValidationErrors.NOT_ALPHABETICAL)
    private String lastName;
    private String orderNumber;
}

package com.example.cms.service;

import com.example.cms.domain.SignUpForm;
import com.example.cms.domain.model.Customer;
import com.example.cms.exception.CustomException;
import com.example.cms.exception.ErrorCode;
import com.example.cms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static com.example.cms.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExists(String email){
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    @Transactional
    public void verifyEamil(String email,String code){
        Customer customer = customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(()-> {
                    return new CustomException(NOT_FOUND_USER);
                });
        if(customer.isVerify()){
            throw new CustomException(ALREADY_VERIFY_USER);
        }
        else if(!customer.getVerificationCode().equals(code)){
            throw new CustomException(WRONG_VERIFICATION);
        }
        else if(customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(EXPIRE_CODE);
        }
        customer.setVerify(true);
    };

    @Transactional
    public LocalDateTime ChangeCustomerValidateEmail(Long customerId, String verificationCode){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
            return customer.getVerifyExpiredAt();
        }throw new CustomException(ErrorCode.NOT_FOUND_USER);


    }

}

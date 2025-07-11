package net.me.ebanking.endpoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.me.ebanking.dto.CustomerDto;
import net.me.ebanking.entitie.Customer;
import net.me.ebanking.exception.CustomerNotFundException;
import net.me.ebanking.service.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerController {
    private BankAccountService bankAccountService;

    @PostMapping("customer")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Customer save(@RequestBody CustomerDto customerDto) {
        return bankAccountService.save(customerDto);
    }

    @GetMapping("customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDto> customerList() {
        return bankAccountService.customerList();
    }

    @GetMapping("customer/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CustomerDto getCustomer(@PathVariable Long id) throws CustomerNotFundException {
        return bankAccountService.getCustomer(id);
    }

    @PutMapping("updatecustomer/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void updateCustomer(@PathVariable Long id,@RequestBody CustomerDto customerDto) throws CustomerNotFundException {
        bankAccountService.updateCustomer(id, customerDto);
    }
    @DeleteMapping("deletecustomer/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFundException {
        bankAccountService.deleteCustomer(id);
    }
    @GetMapping("customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDto> getCustomerByKeyword(@RequestParam("keyword") String keyword) {
        return bankAccountService.getCustomerByKeyword(keyword);
    }
}



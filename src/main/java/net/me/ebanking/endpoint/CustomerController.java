package net.me.ebanking.endpoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.me.ebanking.dto.CustomerDto;
import net.me.ebanking.entitie.Customer;
import net.me.ebanking.exception.CustomerNotFundException;
import net.me.ebanking.service.BankAccountService;
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
    public Customer save(@RequestBody CustomerDto customerDto) {
        return bankAccountService.save(customerDto);
    }

    @GetMapping("customers")
    public List<CustomerDto> customerList() {
        return bankAccountService.customerList();
    }

    @GetMapping("customer/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) throws CustomerNotFundException {
        return bankAccountService.getCustomer(id);
    }

    @PutMapping("updatecustomer/{id}")
    public void updateCustomer(@PathVariable Long id,@RequestBody CustomerDto customerDto) throws CustomerNotFundException {
        bankAccountService.updateCustomer(id, customerDto);
    }
    @DeleteMapping("deletecustomer/{id}")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFundException {
        bankAccountService.deleteCustomer(id);
    }
    @GetMapping("customers/search")
    public List<CustomerDto> getCustomerByKeyword(@RequestParam("keyword") String keyword) {
        return bankAccountService.getCustomerByKeyword(keyword);
    }
}



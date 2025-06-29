package net.me.ebanking.mapper;

import lombok.AllArgsConstructor;
import net.me.ebanking.dto.CurrentAccountDto;
import net.me.ebanking.dto.SavingAccountDto;
import net.me.ebanking.entitie.CurrentAccount;
import net.me.ebanking.entitie.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankaccountMapper {

    private CustomerMapper customerMapper;

    public CurrentAccountDto fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDto currentAccountDto = new CurrentAccountDto();
        BeanUtils.copyProperties(currentAccount, currentAccountDto);
        currentAccountDto.setCustomerDto(customerMapper.fromCustomer(currentAccount.getCustomer()));
        currentAccountDto.setType(currentAccount.getClass().getSimpleName());
        return  currentAccountDto;
    }
    public CurrentAccount fromCurrentAccountDto(CurrentAccountDto currentAccountDto){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDto, currentAccount);
        currentAccount.setCustomer(customerMapper.fromCustomerDto(currentAccountDto.getCustomerDto()));
        return currentAccount;
    }

    public SavingAccountDto fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDto savingAccountDto = new SavingAccountDto();
        BeanUtils.copyProperties(savingAccount, savingAccountDto);
        savingAccountDto.setCustomerDto(customerMapper.fromCustomer(savingAccount.getCustomer()));
        savingAccountDto.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDto;
    }
    public SavingAccount fromSavingAccountDto(SavingAccountDto savingAccountDto){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDto, savingAccount);
        savingAccount.setCustomer(customerMapper.fromCustomerDto(savingAccountDto.getCustomerDto()));
        return savingAccount;
    }


}

package net.me.ebanking.mapper;

import net.me.ebanking.dto.AccountOperationDto;
import net.me.ebanking.entitie.AccountOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountOperationMapper {

    public AccountOperationDto fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDto accountOperationDto = new AccountOperationDto();
        BeanUtils.copyProperties(accountOperation, accountOperationDto);
        return accountOperationDto;
    }

    public AccountOperation fromAccountOperationDto(AccountOperationDto accountOperationDto){
        AccountOperation accountOperation = new AccountOperation();
        BeanUtils.copyProperties(accountOperationDto, accountOperation);
        return accountOperation;
    }
}

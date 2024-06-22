package net.achraf.ebankingbackend.services;

import jakarta.transaction.Transactional;
import net.achraf.ebankingbackend.entities.BankAccount;
import net.achraf.ebankingbackend.entities.CurrentAccount;
import net.achraf.ebankingbackend.entities.SavingAccount;
import net.achraf.ebankingbackend.repositories.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankAccountRepo bankAccountRepo;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepo.findById("").orElse(null);
        if (bankAccount != null){
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft=>"+((CurrentAccount)bankAccount).getOverDraft());
            }else if (bankAccount instanceof SavingAccount){
                System.out.println("Rate=>"+((SavingAccount)bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
            });
        }
    };

}

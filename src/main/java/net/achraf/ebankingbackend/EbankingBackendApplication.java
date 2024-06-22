package net.achraf.ebankingbackend;

import net.achraf.ebankingbackend.dtos.BankAccountDTO;
import net.achraf.ebankingbackend.dtos.CurrentBankAccountDTO;
import net.achraf.ebankingbackend.dtos.CustomerDTO;
import net.achraf.ebankingbackend.dtos.SavingBankAccountDTO;
import net.achraf.ebankingbackend.entities.*;
import net.achraf.ebankingbackend.enums.AccountStatus;
import net.achraf.ebankingbackend.enums.OperationType;
import net.achraf.ebankingbackend.exceptions.CustomerNotFoundException;
import net.achraf.ebankingbackend.repositories.AccountOperationRepo;
import net.achraf.ebankingbackend.repositories.BankAccountRepo;
import net.achraf.ebankingbackend.repositories.CustomerRepo;
import net.achraf.ebankingbackend.services.BankAccountService;
import net.achraf.ebankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
		}
		@Bean
		CommandLineRunner commandLineRunner (BankAccountService bankAccountService){
			return args -> {
				Stream.of("Hassan", "Imane", "Mohamed").forEach(name -> {
					CustomerDTO customer = new CustomerDTO();
					customer.setName(name);
					customer.setEmail(name + "@gmail.com");
					bankAccountService.saveCustomer(customer);
				});
				bankAccountService.listCustomers().forEach(customer -> {
					try {
						bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
						bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());

					} catch (CustomerNotFoundException e) {
						e.printStackTrace();
					}
				});
				List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
				for (BankAccountDTO bankAccount : bankAccounts) {
					for (int i = 0; i < 10; i++) {
						String accountId;
						if (bankAccount instanceof SavingBankAccountDTO) {
							accountId = ((SavingBankAccountDTO) bankAccount).getId();
						} else {
							accountId = ((CurrentBankAccountDTO) bankAccount).getId();
						}
						bankAccountService.credit(accountId, 10000 + Math.random() * 120000, "Credit");
						bankAccountService.debit(accountId, 1000 + Math.random() * 9000, "Debit");
					}
				}
			};
		}
	//@Bean
	CommandLineRunner start(CustomerRepo customerRepo,
							BankAccountRepo bankAccountRepo,
							AccountOperationRepo accountOperationRepo){
		return args -> {
			Stream.of("Hassan","Yassine","Ahmed").forEach(name->{
				Customer customer=new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepo.save(customer);
			});
			customerRepo.findAll().forEach(cust ->{
				CurrentAccount currentAccount=new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(9000);
				bankAccountRepo.save(currentAccount);

				SavingAccount savingAccount=new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(5);
				bankAccountRepo.save(savingAccount);
			});
			bankAccountRepo.findAll().forEach(acc -> {
				for (int i = 0;i<10;i++){
					AccountOperation accountOperation=new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
					accountOperation.setBankAccount(acc);
					accountOperationRepo.save(accountOperation);
				}
			});
		};
	}

}

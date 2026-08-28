package com.example.student_library_management_system.service;

import com.example.student_library_management_system.enums.TransactionType;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Transaction;
import com.example.student_library_management_system.repository.BookRepository;
import com.example.student_library_management_system.repository.CardRepository;
import com.example.student_library_management_system.repository.TransactionRepository;
import com.example.student_library_management_system.requestdto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    public String createTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = new Transaction();
        if(transactionRequestDto.getTransactionType().equals(TransactionType.BORROW)){
            transaction.setTransactionType(TransactionType.BORROW);
            transaction.setDueDate(LocalDate.now().plusDays(7).toString());
        } else if (transactionRequestDto.getTransactionType().equals(TransactionType.RETURN)){
            transaction.setTransactionType(TransactionType.RETURN);
            transaction.setDueDate(null);
        }

        // cardid and bookid - foreign keys
        // we have to go to cardrepository and get the details of card if present then set it inside transaction
        // we have to go to bookrepository and get the details of book if present then set it inside transaction

        Optional<Card> cardOptional = cardRepository.findById(transactionRequestDto.getCardId());
        if(cardOptional.isPresent()){
            transaction.setCard(cardOptional.get());
        } else {
            transaction.setCard(null);
        }

        Optional<Book> bookOptional = bookRepository.findById(transactionRequestDto.getBookId());
        if(bookOptional.isPresent()){
            transaction.setBook(bookOptional.get());
        } else {
            transaction.setBook(null);
        }

        transactionRepository.save(transaction);

        return "Transaction created successfully!";
    }
}

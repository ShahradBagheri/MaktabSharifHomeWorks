package util;

import repository.LoanRepository;
import repository.PaymentRepository;
import repository.StudentRepository;
import repository.impl.LoanRepositoryImpl;
import repository.impl.PaymentRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import service.*;
import service.impl.*;

public class ApplicationContext {

    public static final StudentRepository studentRepository;
    public static final LoanRepository loanRepository;
    public static final PaymentRepository paymentRepository;

    public static final StudentService studentService;
    public static final LoanService loanService;
    public static final CardService cardService;
    public static final RentContractService rentContractService;
    public static final PaymentService paymentService;

    static {
        studentRepository = new StudentRepositoryImpl();
        loanRepository = new LoanRepositoryImpl();
        paymentRepository = new PaymentRepositoryImpl();

        studentService = new StudentServiceImpl();
        loanService = new LoanServiceImpl();
        cardService = new CardServiceImpl();
        rentContractService = new RentContractServiceImpl();
        paymentService = new PaymentServiceImpl();
    }
}

package dasturlash.uz.containers;

import dasturlash.uz.controllers.*;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.repositories.*;
import dasturlash.uz.services.*;
import dasturlash.uz.utils.ProfileValidationUtil;

import java.util.Scanner;

public class ComponentContainer {
    public static TableRepository tableRepository = new TableRepository();
    public static InitService initService = new InitService();
    public static AuthService authService = new AuthService();
    public static MainController mainController = new MainController();
    public static Scanner scannerForStr = new Scanner(System.in);
    public static Scanner scannerForDigit = new Scanner(System.in);
    public static CategoryController categoryController = new CategoryController();
    public static CategoryService categoryService = new CategoryService();
    public static CategoryRepository categoryRepository = new CategoryRepository();
    public static BookController bookController = new BookController();
    public static BookService bookService = new BookService();
    public static BookRepository bookRepository = new BookRepository();
    public static ProfileController profileController = new ProfileController();
    public static ProfileService profileService = new ProfileService();
    public static ProfileValidationUtil profileValidationUtil = new ProfileValidationUtil();
    public static ProfileRepository profileRepository = new ProfileRepository();
    public static StudentProfileController studentProfileController = new StudentProfileController();
    public static StudentBookRepository studentBookRepository = new StudentBookRepository();
    public static StudentBookService studentBookService = new StudentBookService();
    public static ProfileDTO profileDTO;


}

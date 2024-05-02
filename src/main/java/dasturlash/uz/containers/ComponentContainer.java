package dasturlash.uz.containers;

import dasturlash.uz.controllers.BookController;
import dasturlash.uz.controllers.CategoryController;
import dasturlash.uz.controllers.MainController;
import dasturlash.uz.repositories.BookRepository;
import dasturlash.uz.repositories.CategoryRepository;
import dasturlash.uz.repositories.TableRepository;
import dasturlash.uz.services.AuthService;
import dasturlash.uz.services.BookService;
import dasturlash.uz.services.CategoryService;
import dasturlash.uz.services.InitService;

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


}

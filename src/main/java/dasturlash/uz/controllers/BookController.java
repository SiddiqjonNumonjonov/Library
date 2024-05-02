package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.BookDTO;

import java.time.LocalDate;

public class BookController {
    public void start() {
        ComponentContainer.tableRepository.createTable();
        ComponentContainer.initService.initAdmin();
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1:
                    bookLists();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("something went wrong !!!!");
            }
        }
    }

    private void delete() {
        System.out.println("enter id : ");
        int id = ComponentContainer.scannerForDigit.nextInt();

       var isDeleted =  ComponentContainer.bookService.delete(id);
       if(isDeleted) {
           System.out.println("deleted successfully");
       }
    }

    private void search() {
        System.out.println("enter query : ");
        String query = ComponentContainer.scannerForStr.nextLine();

       var searchedBooks  = ComponentContainer.bookService.search(query);
       if(searchedBooks == null) {
           System.err.println("no result !");
       }else {
           for (BookDTO bookDTO : searchedBooks) {
               System.out.println(bookDTO.getId()+" "+bookDTO.getTitle()+" "+bookDTO.getCategoryDTO().getName());
           }
       }
    }

    private void bookLists() {
       var allBooks =  ComponentContainer.bookService.bookLists();

       if(allBooks == null) {
           System.out.println("no book");
       }else {
           for (BookDTO bookDTO : allBooks) {
               System.out.println(bookDTO.getId()+" "+bookDTO.getTitle() + " "+bookDTO.getCategoryDTO().getName());
           }
       }
    }

    private void addBook() {
        System.out.println("enter title : ");
        String title = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter author : ");
        String author = ComponentContainer.scannerForStr.nextLine();

        System.out.println("enter available days :");
        int availableDays = ComponentContainer.scannerForDigit.nextInt();

        System.out.println("enter category id : ");
        int categoryId = ComponentContainer.scannerForDigit.nextInt();

        System.out.println("enter published date : ");
        LocalDate publishedAt = LocalDate.parse(ComponentContainer.scannerForStr.next());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(author);
        bookDTO.setAvailableDays(availableDays);
        bookDTO.setCategory_id(categoryId);
        bookDTO.setTitle(title);
        bookDTO.setPublishedAt(publishedAt);

       var isAdded =  ComponentContainer.bookService.add(bookDTO);
       if(isAdded) {
           System.out.println("added successfully");
       }
    }

    public void printMenu() {
        System.out.println("***Book Menu***");
        System.out.println("1=>Book lists");
        System.out.println("2=>Search");
        System.out.println("3=>Add book");
        System.out.println("4=>Remove book");
        System.out.println("5=>Books on hand");
        System.out.println("6=>Book history");
        System.out.println("7=>Best books");
    }
    public int getAction () {
        return ComponentContainer.scannerForDigit.nextInt();
    }
}

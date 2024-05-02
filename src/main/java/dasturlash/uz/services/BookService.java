package dasturlash.uz.services;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.BookDTO;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {
  public Boolean add(BookDTO bookDTO) {
      if(!isValid(bookDTO)) {
          return false;
      }

      var isExist = ComponentContainer.categoryRepository.getById(bookDTO.getCategory_id());

      if(isExist == null) {
          return false;
      }

      bookDTO.setCreatedAt(LocalDateTime.now());
      bookDTO.setVisible(true);

    return   ComponentContainer.bookRepository.add(bookDTO);
  }
  public Boolean isValid(BookDTO bookDTO) {
      if(bookDTO.getTitle() == null || bookDTO.getTitle().isBlank() || bookDTO.getTitle().length() < 3) {
          return false;
      }
      return bookDTO.getAuthor() != null && !bookDTO.getAuthor().isBlank() && bookDTO.getAuthor().length() >= 3;
  }

    public List<BookDTO> bookLists() {
      var allBooks = ComponentContainer.bookRepository.bookLists();
      if(allBooks.isEmpty()) {
          return null;
      }
      return allBooks;
    }

    public List<BookDTO> search(String query) {
     var searchedBooks =  ComponentContainer.bookRepository.search(query);
     if(searchedBooks.isEmpty()) {
         return null;
     }
      return searchedBooks;
    }

    public Boolean delete(int id) {
       return ComponentContainer.bookRepository.delete(id);
    }
}

package dasturlash.uz.services;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.dtos.StudentBookDTO;
import dasturlash.uz.enums.StudentBookStatus;

import java.time.LocalDateTime;
import java.util.List;

public class StudentBookService {
    public Boolean takeBook(int bookId) {

        var isExist = ComponentContainer.bookRepository.getById(bookId);
        if(isExist == null) {
            System.out.println("something went wrong !!!");
            return false;
        }
        StudentBookDTO studentBookDTO = new StudentBookDTO();
        studentBookDTO.setBookId(bookId);
        studentBookDTO.setStudentId(ComponentContainer.profileDTO.getId());
        studentBookDTO.setStatus(StudentBookStatus.TAKEN);
        studentBookDTO.setCreatedAt(LocalDateTime.now());
        studentBookDTO.setReturnedAt(null);

        return ComponentContainer.studentBookRepository.add(studentBookDTO);

    }

    public List<StudentBookDTO> booksOnHand(int id) {
       ProfileDTO profileDTO =  ComponentContainer.profileRepository.getById(id);
       if(profileDTO == null) {
           return null;
       }

      return ComponentContainer.studentBookRepository.booksOnHand(id);
    }
}

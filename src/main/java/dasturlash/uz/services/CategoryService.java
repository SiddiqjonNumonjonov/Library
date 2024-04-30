package dasturlash.uz.services;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.CategoryDTO;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryService {
    public Boolean add(CategoryDTO categoryDTO) {

        CategoryDTO category  = ComponentContainer.categoryRepository.getByName(categoryDTO.getName());
        if(category != null) {
            return false;
        }

        categoryDTO.setVisible(true);
        categoryDTO.setCreatedAt(LocalDateTime.now());

       return ComponentContainer.categoryRepository.add(categoryDTO);

    }

    public List<CategoryDTO> allCategories() {
        var allCategories = ComponentContainer.categoryRepository.allCategories();
        if(allCategories.isEmpty()) {
            return null;
        }
        return allCategories;
    }

    public Boolean delete(String name) {
        CategoryDTO categoryDTO = ComponentContainer.categoryRepository.getByName(name);

        if(categoryDTO == null){
            return false;
        }
        return ComponentContainer.categoryRepository.delete(name);
    }
}

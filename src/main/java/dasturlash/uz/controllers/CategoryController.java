package dasturlash.uz.controllers;

import dasturlash.uz.containers.ComponentContainer;
import dasturlash.uz.dtos.CategoryDTO;

public class CategoryController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int action = getAction();

            switch (action) {
                case 1:
                    categoryLists();
                    break;
                case 2:
                    delete();
                break;
                case 3:
                    add();
                    break;
                case 4:
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.err.println("something went wrong !!!");
            }
        }
    }

    private void delete() {
        System.out.println("enter name : ");
        String name = ComponentContainer.scannerForStr.nextLine();

       var isDeleted =  ComponentContainer.categoryService.delete(name);
       if(isDeleted) {
           System.out.println("deleted successfully");
       }
    }

    private void categoryLists() {
        var allCategories = ComponentContainer.categoryService.allCategories();

        if(allCategories == null) {
            System.out.println("no category");
        }else{
            for (CategoryDTO categoryDTO : allCategories) {
                System.out.println(categoryDTO.getId() +" "+ categoryDTO.getName());
            }
        }
    }

    private void add() {

        System.out.println("enter name : ");
        String name = ComponentContainer.scannerForStr.nextLine();

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);

       var isAdded = ComponentContainer.categoryService.add(categoryDTO);
       if(isAdded) {
           System.out.println("created Successfully");
       }

    }

    public void printMenu () {
            System.out.println("***Admin Menu***");
            System.out.println("1=>Category lists");
            System.out.println("2=> Delete Category");
            System.out.println("3=>Add category ");
            System.out.println("0=>Exit");
            System.out.println("enter action : ");
        }
        public int getAction () {
            return ComponentContainer.scannerForDigit.nextInt();
        }
    }

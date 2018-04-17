package me.afua.booleanexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    BookRepository bookRepo;

    @RequestMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("library", bookRepo.findAll());
        return "index";
    }

    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("aBook", new Book());
        return "addbook";
    }

    @RequestMapping("/savebook")
    public String saveBook(@ModelAttribute("aBook") Book toSave, BindingResult result) {

        if (result.hasErrors()) {
            return "addbook";
        }
        bookRepo.save(toSave);
        return "redirect:/";
    }

    @RequestMapping("/changestatus/{id}")
    public String borrowReturn(@PathVariable("id") long id) {
        Book thisBook = bookRepo.findById(id).get();
        //if (thisBook.isBorrowed())
            //Reverse the status
            thisBook.setBorrowed(!thisBook.isBorrowed());
            bookRepo.save(thisBook);
            return "redirect:/";
        }
         @RequestMapping("/update/{id}")
         public String updateBook(@PathVariable ("id") long id, Model model){
         model.addAttribute("aBook ",bookRepo.findById(id).get());
       //   Book bBook = new Book();

       //  System.out.println(bBook.getTitle()+bBook.getId() );
        return "addbook";
         }




    }




package com.work.sellbook.controllers;

import com.work.sellbook.entities.Book;
import com.work.sellbook.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
public class MVCController {

    private BookRepository bookRepository;
    public MVCController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping(path={"", "/home"})
    public String home()
    {
        return "Home";
    }

    @GetMapping(path = "sell")
    public String sell(@ModelAttribute(value = "new_book") Book book)
    {
        return "SellBook";
    }

    @PostMapping(path = "addbook")
    public String addBook(@ModelAttribute(value = "new_book") Book book)
    {
        bookRepository.save(book);
        System.out.println(book.toString());
        return "redirect:buy";
    }

    @GetMapping(path = "buy")
    public ModelAndView buy()
    {
        ModelAndView html = new ModelAndView("BuyBook");
        html.addObject("book_list", bookRepository.findAll());
        return html;
    }

    @PostMapping(path = "deletebook")
    public String deleteBook(@RequestParam(name = "id") Integer id)
    {
        bookRepository.deleteById(id);
        return "redirect:buy";
    }

    @GetMapping(path = "about")
    public String about()
    {
        return "About";
    }

//    @GetMapping(path = "sil")
//    public ModelAndView sil()
//    {
//        ModelAndView indexHtml = new ModelAndView("ders_sil");
//        indexHtml.addObject("ders_list", dersRepository.findAll());
//        return indexHtml;
//    }
//
//    @GetMapping(path = "ekle")
//    public ModelAndView ekle()
//    {
//        ModelAndView indexHtml = new ModelAndView("ders_ekle");
//        indexHtml.addObject("ders", new Ders());
//        indexHtml.addObject("ogretmen_list", ogretmenRepository.findAll());
//        indexHtml.addObject("konu_list", konuRepository.findAll());
//        return indexHtml;
//    }

}

package com.example.bookshopapi.service.impl;

import com.example.bookshopapi.dto.request.BookRequest;
import com.example.bookshopapi.dto.response.*;
import com.example.bookshopapi.entity.*;
import com.example.bookshopapi.enums.ExceptionEnum;
import com.example.bookshopapi.exceptions.MyException;
import com.example.bookshopapi.repository.*;
import com.example.bookshopapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;
    private final TypeRepository typeRepository;
    private final PublishingHouseRepository pubHouseRepository;


    @Override
    public ResponseModel<List<BookResponse>> getAllBooks() {
        List<Book> bookList=bookRepository.findAll();
        if (bookList.isEmpty()){
            throw new MyException(ExceptionEnum.EMPTY_BOOK);
        }
        List<BookResponse> bookResponseList=bookList.stream().map(book -> convertToRes(book)).collect(Collectors.toList());
        return ResponseModel.<List<BookResponse>>builder().result(bookResponseList)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode())
                .error(false).build();


    }

    @Override
    public ResponseModel<BookResponse> addBook(BookRequest bookRequest) {
        Author author=null;
        Type type=null;
        Language language=null;
        PublishingHouse publishingHouse=null;
        if (bookRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }
        if (authorRepository.findByAuthor(bookRequest.getAuthorRequest().getAuthor())==null){
            Author author1=new Author();
            author1.setAuthor(bookRequest.getAuthorRequest().getAuthor());
            author=authorRepository.save(author1);
        }else {
            author=authorRepository.findByAuthor(bookRequest.getAuthorRequest().getAuthor());
        }if (typeRepository.findByTypeName(bookRequest.getTypeRequest().getType())==null){
            Type type1=new Type();
            type1.setTypeName(bookRequest.getTypeRequest().getType());
            type=typeRepository.save(type1);
        }else {
            type=typeRepository.findByTypeName(bookRequest.getTypeRequest().getType());
        }if (languageRepository.findByLanguage(bookRequest.getLanguageRequest().getLanguage())==null){
            Language language1=new Language();
            language1.setLanguage(bookRequest.getLanguageRequest().getLanguage());
            language=languageRepository.save(language1);
        }else {
            language=languageRepository.findByLanguage(bookRequest.getLanguageRequest().getLanguage());
        }if (pubHouseRepository.findByHouseName(bookRequest.getPublishingHouseRequest().getPubHouse())==null){
            PublishingHouse publishingHouse1=new PublishingHouse();
            publishingHouse1.setHouseName(bookRequest.getPublishingHouseRequest().getPubHouse());
            publishingHouse=pubHouseRepository.save(publishingHouse1);
        }else {
            publishingHouse=pubHouseRepository.findByHouseName(bookRequest.getPublishingHouseRequest().getPubHouse());
        }
        Book book=new Book();
        book.setName(bookRequest.getName());
        book.setAuthor(author);
        book.setLanguage(language);
        book.setPublishingHouse(publishingHouse);
        book.setType(type);
        book.setPage(bookRequest.getPage());
        book.setPrice(bookRequest.getPrice());
        book.setDiscount(bookRequest.getDiscount());
        book.setStock(bookRequest.getStock());
        Book savedBook=bookRepository.save(book);
        BookResponse bookResponse=convertToRes(book);
        return ResponseModel.<BookResponse>builder().result(bookResponse)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode())
                .error(false).build();

    }

    @Override
    public ResponseModel<BookResponse> updateBook(Long bookId, BookRequest bookRequest) {
        if (bookRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }
        if (!bookRepository.findById(bookId).isPresent()){
            throw new MyException(ExceptionEnum.BOOK_NOT_FOUND);
        }
        Author author=null;
        Type type=null;
        Language language=null;
        PublishingHouse publishingHouse=null;
        if (bookRequest==null){
            throw new MyException(ExceptionEnum.BAD_REQUEST);
        }
        if (authorRepository.findByAuthor(bookRequest.getAuthorRequest().getAuthor())==null){
            Author author1=new Author();
            author1.setAuthor(bookRequest.getAuthorRequest().getAuthor());
            author=authorRepository.save(author1);
        }else {
            author=authorRepository.findByAuthor(bookRequest.getAuthorRequest().getAuthor());
        }if (typeRepository.findByTypeName(bookRequest.getTypeRequest().getType())==null){
            Type type1=new Type();
            type1.setTypeName(bookRequest.getTypeRequest().getType());
            type=typeRepository.save(type1);
        }else {
            type=typeRepository.findByTypeName(bookRequest.getTypeRequest().getType());
        }if (languageRepository.findByLanguage(bookRequest.getLanguageRequest().getLanguage())==null){
            Language language1=new Language();
            language1.setLanguage(bookRequest.getLanguageRequest().getLanguage());
            language=languageRepository.save(language1);
        }else {
            language=languageRepository.findByLanguage(bookRequest.getLanguageRequest().getLanguage());
        }if (pubHouseRepository.findByHouseName(bookRequest.getPublishingHouseRequest().getPubHouse())==null){
            PublishingHouse publishingHouse1=new PublishingHouse();
            publishingHouse1.setHouseName(bookRequest.getPublishingHouseRequest().getPubHouse());
            publishingHouse=pubHouseRepository.save(publishingHouse1);
        }else {
            publishingHouse=pubHouseRepository.findByHouseName(bookRequest.getPublishingHouseRequest().getPubHouse());
        }
        Book book=bookRepository.findById(bookId).get();
        book.setId(bookId);
        book.setName(bookRequest.getName());
        book.setAuthor(author);
        book.setLanguage(language);
        book.setPublishingHouse(publishingHouse);
        book.setType(type);
        book.setPage(bookRequest.getPage());
        book.setPrice(bookRequest.getPrice());
        book.setDiscount(bookRequest.getDiscount());
        book.setStock(bookRequest.getStock());
        Book updatedBook=bookRepository.save(book);
        BookResponse bookResponse=convertToRes(updatedBook);
        return ResponseModel.<BookResponse>builder().result(bookResponse)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode())
                .error(false).build();
    }

    @Override
    public ResponseModel<BookResponse> getBookById(Long bookId) {
        if (!bookRepository.findById(bookId).isPresent()){
            throw new MyException(ExceptionEnum.BOOK_NOT_FOUND);
        }
        Book book=bookRepository.findById(bookId).get();
        BookResponse bookResponse=convertToRes(book);
        return ResponseModel.<BookResponse>builder().result(bookResponse)
                .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode())
                .error(false).build();
    }

    @Override
    public void deleteBook(Long bookId) {
        if (!bookRepository.findById(bookId).isPresent()){
            throw new MyException(ExceptionEnum.BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(bookId);
    }

    public BookResponse convertToRes(Book book){
        AuthorResponse authorResponse=new AuthorResponse(book.getAuthor().getId(),book.getAuthor().getAuthor());
        TypeResponse typeResponse=new TypeResponse(book.getType().getId(),book.getType().getTypeName());
        LanguageResponse languageResponse=new LanguageResponse(book.getLanguage().getId(),book.getLanguage().getLanguage());
        PublishingHouseResponse phr=new PublishingHouseResponse(book.getPublishingHouse().getId(),book.getPublishingHouse().getHouseName());

        return BookResponse.builder().name(book.getName()).authorResponse(authorResponse)
                .typeResponse(typeResponse).languageResponse(languageResponse).publishingHouseResponse(phr).stock(book.getStock())
                .id(book.getId()).page(book.getPage()).price(book.getPrice()).discount(book.getDiscount()).build();
    }
}

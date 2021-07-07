/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.users;

import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.entities.Book;
import onlineBookstoreServiceSystem.entities.BookCopy;
import onlineBookstoreServiceSystem.enums.FormOfEmployment;
import onlineBookstoreServiceSystem.enums.Genre;
import java.time.LocalDate;
import java.util.List;


/**
 * This class stores data about storekeeper.
 * Storekeeper can add, edit and delete books
 *
 * Inherits from Employee
 *
 * Linked with:
 * 1. Book with cardinality 1 - 1..*
 *
 * @see Employee
 * @see Book
 */
public class Storekeeper extends Employee
{
    /**
     * Storekeeper constructor
     *
     * @param name
     * @param surname
     * @param address
     * @param employmentDate
     * @param baseSalary
     * @param formOfEmployment
     * @throws Exception
     */
    public Storekeeper(String name, String surname, Address address,
                       LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment) throws Exception
    {
        super(name, surname, address, employmentDate, baseSalary, formOfEmployment);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

    /**
     * This method links storekeeper with selected book
     *
     * @param book
     */
    public void addLinkBook(Book book)
    {
        this.addLink("edits","is edited",  book);
    }

    /**
     * This method adds new book
     *
     * @param title
     * @param author
     * @param price
     * @param genre
     * @return newly created book
     * @throws Exception
     */
    public Book addBook(String title, String author, int price, Genre genre) throws Exception
    {
        Book newBook = new Book(title, author, price, genre);
        this.addLinkBook(newBook);

        return newBook;
    }

    /**
     * This method deletes selected book from the book list
     *
     * @param bookToDelete
     * @param bookList
     */
    public void deleteBook(Book bookToDelete, List<Book> bookList)
    {
        bookList.remove(bookToDelete);
    }

    /**
     * This method adds new book copy and links it with selected book
     *
     * @param numberISBN
     * @param language
     * @param book
     * @return newly created book copy
     * @throws Exception
     */
    public BookCopy addBookCopy(int numberISBN, String language, Book book) throws Exception
    {
        BookCopy newBookCopy = new BookCopy(numberISBN, language);
        book.addLinkBookCopy(newBookCopy);

        return newBookCopy;
    }

}
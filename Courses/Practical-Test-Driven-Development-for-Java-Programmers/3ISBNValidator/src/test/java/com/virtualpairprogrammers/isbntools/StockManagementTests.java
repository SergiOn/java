package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @BeforeEach
    public void setup() {
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);

        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {
//        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
//            @Override
//            public Book lookup(String isbn) {
//                return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
//            }
//        };
//        ExternalISBNDataService testDatabaseService = isbn -> null;

//        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));

//        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

//        StockManager stockManager = new StockManager();
//        stockManager.setWebService(testWebService);
//        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
//        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
//        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

//        when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

//        StockManager stockManager = new StockManager();
//        stockManager.setWebService(webService);
//        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

//        verify(databaseService, times(1)).lookup("0140177396");
//        verify(databaseService).lookup("0140177396");
        verify(testDatabaseService).lookup("0140177396");
//        verify(webService, times(0)).lookup(anyString());
//        verify(webService, never()).lookup(anyString());
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
//        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
//        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

//        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
//        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

//        StockManager stockManager = new StockManager();
//        stockManager.setWebService(webService);
//        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

//        verify(databaseService, times(1)).lookup("0140177396");
//        verify(databaseService).lookup("0140177396");
        verify(testDatabaseService).lookup("0140177396");
//        verify(webService, times(1)).lookup("0140177396");
//        verify(webService).lookup("0140177396");
        verify(testWebService).lookup("0140177396");
    }

}

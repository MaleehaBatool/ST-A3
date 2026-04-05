package com.framework.tests;

import com.framework.service.ShoppingCart;
import com.framework.model.Product;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ShoppingCart service.
 */
@DisplayName("ShoppingCart Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("fast")
class ShoppingCartTest {

    private ShoppingCart cart;
    private Product apple;
    private Product laptop;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        apple = new Product("P001", "Apple", 1.5, 100);
        laptop = new Product("P002", "Laptop", 999.99, 10);
    }

    // ---- POSITIVE TESTS ----

    @Test
    @Order(1)
    @DisplayName("should_addItemSuccessfully_when_productIsValid")
    void should_addItemSuccessfully_when_productIsValid() {
        cart.addItem(apple, 5);
        assertEquals(5, cart.getItemCount());
        assertFalse(cart.isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("should_calculateCorrectSubtotal_when_itemsAreAdded")
    void should_calculateCorrectSubtotal_when_itemsAreAdded() {
        cart.addItem(apple, 4);   // 4 * 1.5 = 6.0
        cart.addItem(laptop, 1);  // 1 * 999.99 = 999.99
        assertEquals(1005.99, cart.getSubtotal(), 0.01);
    }

    @Test
    @Order(3)
    @DisplayName("should_applyDiscountCorrectly_when_discountPercentProvided")
    void should_applyDiscountCorrectly_when_discountPercentProvided() {
        cart.addItem(laptop, 1);  // 999.99
        cart.applyDiscount(10);   // 10% off
        assertEquals(899.991, cart.getTotal(), 0.01);
    }

    @Test
    @Order(4)
    @DisplayName("should_removeItem_when_productExistsInCart")
    void should_removeItem_when_productExistsInCart() {
        cart.addItem(apple, 3);
        cart.removeItem(apple);
        assertTrue(cart.isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("should_updateQuantity_when_productIsInCart")
    void should_updateQuantity_when_productIsInCart() {
        cart.addItem(apple, 3);
        cart.updateQuantity(apple, 10);
        assertEquals(10, cart.getItemCount());
    }

    @Test
    @Order(6)
    @DisplayName("should_clearCart_when_clearIsCalled")
    void should_clearCart_when_clearIsCalled() {
        cart.addItem(apple, 5);
        cart.addItem(laptop, 2);
        cart.clearCart();
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
    }

    // ---- NEGATIVE TESTS ----

    @Test
    @Order(7)
    @DisplayName("should_throwException_when_nullProductAdded")
    void should_throwException_when_nullProductAdded() {
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(null, 1));
    }

    @Test
    @Order(8)
    @DisplayName("should_throwException_when_quantityExceedsStock")
    void should_throwException_when_quantityExceedsStock() {
        assertThrows(IllegalStateException.class, () -> cart.addItem(laptop, 100));
    }

    @Test
    @Order(9)
    @DisplayName("should_throwException_when_removingNonExistentProduct")
    void should_throwException_when_removingNonExistentProduct() {
        assertThrows(IllegalArgumentException.class, () -> cart.removeItem(apple));
    }

    @Test
    @Order(10)
    @DisplayName("should_throwException_when_discountIsAbove100")
    void should_throwException_when_discountIsAbove100() {
        assertThrows(IllegalArgumentException.class, () -> cart.applyDiscount(110));
    }

    // ---- BOUNDARY TESTS ----

    @Test
    @Order(11)
    @DisplayName("should_returnZeroTotal_when_cartIsEmpty")
    void should_returnZeroTotal_when_cartIsEmpty() {
        assertEquals(0.0, cart.getTotal(), 0.001);
    }

    @Test
    @Order(12)
    @DisplayName("should_applyZeroDiscount_when_discountIsZero")
    void should_applyZeroDiscount_when_discountIsZero() {
        cart.addItem(apple, 2);
        cart.applyDiscount(0);
        assertEquals(3.0, cart.getTotal(), 0.001);
    }
}

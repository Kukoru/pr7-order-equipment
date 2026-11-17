package ru.edu.pr04;

public class OrderItem {
    private final Product product; 
    private int quantity;
    
    // Вариант 7: Максимальное количество = 40
    public static final int MAX_QUANTITY = 40;

    public OrderItem(Product product, int quantity) { 
        this.product = product; 
        setQuantity(quantity); 
    }
    
    private void setQuantity(int quantity) {
        if (quantity < 1 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Количество должно быть от 1 до " + MAX_QUANTITY);
        }
        this.quantity = quantity; 
    }
    
    public void increase(int delta) { 
        setQuantity(this.quantity + delta); 
    }
    
    public Product getProduct() { return product; } 
    public int getQuantity() { return quantity; }
    
    public double getLineTotal() { 
        return product.getPrice() * quantity; 
    }
}
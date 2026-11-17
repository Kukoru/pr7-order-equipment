package ru.edu.pr04;

import java.util.*;

public class Order {
    private final Employee employee; 
    private final List<OrderItem> items = new ArrayList<>(); 
    private OrderStatus status = OrderStatus.DRAFT;
    
    public Order(Employee employee) { 
        this.employee = Objects.requireNonNull(employee); 
    }
    
    public void addProduct(Product product, int quantity) {
        if (status == OrderStatus.CONFIRMED) throw new IllegalStateException("Заказ подтвержден");
        
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increase(quantity); // Объединение повторного товара
                return;
            }
        }
        items.add(new OrderItem(product, quantity));
    }
    
    public void removeProduct(String productId) {
        if (status == OrderStatus.CONFIRMED) throw new IllegalStateException("Заказ подтвержден");
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    
    public double total() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getLineTotal();
        }
        return sum;
    }
    
    public void confirm() {
        if (items.isEmpty()) throw new IllegalStateException("Нельзя подтвердить пустой заказ");
        this.status = OrderStatus.CONFIRMED;
    }
    
    // В исходнике уже правильно: List.copyOf создает неизменяемую копию
    public List<OrderItem> getItems() { return List.copyOf(items); }
    public OrderStatus getStatus() { return status; }
}
package ru.edu.pr04;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = true;
        try { 
            Employee emp = new Employee("E1", "Ivanov");
            Product p1 = new Product("P1", "Mouse", 1000);
            Product p2 = new Product("P2", "Keyboard", 2000);
            
            Order order = new Order(emp);
            order.addProduct(p1, 10);
            order.addProduct(p1, 20); // Объединение в 30
            order.addProduct(p2, 10);
            
            ok &= order.getItems().size() == 2;
            ok &= Math.abs(order.total() - 50000.0) < 0.001;

            // Проверка границы 40
            try {
                order.addProduct(p2, 31); // 10 + 31 = 41 (Ошибка)
                ok = false;
            } catch (IllegalArgumentException e) {}

            // Проверка запрета изменений после confirm
            order.confirm();
            try {
                order.addProduct(p1, 1);
                ok = false;
            } catch (IllegalStateException e) {}

            // Проверка защиты коллекции
            try {
                order.getItems().add(new OrderItem(p1, 1));
                ok = false;
            } catch (UnsupportedOperationException e) {}
            
        } catch (Exception e) { 
            System.out.println("FAIL exception " + e.getMessage()); 
        }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}
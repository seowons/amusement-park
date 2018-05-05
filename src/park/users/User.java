package park.users;

import park.interfaces.IConsumable;
import park.interfaces.IUsable;
import park.products.Product;
import park.products.Ticket;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private double budget;
    private UserType type;
    private Ticket ticket;
    private ArrayList<Product> boughtProducts;

    public User(String name, int age, double budget, UserType type) {
        this.name = name;
        this.age = age;
        this.budget = budget;
        this.type = type;
        this.boughtProducts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBudget() {
        return budget;
    }

    public boolean getTicketNumber(String ticketNumber) {
        return this.ticket.getTicketNumber().equals(ticketNumber);
    }
    public double getTicketPrice() {
        return this.ticket.getTicketPrice();
    }
    public int getUserTicketCredits() {
        return ticket.getTicketCredits();
    }
        // UNDER 18 ,PENSIONER, DISABLED - price 15$
       // SMALLGROUP - price = 14$
        // BIGGROUP - price = 13$
    public void addTicket(String ticketNumber) {
        if (this.type == UserType.UNDER18 || this.type == UserType.PENSIONER || this.type == UserType.DISABLED) {
            this.ticket = new Ticket(ticketNumber, 15);
        } else if (this.type == UserType.ADULT) {
            this.ticket = new Ticket(ticketNumber, 20);
        } else if (this.type == UserType.SMALLGROUP) {
            this.ticket = new Ticket(ticketNumber, 14);
        } else {
            this.ticket = new Ticket(ticketNumber, 13);
        }
    }

    public Ticket getUserTicket() {
        return ticket;
    }

    public void addCredits(int numberOfTickets) {
        this.ticket.addCredits(numberOfTickets);
        this.budget -= numberOfTickets * this.getTicketPrice();
    }

    public void addBoughtProduct(Product product) {
        this.boughtProducts.add(product);
        this.budget -= product.getPrice();
    }

    public void sayAllProducts() {
        for (Product product : boughtProducts) {
            System.out.println(product);
        }
    }

    public List<String> getAllProductsNames() {
        List<String> productNames = new ArrayList<>();

        for (Product product : boughtProducts) {
            productNames.add(product.getName());
        }

        return productNames;

    }

    public Product getProductByName(String productName) {
        for (Product product : boughtProducts) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }

        return null;
    }

    public void consumeProduct(Product product) {
        boughtProducts.remove(product);

        if (product instanceof IConsumable) {
            ((IConsumable) product).consume();
            return;
        }

        if (product instanceof IUsable) {
            ((IUsable) product).use();
            return;
        }


    }

    @Override
    public String toString() {
        return String.format("|\tUser %s\t|\t%d years old\t|\tbudget %.2f$\t|\tTicket \"%s\"\t|", this.name, this.age, this.budget,this.ticket.getTicketNumber());
    }
}
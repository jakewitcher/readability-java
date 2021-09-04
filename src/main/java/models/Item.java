package models;

import java.util.Objects;

public class Item {
    private String upc = "0123";
    private final String description;
    private final Double quantity;
    private final UnitOfMeasurement unit;

    public Item(String description, Double quantity, UnitOfMeasurement unit) {
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UnitOfMeasurement getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return Objects.equals(description, that.description) && Objects.equals(quantity, that.quantity) && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, quantity, unit);
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }
}

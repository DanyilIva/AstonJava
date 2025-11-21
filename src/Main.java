public class Main {
    public class Product {
        String productName;
        String productDate;
        String productManufacturer;
        String productCountry;
        double productPrice;
        boolean productBooking;

        public Product(String productName, String productDate, String productManufacturer, String productCountry, double productPrice, boolean productBooking) {
            this.productName = productName;
            this.productDate = productDate;
            this.productManufacturer = productManufacturer;
            this.productCountry = productCountry;
            this.productPrice = productPrice;
            this.productBooking = productBooking;
        }

        public void productInfo() {
            System.out.println("Название товара: " + productName);
            System.out.println("Дата производства товара: " + productDate);
            System.out.println("Производитель товара: " + productManufacturer);
            System.out.println("Страна происхождения товара: " + productCountry);
            System.out.println("Цена товара: " + productPrice);
            if (productBooking) {
                System.out.println("Забронирован покупателем");
            } else {
                System.out.println("Бронь отсутствует");
            }
        }

    }

    public class Park {
        public class Attractions {
            String attractionName;
            String attractionTime;
            double attractionPrice;

            public Attractions(String attractionName,String attractionTime,double attractionPrice) {
                this.attractionName = attractionName;
                this.attractionTime = attractionTime;
                this.attractionPrice = attractionPrice;
            }

            public void attractionsInfo() {
                System.out.println("Название аттракциона: " + attractionName);
                System.out.println("Время работы: " + attractionTime);
                System.out.println("Стоимость: " + attractionPrice);
            }
        }

        public void infoAttractions() {
            Attractions [] attractions = new Attractions[4];
            attractions[0] = new Attractions("Колесо обозрения", "10:00 - 22:00", 500.0);
            attractions[1] = new Attractions("Американские горки", "11:00 - 21:00", 700.0);
            attractions[2] = new Attractions("Водные горки", "12:00 - 20:00", 600.0);
            attractions[3] = new Attractions("Мельница", "10:00 - 18:00", 300.0);
            for(Attractions attractionArray : attractions) {
                attractionArray.attractionsInfo();
                System.out.println();
            }
        }
    }

    public void main(String[] args) {
        Product product = new Product("PlayStation 5", "15.11.2016", "Sony Interactive Entertainment", "Япония", 3000.00,true);
        product.productInfo();
        System.out.println();

        Product [] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung Galaxy S25 Ultra", "01.02.2025", "Samsung Corporation", "Южная Корея", 5599.0, false);
        productsArray[1] = new Product("iPhone 15 Pro Max", "15.08.2025", "Apple Inc.", "США", 8999.0, true);
        productsArray[2] = new Product("Xiaomi Mi Notebook Air", "20.03.2025", "Xiaomi Technology Co., Ltd.", "Китай", 3499.0, false);
        productsArray[3] = new Product("Sony WH-1000XM5", "10.06.2025", "Sony Corporation", "Япония", 2999.0, true);
        productsArray[4] = new Product("PlayStation 5", "30.11.2025", "Sony Interactive Entertainment", "Япония", 4999.0, false);
        for(Product arrayProduct : productsArray) {
            arrayProduct.productInfo();
            System.out.println();
        }

        Park park = new Park();
        park.infoAttractions();
    }
}
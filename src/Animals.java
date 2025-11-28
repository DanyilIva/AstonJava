public class Animals {
    public static abstract class Animal {
        protected static int count = 0;

        protected Animal() {
            count++;
        }

        public abstract void run(int meters);
        public abstract void swim(int meters);

        public static int getAnimals() {
            return count;
        }
    }

    public static class Dog extends Animal {
        static int dogCount = 0;
        final String dogName;

        public Dog(String dogName) {
            super();
            this.dogName = dogName;
            dogCount++;
        }
        @Override
        public void run(int meters) {
            if (meters > 500) {
                System.out.println(dogName + " устал и не может бежать");
            }
            else {
                System.out.println(dogName + " пробежал " + meters + " метров");
            }
        }
        @Override
        public void swim(int meters) {
            if (meters > 10) {
                System.out.println(dogName + " устал и не может плыть");
            }
            else {
                System.out.println(dogName + " проплыл " + meters + " метров");
            }
        }

        public static int getDogs() {
            return dogCount;
        }
    }

    public static class Cat extends Animal {
        static int catCount = 0;
        final String catName;

        public Cat(String catName) {
            super();
            this.catName = catName;
            catCount++;
        }
        @Override
        public void run(int meters) {
            if (meters > 200) {
                System.out.println(catName + " устала и не может бежать");
            }
            else {
                System.out.println(catName + " пробежала " + meters + " метров");
            }
        }
        @Override
        public void swim(int meters) {
            System.out.println(catName + " не может плавать!");
        }

        public static int getCats() {
            return catCount;
        }
    }

    static class Miska {
        int foodLevel;

        public Miska (int startFood) {
            foodLevel = startFood;
        }

        public void feed(Cat cat, int amount) {
            if (amount > foodLevel) {
                System.out.println(cat.catName + " осталась голодной, еды не хватило.");
            } else {
                foodLevel -= amount;
                System.out.println(cat.catName + " хорошо поела и теперь довольна.");
            }
        }

        public void refill(int amount) {
            foodLevel += amount;
        }

        public int getFoodLevel() {
            return foodLevel;
        }
    }
}

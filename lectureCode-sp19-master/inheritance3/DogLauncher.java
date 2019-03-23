public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog d4 = new Dog("hanah",88);
        Dog d5 = new Dog("bigfa",99);
        Dog[] dogs = new Dog[]{d1, d2, d3,d4,d5};

        //Dog d = (Dog) Maximizer.max(dogs);
        //System.out.println(d.name);

        Comparator<Dog> nc = Dog.getNameComparetor();
        if(nc.compare(d1,d2) > 0){
            d1.bark();
        }else{
            d2.bark();
        }


    }
}

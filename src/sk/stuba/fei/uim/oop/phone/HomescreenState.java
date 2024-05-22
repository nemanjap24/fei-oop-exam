package sk.stuba.fei.uim.oop.phone;

class HomescreenState extends State{
    public HomescreenState(Phone phone) {
        super(phone);
    }

    @Override
    String getName() {
        return "Homescreen";
    }
}

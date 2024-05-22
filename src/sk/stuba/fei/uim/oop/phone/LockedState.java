package sk.stuba.fei.uim.oop.phone;

class LockedState extends State{
    public LockedState(Phone phone) {
        super(phone);
    }

    @Override
    String getName() {
        return "Locked";
    }

}

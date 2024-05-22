package sk.stuba.fei.uim.oop.phone;

class WaitingForPasswordState extends State{
    public WaitingForPasswordState(Phone phone) {
        super(phone);
    }

    @Override
    String getName() {
        return "WaitingForPassword";
    }
}

package sk.stuba.fei.uim.oop.phone;

class ApplicationRunningState extends State{
    public ApplicationRunningState(Phone phone) {
        super(phone);
    }

    @Override
    String getName() {
        return "ApplicationRunning";
    }
}

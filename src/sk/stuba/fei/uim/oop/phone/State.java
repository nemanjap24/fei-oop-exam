package sk.stuba.fei.uim.oop.phone;

public abstract class State {
    Phone phone;

    State(Phone phone) {
        this.phone = phone;
    }
    String getName() {
        return getClass().getSimpleName().replace("State", "");
    }
}







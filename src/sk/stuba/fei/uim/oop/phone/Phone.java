package sk.stuba.fei.uim.oop.phone;

import sk.stuba.fei.uim.oop.application.Application;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public class Phone {

    private State currentState;
    private Set<Application> installedApplications;
    private Application runningApplication;
    private boolean unlocked;

    Phone() {
        currentState = new LockedState(this);
        installedApplications = new HashSet<>();
        Application appStore = new Application("AppStore", true, false);
        Application firefox = new Application("Firefox");
        Application camera = new Application("Camera", false, true);
        installedApplications.addAll(Set.of(appStore, firefox, camera));
        unlocked = false;
    }

    private void changeState(State state){
        currentState = state;
    }
    public void powerButtonPressed() {
        if(currentState.getName().equals("Locked")){
            currentState = new WaitingForPasswordState(this);
        }else{
            currentState = new LockedState(this);
            unlocked = false;
        }
        runningApplication = null;
    }

    public void backButtonPressed() {
        if(currentState.getName().equals("ApplicationRunning")){
            if(isUnlocked())
            {
                changeState(new HomescreenState(this));
            }
            else{
                changeState(new LockedState(this));
            }
            runningApplication = null;
        }
    }

    public void install(Application app) {
        if(currentState.getName().equals("ApplicationRunning")){
            if(runningApplication.canInstallApplication())
                installedApplications.add(app);
        }
    }

    public void uninstall(Application app) {
        if(currentState.getName().equals("Homescreen")){
            installedApplications.remove(app);
        }
    }

    public void start(Application app) {
        if(currentState.getName().equals("Homescreen")){
            runningApplication = app;
            currentState = new ApplicationRunningState(this);
        } else if (app.startableFromLockedScreen() && currentState.getName().equals("WaitingForPassword")) {
            runningApplication = app;
            changeState(new ApplicationRunningState(this));
        }
    }

    public void passwordEntered(String password) {
        if(currentState.getName().equals("WaitingForPassword")){
            if(password.equals("123")){
                currentState = new HomescreenState(this);
                unlocked = true;
            }
        }
    }

    public Set<Application> getInstalledApplications() {
        return installedApplications;
    }

    public Application getRunningApplication() {
        return runningApplication;
    }

    public String getStateName() {
        return currentState.getName();
    }
    private boolean isUnlocked(){
        return unlocked;
    }
}

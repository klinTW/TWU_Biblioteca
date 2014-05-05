package com.twu.biblioteca;

public class QuitCommand implements Command {
    private DoneState done;

    public QuitCommand(DoneState done) {
        this.done = done;
    }

    @Override
    public void execute() {
        done.toggle();
    }

    @Override
    public String returnName() {
        return "0) Quit";
    }
}

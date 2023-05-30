package com.hzy;

/**
 * @title: Activity
 * @Author zxwyhzy
 * @Date: 2023/5/24 22:15
 * @Version 1.0
 */
// Activity.java  活动类

public class Activity {
    private State noRaffleState;
    private State canRaffleState;
    private State dispenseState;
    private State dispenseOutState;

    private State state;
    private int prizeCount;

    public Activity(int prizeCount) {
        noRaffleState = new NoRaffleState(this);
        canRaffleState = new CanRaffleState(this);
        dispenseState = new DispenseState(this);
        dispenseOutState = new DispenseOutState(this);

        this.prizeCount = prizeCount;

        if (prizeCount > 0) {
            state = noRaffleState;
        } else {
            state = dispenseOutState;
        }
    }
    public void deductMoney() {
        state.deduceMoney();
    }

    public void raffle() {
        if (state.raffle()) {
            state.dispensePrize();
        }
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(int prizeCount) {
        this.prizeCount = prizeCount;
    }
}

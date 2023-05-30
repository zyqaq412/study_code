package com.hzy;

import java.util.Random;

/**
 * @title: State
 * @Author zxwyhzy
 * @Date: 2023/5/24 21:53
 * @Version 1.0
 */
public abstract class State {
    protected Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public abstract void deduceMoney();
    public abstract boolean raffle();
    public abstract void dispensePrize();
}

class NoRaffleState extends State {
    public NoRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("扣除50积分");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("扣除积分后才能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
         System.out.println("不能发放奖品");
    }
}

class CanRaffleState extends State {
    public CanRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("已扣除积分，无需再次扣除");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖...");
        int lucky = new Random().nextInt(101);
        if (lucky <= 10 && activity.getPrizeCount() > 0) {
            activity.setState(activity.getDispenseState());
            return true;
        } else {
            System.out.println("很遗憾，没有中奖");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
         System.out.println("不能发放奖品");
    }
}

class DispenseState extends State {
    public DispenseState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("奖品已发放，请领取奖品后再参与抽奖");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品已发放，请领取奖品后再参与抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        if (activity.getPrizeCount() > 0) {
            System.out.println("恭喜您中奖了！领取奖品一份");
            activity.setPrizeCount(activity.getPrizeCount() - 1);
            if (activity.getPrizeCount() == 0) {
                activity.setState(activity.getDispenseOutState());
            } else {
                activity.setState(activity.getNoRaffleState());
            }
        } else {
            System.out.println("奖品已领完");
            activity.setState(activity.getDispenseOutState());
        }
    }
}

class DispenseOutState extends State {
    public DispenseOutState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("奖品已领完");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品已领完");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品已领完");
    }
}


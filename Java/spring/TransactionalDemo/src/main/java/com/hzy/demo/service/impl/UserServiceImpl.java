package com.hzy.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.demo.mapper.UserMapper;
import com.hzy.demo.pojo.User;
import com.hzy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title: UserServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/12/4 13:14
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @Transactional()
    public String Transfer(Integer fromId, Integer toId, Double money){
        try {
            // 查询转出账户
            User fromUser = getById(fromId);
            if (fromUser == null) {
                throw new RuntimeException("转出账户不存在");
            }
            log.info("id: {}的余额:{}",fromUser.getId(),fromUser.getBalance());
            // 查询转入账户
            User toUser = getById(toId);
            if (toUser == null) {
                throw new RuntimeException("转入账户不存在");
            }

            // 检查余额是否足够
            if (fromUser.getBalance() < money) {
                throw new RuntimeException("余额不足");
            }

            // 更新转出账户余额
            fromUser.setBalance(fromUser.getBalance() - money);
            updateById(fromUser);

            // 手动抛出异常，测试事务回滚
            // if (1 == 1) throw new RuntimeException("转账异常，事务回滚");

            // 更新转入账户余额
            toUser.setBalance(toUser.getBalance() + money);
            updateById(toUser);

            log.info("转账成功");
            return "转账成功";
        } catch (Exception e) {
            throw new RuntimeException("转账失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional()
    public String payment(Integer id, Double money) {
        try {
            User user = getById(id);
            if (user == null) {
                throw new RuntimeException("支付账户不存在");
            }
            // 更新转出账户余额
            user.setBalance(user.getBalance()-money);
            updateById(user);
            Thread.sleep(5000);
            // if (1 == 1) throw new RuntimeException("转账异常，事务回滚");
            log.info("支付成功");
        }catch (RuntimeException e){
            throw new RuntimeException("支付失败");
        }catch (Exception e){

        }
        return "支付成功";
    }
}

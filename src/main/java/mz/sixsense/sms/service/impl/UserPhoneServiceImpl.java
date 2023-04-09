package mz.sixsense.sms.service.impl;

import mz.sixsense.sms.entity.UserPhone;
import mz.sixsense.sms.repository.UserPhoneRepository;
import mz.sixsense.sms.service.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPhoneServiceImpl implements UserPhoneService {

    @Autowired
    private UserPhoneRepository userPhoneRepository;

    @Override
    public void insertUserPhone(String getPhone) {

        UserPhone userPhone = new UserPhone();
        userPhone.setMemberPhone(getPhone);

        userPhoneRepository.save(userPhone);
    }

    @Override
    public int checkUserPhone(String getPhone) {

        if(userPhoneRepository.findUserPhone(getPhone) == null){
            return 1;
        }else{
            return 0;
        }
    }
}

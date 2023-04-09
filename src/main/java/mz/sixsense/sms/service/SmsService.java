package mz.sixsense.sms.service;

import mz.sixsense.member.entity.Members;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface SmsService {
    String phoneNumberCheck(String to) throws CoolsmsException;

}

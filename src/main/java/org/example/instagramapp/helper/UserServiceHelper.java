package org.example.instagramapp.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.enums.ActiveType;
import org.example.instagramapp.enums.ErrorResponseEnum;
import org.example.instagramapp.model.entity.ConfirmationOtp;
import org.example.instagramapp.model.entity.Follower;
import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.model.exception.GeneralException;
import org.example.instagramapp.model.request.resetPassword.ResetPasswordRequest;
import org.example.instagramapp.repository.FollowerRepository;
import org.example.instagramapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import static org.example.instagramapp.global.GlobalData.currentUserId;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceHelper {
    UserRepository userRepository;
    FollowerRepository followerRepository;

    public void setActiveType(Users user, Integer activeType) {
        if (activeType == 0)
            user.setActiveType(ActiveType.PRIVATE.name());
        else if (activeType == 1)
            user.setActiveType(ActiveType.PUBLIC.name());
        else
            throw new GeneralException(ErrorResponseEnum.ACTIVE_TYPE_ERROR);
    }

    public Users getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new GeneralException(ErrorResponseEnum.USER_NOT_FOUND_EMAIL));
    }

    public Users getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() ->
                new GeneralException(ErrorResponseEnum.USER_NOT_FOUND_ID));
    }

    public void checkPasswordForReset(Users user, ConfirmationOtp otp, ResetPasswordRequest request) {
        if (!user.equals(otp.getUser()))
            throw new GeneralException(ErrorResponseEnum.USER_NOT_FOUND_OTP);
        if (!request.getNewPassword().equals(request.getRepeatPassword()))
            throw new GeneralException(ErrorResponseEnum.PASSWORD_NOT_MATCHES);
    }

    public void checkCurrentUser() {
        if (currentUserId == null)
            throw new GeneralException(ErrorResponseEnum.NOT_REGISTERED);
    }

    public Follower checkFollower(Users user) {
        Follower follower;
        if (followerRepository.findByUser_Id(user.getId()) == null) {
            follower = new Follower();
            follower.setUser(user);
        } else
            follower = followerRepository.findByUser_Id(user.getId());
        return follower;
    }
}